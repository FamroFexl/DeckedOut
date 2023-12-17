/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game;

import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.event.EventTypes;
import com.fexl.deckedout.game.event.Events;
import com.fexl.deckedout.game.zones.DOZone;

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
	
	public boolean checkClank() {
		//Every game second (20 ticks), iterate through the clank zones and check if the player's position is in one.
		//If they are, activate this method
		
		long tickRate = 20;
		long pingRate = 2;
		
		for(DOZone zone : dungeon.clankZones) {
			//If the player enters the clank zone, every time this method gets activated, randomly determine if the clank goes up
			//Clank likelihood is determined by the number of valid blocks in the zone
			/**
			if((new Random()).nextInt(zone.getValidZoneBlocks().size()) == 0 && !zoneActive) {
				clank.addClank(1);
				//If the clank goes up, set zoneActive to true until the player leaves the zone, disabling clank increase
				zoneActive = true;
				//Effectively, even if a player spends a long time in a zone, they can only activate that zone once as long as they stay within it.
				
				return true;
			}**/
		}
		return false;
	}
	
	public int getClank() {
		return clank;
	}
}
