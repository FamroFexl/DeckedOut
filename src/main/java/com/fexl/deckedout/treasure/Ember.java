package com.fexl.deckedout.treasure;

import com.fexl.deckedout.SpawnType;
import com.fexl.deckedout.event.Events;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class Ember extends Treasure {
	
	public Ember(Events events, Minecraft minecraft) {
		super(events, minecraft);
	}

	private static int queuedEmbers = 0;
	
	public final void addEmbers(int amount) {
		queuedEmbers += amount;
	}
	
	public final void removeEmbers(int amount) {
		if(queuedEmbers - amount < 0) {
			queuedEmbers = 0;
			return;
		}
		queuedEmbers -= amount;
	}
	
	public SpawnType.Treasure treasureType = SpawnType.Treasure.EMBER;
	public ItemStack item; //set item
	public ResourceLocation sound; //set sound
	
	public void resetEmbers() {
		queuedEmbers = 0;
	}
}
