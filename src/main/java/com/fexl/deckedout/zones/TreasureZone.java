/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.zones;

import java.util.ArrayList;

import com.fexl.deckedout.random.PercentageRate;
import com.fexl.deckedout.random.TreasureRate;
import com.fexl.deckedout.treasure.Treasure;

import net.minecraft.core.BlockPos;

/**
 * Used to spawn treasure in the dungeon on a {@link com.fexl.deckedout.random.PercentageRate} basis
 */
public class TreasureZone extends DOZone {
		
	public TreasureZone(BlockPos blockPos1, BlockPos blockPos2, PercentageRate rate, int level) {
		super(blockPos1, blockPos2);
		this.rate = rate;
		this.level = level;
	}
	
	public TreasureZone(BlockPos blockPos1, BlockPos blockPos2, int level) {
		super(blockPos1, blockPos2);
		this.rate = TreasureRate.STANDARD_RATE;
		this.level = level;
	}
	
	public TreasureZone(BlockPos blockPos, int level) {
		this(blockPos, blockPos, level);
	}
	
	public void spawnTreasure() {
		//If the rate is uninitialized
		if(this.rate == null) {
			this.rate = TreasureRate.STANDARD_RATE;
		}
		
		//Spawn a rate-weighted treasure on a random block
		((Treasure) this.rate.getRate()).spawn(this.getRandValidZoneBlock());
		
	}

}
