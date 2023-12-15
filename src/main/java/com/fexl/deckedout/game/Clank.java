/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game;

import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.event.EventTypes;
import com.fexl.deckedout.game.event.Events;

import net.minecraft.world.InteractionResult;

/**
 * Defines the dungeon's stability. The less stable the dungeon is, the greater chance the {@link com.fexl.deckedout.game.User}s won't make it out.
 */
public class Clank {
	private int clank;
	private int clankBlock;
	
	private Events events;
	private Dungeon dungeon;
	
	public Clank(Events events, Dungeon dungeon) {
		this.events = events;
		this.dungeon = dungeon;
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
			if(clank + amount > dungeon.getMaxClank()) {
				clank = dungeon.getMaxClank();
				return;
			}
			
			//Add clank
			clank += amount;
		}
	}
	
	public void addClankBlock(int amount) {
		InteractionResult result = events.CLANK_EVENT.invoker().interact(EventTypes.Clank.ADD_CLANK_BLOCK);
		if(!(result == InteractionResult.FAIL)) {
			if(clankBlock + amount > dungeon.getMaxClankBlock()) {
				clankBlock = dungeon.getMaxClankBlock();
				return;
			}
			
			clankBlock += amount;
		}
	}
	
	public int getClank() {
		return clank;
	}
}
