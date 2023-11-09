/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.zones;

import java.util.ArrayList;
import java.util.Random;

import com.fexl.deckedout.Clank;
import com.fexl.deckedout.DOPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

/**
 * Detects {@link com.fexl.deckedout.DOPlayer} and {@link com.fexl.deckedout.Lackey} movements and increments the {@link com.fexl.deckedout.Clank} based on a {@link com.fexl.deckedout.random.PercentageRate} basis.
 */
public class ClankZone extends DOZone {
	//Valid spawn blocks
	private ArrayList<Block> validBlocks = new ArrayList<Block>();
		
	//Valid holding spawn blocks
	private ArrayList<Block> validBaseBlocks = new ArrayList<Block>();
		
	private boolean zoneActive = false;
	
	private long lastTime = 0;
	
	//Zone ping rate in seconds
	private int pingRate = 2;
	
	private int tickRate = 20;
	
	private Clank clank;
	private Minecraft minecraft;
	
	public ClankZone(BlockPos blockPos1, BlockPos blockPos2, Clank clank, Minecraft minecraft, int pingRate) {
		super(blockPos1, blockPos2);
		this.clank = clank;
		this.pingRate = pingRate;
	}
	
	public void clankTrigger() {
		//Every game second (20 ticks), iterate through the clank zones and check if the player's position is in one.
		//If they are, activate this method
		
		//Player must be outside of the zone for ~pingRate zone pings in order for it to reactivate
		if(minecraft.level.getGameTime() >= lastTime + pingRate*tickRate) {
			//Ping lapse
			zoneActive = false;
		}
		
		lastTime = minecraft.level.getGameTime();
				
		//If the player enters the clank zone, every time this method gets activated, randomly determine if the clank goes up
		//Clank likelihood is determined by the number of valid blocks in the zone
		if((new Random()).nextInt(this.getValidZoneBlocks().size()) == 0 && !zoneActive) {
			clank.addClank(1);
			//If the clank goes up, set zoneActive to true until the player leaves the zone, disabling clank increase
			zoneActive = true;
			//Effectively, even if a player spends a long time in a zone, they can only activate that zone once as long as they stay within it. 
		}
	}
}
