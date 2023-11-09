package com.fexl.deckedout.treasure;

import com.fexl.deckedout.SpawnType;
import com.fexl.deckedout.event.Events;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class Coin extends Treasure {
	public Coin(Events events, Minecraft minecraft) {
		super(events, minecraft);
	}
	
	public SpawnType.Treasure treasureType = SpawnType.Treasure.COIN;
	public ItemStack item; //set item
	public ResourceLocation sound; //set sound
	
}
