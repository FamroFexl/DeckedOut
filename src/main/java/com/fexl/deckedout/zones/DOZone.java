package com.fexl.deckedout.zones;

import com.fexl.deckedout.random.PercentageRate;

import net.minecraft.core.BlockPos;

public class DOZone extends Zone {
	//Decked out level the zone resides on
	public int level;
	
	public PercentageRate rate = null;
	
	public DOZone(BlockPos blockPos1, BlockPos blockPos2) {
		super(blockPos1, blockPos2);
	}
	
	public DOZone(BlockPos blockPos) {
		super(blockPos);
	}
	
	public void setRate(PercentageRate rate) {
		this.rate = rate;
	}
	
	public PercentageRate getRate() {
		return this.rate;
	}
	
	public int getLevel() { 
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

}
