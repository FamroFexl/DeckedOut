package com.fexl.deckedout.treasure;

import com.fexl.deckedout.SpawnType;
import com.fexl.deckedout.event.Events;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Artifact extends Treasure {
	public Artifact(Events events, Minecraft minecraft) {
		super(events, minecraft);
	}

	public SpawnType.Treasure treasureType = SpawnType.Treasure.ARTIFACT;
	public ItemStack item; //set item
	public ResourceLocation sound; //set sound
	
	private static boolean artifactObtained = false;
	
	public Item createCompass() {
		//Generate using Difficulty.getDifficulty()
		//use Rate class to generate likeliness of spawning at back of dungeon
		return null;
	}
	
	public static boolean getArtifactObtained() {
		return artifactObtained;
	}
	
	@Override
	public void spawn(BlockPos blockPos) {
		InteractionResult result = events.TREASURE_EVENT.invoker().interact(blockPos, treasureType);
		artifactObtained = true;
		if(!(result == InteractionResult.FAIL)) {
			//Randomly select what the artifact will be based on the zone Rate, difficulty, and compass type
			
			
			new ItemEntity(Minecraft.getInstance().level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), item);
			//set sound
		}
		//use Rate class to generate likelihood based on zone Rate
	}
}
