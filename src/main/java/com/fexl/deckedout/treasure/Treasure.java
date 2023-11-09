package com.fexl.deckedout.treasure;

import com.fexl.deckedout.SpawnType;
import com.fexl.deckedout.event.Events;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class Treasure {
	public ItemStack item = null; //Set default item
	public SpawnType.Treasure treasureType; //Set default type
	public ResourceLocation sound; //Set default sound
	
	//What treasure is currently queued for release
	private int queuedTreasure = 0;
	
	public Minecraft minecraft;
	public Events events;
	
	public Treasure(Events events, Minecraft minecraft) {
		this.events = events;
	}
	
	public final void addTreasure(int amount) {
		queuedTreasure += amount;
	}
	
	public final void removeTreasure(int amount) {
		if(queuedTreasure - amount < 0) {
			queuedTreasure = 0;
			return;
		}
		queuedTreasure -= amount;
	}
	
	public void spawn(BlockPos blockPos) {
		InteractionResult result = events.TREASURE_EVENT.invoker().interact(blockPos, treasureType);
		if(!(result == InteractionResult.FAIL)) {
			new ItemEntity(minecraft.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), item);
			//play sound
		}
	}
	
	public final void resetTreasure() {
		queuedTreasure = 0;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
}
