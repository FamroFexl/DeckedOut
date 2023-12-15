/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/

package com.fexl.deckedout.game.dungeon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import com.fexl.deckedout.game.Clank;
import com.fexl.deckedout.game.DOPlayer;
import com.fexl.deckedout.game.event.EventTypes;
import com.fexl.deckedout.game.event.Events;
import com.fexl.deckedout.game.zones.DOZone;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.CompoundTagArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;

/**
 * Performs actions on a loaded {@link com.fexl.deckedout.dungeon.Dungeon}
 */
public class GameDungeon {
	//Index is the level, value is the current amount of treasure spawned on that level
	public LinkedHashMap<Integer, Integer> currentTreasure = new LinkedHashMap<Integer, Integer>();
		
	//Index is the level, value is the current amount of embers spawned on that level
	public LinkedHashMap<Integer, Integer> currentEmbers = new LinkedHashMap<Integer, Integer>();
	
	public DOPlayer doPlayer;
	public Dungeon dungeon;
	public Minecraft minecraft;
	public Clank clank;
	public Events events;
	
	public ItemStack gameCompass;
	public BlockPos artifactPos;
	public DOZone randomArtifactZone;
	public boolean artifactRetrieved = false;
	
	private ResourceLocation frostEmberSound = new ResourceLocation("deckedout", "items.frost_ember");
	private ResourceLocation treasureSound = new ResourceLocation("deckedout", "items.treasure");
	private ResourceLocation artifactSound = new ResourceLocation("deckedout", "items.artifact");
	
	public GameDungeon(DOPlayer doPlayer, Dungeon dungeon) {
		this.doPlayer = doPlayer;
		this.dungeon = dungeon;
		
		randomArtifactZone = this.randomLevelZone(doPlayer.getSelectedDOLevel(), dungeon.artifactZones);
		artifactPos = randomArtifactZone.getRandValidZoneBlock();
	}
	
	public void spawnMobs(int level) {
		ArrayList<DOZone> levelZones = this.getLevelZones(level, dungeon.mobZones);
		
		for(DOZone zone : levelZones) {
			try {
				//NBT tag in the form of the nbt contained in the "summon" command with an "id" tag
				CompoundTag argument = (CompoundTagArgument.compoundTag()).parse(new StringReader(zone.rate.chance()));
				
				BlockPos randPos = zone.getRandValidZoneBlock();
				
				Entity entity2 = EntityType.loadEntityRecursive(argument, minecraft.level, entity -> {
					entity.moveTo(randPos.getX(), randPos.getY(), randPos.getZ(), entity.getXRot(), entity.getYRot());
					return entity;
				});
				
			} catch (CommandSyntaxException commandSyntaxException) {
				
			}
		}
	}
	
