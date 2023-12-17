package com.fexl.deckedout.game;

import java.util.List;

import com.fexl.deckedout.game.dungeon.Dungeon;
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
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;

public class Artifact {
	private ItemStack artifactCompass;
	private BlockPos artifactPos;
	private DOZone randomArtifactZone;
	private boolean artifactRetrieved = false;
	
	private Minecraft minecraft;
	private DOPlayer doPlayer;
	
	private ResourceLocation artifactSound = new ResourceLocation("deckedout", "items.artifact");
	
	public Artifact(Minecraft minecraft, DOPlayer doPlayer, Dungeon dungeon) {
		this.minecraft = minecraft;
		this.doPlayer = doPlayer;
		
		//Generate the random artifact zone and spawn point within that zone
		this.randomArtifactZone = Dungeon.randomLevelZone(doPlayer.getSelectedDOLevel(), dungeon.artifactZones);
		this.artifactPos = randomArtifactZone.getRandValidZoneBlock();
		
		//Create the compass
		this.artifactCompass = this.createCompass("", this.artifactPos);
	}
	
	/**
	 * Creates the game compass that guides the {@link com.fexl.deckedout.game.DOPlayer} to the {@link com.fexl.deckedout.game.treasure.Artifact}
	 */
	private ItemStack createCompass(String dimension, BlockPos blockPos) {
		CompoundTag compassTag = new CompoundTag();
		
		//Assign the meaningful tags
		compassTag.putString(CompassItem.TAG_LODESTONE_DIMENSION, dimension);
		compassTag.putBoolean(CompassItem.TAG_LODESTONE_TRACKED, true);
		compassTag.put(CompassItem.TAG_LODESTONE_POS, NbtUtils.writeBlockPos(blockPos));
		
		ItemStack compass = new ItemStack(Items.COMPASS, 1);
		compass.setTag((CompoundTag)compass.getTag().put("tag", compassTag));
		
		return compass;
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
	
	public ItemStack getCompass() {
		return this.artifactCompass;
	}
	
	public void setRetrieved() {
		this.artifactRetrieved = true;
	}
	
	public Boolean getRetrieved() { 
		return this.artifactRetrieved;
	}
}
