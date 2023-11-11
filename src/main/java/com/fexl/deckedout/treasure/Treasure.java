package com.fexl.deckedout.treasure;

import com.fexl.deckedout.event.Events;
import com.fexl.deckedout.event.EventTypes;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class Treasure {
	public ItemStack item = null; //Set default item
	public ResourceLocation sound; //Set default sound
	public int queuedTreasure;
	
	public Minecraft minecraft;
	public Events events;
	
	public Treasure(Events events, Minecraft minecraft) {
		this.events = events;
	}
	
	public void addTreasure(int treasure) {
		queuedTreasure += treasure;
	}
	
	public void spawn(BlockPos blockPos) {
		InteractionResult result = events.TREASURE_EVENT.invoker().interact(blockPos);
		if(!(result == InteractionResult.FAIL)) {
			new ItemEntity(minecraft.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), item);
			//play sound
		}
	}
	
	public ItemStack getItem() {
		return item;
	}
	
}
