/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import com.fexl.deckedout.event.Events;
import com.fexl.deckedout.event.EventTypes;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;

/**
 * Activates obstacles in the dungeon that slow down the {@link com.fexl.deckedout.User}s or redirect their path.
 */
public class Hazard {
	
	private int hazardBlock;
	
	private final int maxHazardBlock = 10;
	
	public Events events;
	
	public Hazard(Events events) {
		this.events = events;
	}
	
	/**
	 * Adds an amount of hazard if there is no {@link com.fexl.deckedout.Hazard#hazardBlock} left.
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
	 * Used to block {@link com.fexl.deckedout.Hazard} when it occurs.
	 */
	public void addHazardBlock(int amount) {
		InteractionResult result = events.HAZARD_EVENT.invoker().interact(EventTypes.Hazard.ADD_HAZARD_BLOCK);
		if(!(result == InteractionResult.FAIL)) {
			if(hazardBlock + amount > maxHazardBlock) {
				hazardBlock = maxHazardBlock;
				return;
			}
			
			hazardBlock += amount;
		}
	}
}
