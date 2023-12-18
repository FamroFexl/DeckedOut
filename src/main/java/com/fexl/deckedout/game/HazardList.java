package com.fexl.deckedout.game;

import java.util.ArrayList;
import java.util.Random;

import com.fexl.deckedout.game.random.Rate;

/**
 * Defines a hazard list, which can contain multiple hazards that can be selectively triggered
 */
public class HazardList {
	private Rate<String> rate;
	private int min = 0;
	private int max = 1;
	
	public HazardList(Rate<String> rate, int min, int max) {
		this.rate = rate;
		this.min = min;
		this.max = max;
	}
	
	public ArrayList<String> getHazards() {
		ArrayList<String> commands = new ArrayList<String>();
		
		//Copy the rate for processing
		Rate<String> tmpRate = this.rate;
		
		//Random number of hazards to return
		int numHazards = (new Random()).nextInt(min, max);
		for(int i = 0; i < numHazards; i++) {
			//Get a hazard
			String chance = this.rate.chance();
			
			//Remove it from the rate
			tmpRate.rate.remove(chance);
			
			//Add it to the rate return
			commands.add(chance);
		}
		
		return commands;
	}
	
	public Rate<String> getRate() {
		return this.rate;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	
}
