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
 * Activates obstacles in the dungeon that slow down the {@link com.fexl.deckedout.game.User}s or redirect their path.
 */
public class Hazard {
	
	private int hazardBlock;
	
	private Events events;
	private Dungeon dungeon;
	
	public Hazard(Events events, Dungeon dungeon) {
		this.events = events;
	}
	
	/**
	 * Adds an amount of hazard if there is no {@link com.fexl.deckedout.game.Hazard#hazardBlock} left.
	 */
	public void addHazard(int amount) {
		InteractionResult result = events.HAZARD_EVENT.invoker().interact(EventTypes.Hazard.ADD_HAZARD);
		if(!(result == InteractionResult.FAIL)) {
			//Hazard Block
			if(hazardBlock > 0) { 
				hazardBlock--;
				return;
			}
			
			//Randomly determine if and what hazard will trigger for the amount given
		}
	}
	
	/**
	 * Used to block {@link com.fexl.deckedout.game.Hazard} when it occurs.
	 */
	public void addHazardBlock(int amount) {
		InteractionResult result = events.HAZARD_EVENT.invoker().interact(EventTypes.Hazard.ADD_HAZARD_BLOCK);
		if(!(result == InteractionResult.FAIL)) {
			if(hazardBlock + amount > dungeon.getMaxHazardBlock()) {
				hazardBlock = dungeon.getMaxHazardBlock();
				return;
			}
			
			hazardBlock += amount;
		}
	}
}