	/**
	 * Runs every tick (same as hopper minecarts) to check if the compass is in its block
	 */
	public boolean checkCompass() {
		//Check for entities in the blockPos
		List<ItemEntity> entities = doPlayer.level().getEntitiesOfClass(ItemEntity.class, new AABB(this.artifactPos, this.artifactPos), EntitySelector.ENTITY_STILL_ALIVE);
		for(ItemEntity entity : entities) {
			CompoundTag entityTag = entity.getItem().getTag();
			if(entityTag.contains("tag") && (entityTag.getCompound("tag").contains(CompassItem.TAG_LODESTONE_POS) && entityTag.getCompound("tag").get(CompassItem.TAG_LODESTONE_POS) == this.artifactPos)) {
				//doPlayer.level().playSound(null, artifact.blockPos, SoundEvent.createVariableRangeEvent(artifactSound), SoundSource.AMBIENT);
				this.artifactRetrieved = true;
				entity.kill();
						
				//Spawn the artifact
				try {
					//NBT Tag in the format of a "summon item" command with the "{Item:}" wrapper omitted
					CompoundTag argument = (CompoundTagArgument.compoundTag()).parse(new StringReader(randomArtifactZone.rate.chance()));
							
					//Spawn artifact on its blockPos
					new ItemEntity(minecraft.level, this.artifactPos.getX(), this.artifactPos.getY(), this.artifactPos.getZ(), ItemStack.of(argument));
							
					minecraft.level.playSound(null, this.artifactPos, SoundEvent.createVariableRangeEvent(artifactSound), SoundSource.valueOf(null));
							
				} catch (CommandSyntaxException e) {
					//ERROR
				}
						
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates the game compass that guides the {@link com.fexl.deckedout.game.DOPlayer} to the {@link com.fexl.deckedout.game.treasure.Artifact}
	 */
	public ItemStack createCompass() {
		String dimension = "";
		
		CompoundTag compassTag = new CompoundTag();
		compassTag.putString(CompassItem.TAG_LODESTONE_DIMENSION, dimension);
		compassTag.putBoolean(CompassItem.TAG_LODESTONE_TRACKED, true);
		compassTag.put(CompassItem.TAG_LODESTONE_POS, NbtUtils.writeBlockPos(this.artifactPos));
		
		ItemStack compass = new ItemStack(Items.COMPASS, 1);
		compass.setTag((CompoundTag)compass.getTag().put("tag", compassTag));
		
		return compass;
	}
	
	public boolean checkClank() {
		//Every game second (20 ticks), iterate through the clank zones and check if the player's position is in one.
		//If they are, activate this method
		
		long tickRate = 20;
		long pingRate = 2;
		
		for(DOZone zone : dungeon.clankZones) {
			//If the player enters the clank zone, every time this method gets activated, randomly determine if the clank goes up
			//Clank likelihood is determined by the number of valid blocks in the zone
			/**
			if((new Random()).nextInt(zone.getValidZoneBlocks().size()) == 0 && !zoneActive) {
				clank.addClank(1);
				//If the clank goes up, set zoneActive to true until the player leaves the zone, disabling clank increase
				zoneActive = true;
				//Effectively, even if a player spends a long time in a zone, they can only activate that zone once as long as they stay within it.
				
				return true;
			}**/
		}
		return false;
	}
	
	public boolean levelSpawnQueuedTreasure() {
		if(!this.addTreasure(1)) {
			return false;
		}
		
		this.levelSpawnTreasure();
		
		return true;
	}
	
	public boolean levelSpawnTreasure() {
		//Get a random zone on the DOPlayer's level
		DOZone randomTreasureZone = this.randomLevelZone(doPlayer.getCurrentDOLevel(), dungeon.treasureZones);
				
		//Stores the treasure item
		CompoundTag argument = null;
		
		//Spawn treasure on a random block within that zone
		try {
			//NBT tag in the format of https://minecraft.wiki/w/Player.dat_format#Item_structure
			argument = (CompoundTagArgument.compoundTag()).parse(new StringReader(randomTreasureZone.rate.chance()));
		} catch (CommandSyntaxException e) {
			//ERROR
		}
		
		//Item spawn blockPos
		BlockPos randPos = randomTreasureZone.getRandValidZoneBlock();
		
		InteractionResult result = events.ITEM_EVENT.invoker().interact(randPos, argument, EventTypes.Item.TREASURE);
		if(!(result == InteractionResult.FAIL)) {
			//Spawn a rate-weighted treasure on a random block
			new ItemEntity(minecraft.level, randPos.getX(), randPos.getY(), randPos.getZ(), ItemStack.of(argument));
			
			//play sound
			minecraft.level.playSound(null, randPos, SoundEvent.createVariableRangeEvent(treasureSound), SoundSource.valueOf(null));
			
			return true;
		}
				
		return false;
	}
	
	public boolean levelSpawnQueuedEmber() {
		if(!this.addEmbers(1)) {
			return false;
		}
		
		//Stores the ember item
		CompoundTag argument = null;
		
		try {
			//NBT tag in the format of https://minecraft.wiki/w/Player.dat_format#Item_structure
			argument = (CompoundTagArgument.compoundTag()).parse(new StringReader(dungeon.frostEmberItem));
		} catch (CommandSyntaxException e) {
			//ERROR
		}
		
		//Get a random zone on the DOPlayer's level
		DOZone randomTreasureZone = this.randomLevelZone(doPlayer.getCurrentDOLevel(), dungeon.treasureZones);
		
		BlockPos randPos = randomTreasureZone.getRandValidZoneBlock();
		
		InteractionResult result = events.ITEM_EVENT.invoker().interact(randPos, argument, EventTypes.Item.FROST_EMBER);
		
		if(!(result == InteractionResult.FAIL)) {
			//Spawn an ember on a random block within that zone
			new ItemEntity(minecraft.level, randPos.getX(), randPos.getY(), randPos.getZ(), ItemStack.of(argument));
			
			minecraft.level.playSound(null, randPos, SoundEvent.createVariableRangeEvent(frostEmberSound), SoundSource.valueOf(null));
			
			return true;
		}
		
		return false;
	}
	
	public boolean addTreasure(int treasure) {
		//Check the level's max treasure hasn't been reached
		if(currentTreasure.get(doPlayer.getCurrentDOLevel()) >= dungeon.getMaxTreasure(doPlayer.getCurrentDOLevel())) {
			return false;
		}
		
		//Increment the current treasure on that level
		currentTreasure.put(doPlayer.getCurrentDOLevel(), currentTreasure.get(doPlayer.getCurrentDOLevel()) + 1);
		
		return true;
	}
	
	public boolean addEmbers(int embers) {
		//Check the level's max embers hasn't been reached
		if(currentEmbers.get(doPlayer.getCurrentDOLevel()) >= dungeon.getMaxEmbers(doPlayer.getCurrentDOLevel())) {
			return false;
		}
		
		//Increment the current embers on that level
		currentEmbers.put(doPlayer.getCurrentDOLevel(), currentEmbers.get(doPlayer.getCurrentDOLevel()) + 1);
		
		return true;
	}
	
	private <T extends DOZone> T randomLevelZone(int level, ArrayList<T> zones) {
		ArrayList<T> levelZones = this.getLevelZones(level, zones);
		
		return levelZones.get((new Random()).nextInt(levelZones.size()));
	}
	
	private <T extends DOZone> ArrayList<T> getLevelZones(int level, ArrayList<T> zones) {
		ArrayList<T> levelZones = new ArrayList<T>();
		
		for(T zone : zones) {
			if(zone.level == level) {
				levelZones.add(zone);
			}
		}
		
		return levelZones;
	}
}
