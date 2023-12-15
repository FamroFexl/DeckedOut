package com.fexl.deckedout.game.zones;

import com.fexl.deckedout.game.random.Rate;

import net.minecraft.core.BlockPos;

public class DOZone extends Zone  {
	//Decked out level the zone resides on
	public int level;
	
	public String name;
	
	public Rate<String> rate;
	
	public DOZone(BlockPos blockPos1, BlockPos blockPos2) {
		super(blockPos1, blockPos2);
	}
	
	public DOZone(BlockPos blockPos) {
		super(blockPos);
	}
	
	public void setRate(Rate<String> rate) {
		this.rate = rate;
	}
	
	public Rate<String> getRate() {
		return this.rate;
	}
	
	public int getLevel() { 
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
