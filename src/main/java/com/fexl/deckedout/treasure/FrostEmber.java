package com.fexl.deckedout.treasure;

import com.fexl.deckedout.event.Events;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class FrostEmber extends Treasure {
	public ItemStack item; //set item
	public ResourceLocation sound; //set sound
	public int queuedEmbers;
	
	public FrostEmber(Events events, Minecraft minecraft) {
		super(events, minecraft);
	}
	
	public void addEmbers(int embers) {
		queuedEmbers += embers;
	}
}
