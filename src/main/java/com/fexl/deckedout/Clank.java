/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import com.fexl.deckedout.event.Events;
import com.fexl.deckedout.event.EventTypes;

import net.minecraft.world.InteractionResult;

/**
 * Defines the dungeon's stability. The less stable the dungeon is, the greater chance the {@link com.fexl.deckedout.User}s won't make it out.
 */
public class Clank {

	private int clank;
	private final int maxClank = 64;
	
	private int clankBlock;
	private final int maxClankBlock = 10;
	
	public Events events;
	
	public Clank(Events events) {
		this.events = events;
	}
	
	public void addClank(int amount) {
		InteractionResult result = events.CLANK_EVENT.invoker().interact(EventTypes.Clank.ADD_CLANK);
		if(!(result == InteractionResult.FAIL)) {
			//Clank Block
			if(clankBlock > 0) {
				clankBlock--;
				return;
			}
			
			//Clank can't go above max
			if(clank + amount > maxClank) {
				clank = maxClank;
				return;
			}
			
			//Add clank
			clank += amount;
		}
	}
	
	public void addClankBlock(int amount) {
		InteractionResult result = events.CLANK_EVENT.invoker().interact(EventTypes.Clank.ADD_CLANK_BLOCK);
		if(!(result == InteractionResult.FAIL)) {
			if(clankBlock + amount > maxClankBlock) {
				clankBlock = maxClankBlock;
				return;
			}
			
			clankBlock += amount;
		}
	}
	
	public int getClank() {
		return clank;
	}
}
