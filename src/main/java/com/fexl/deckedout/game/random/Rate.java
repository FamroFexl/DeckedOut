package com.fexl.deckedout.game.random;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * A weighted chance system that returns an object when queried, similar to loot tables
 * @param <T>
 */
public class Rate<T> {
	public LinkedHashMap<T, Integer> rate = new LinkedHashMap<T, Integer>();
	
	public Rate(LinkedHashMap<T, Integer> rates) {
		this.rate = rates;
	}
	
	public void setRate(LinkedHashMap<T, Integer> rates) {
		this.rate = rates;
	}
	
	public LinkedHashMap<T, Integer> getRate() {
		return rate;
	}
	
	/**
	 * Returns the {@link java.lang.Object} associated with a rate.
	 */
	public T chance() {
		int total = 0;
		
		for(Entry<T, Integer> entry : rate.entrySet()) {
			total += entry.getValue();
		}
		
		//Calculate a random rate
		int randInt = (new Random()).nextInt(total) + 1;
		
		int totalInt = 0;
		
		Entry<T, Integer> returnEntry = null;
		
		//Based off the number, get the position it falls into in the rates list
		for(Entry<T, Integer> entry : rate.entrySet()) {
			totalInt += entry.getValue();
			if(randInt <= totalInt) {
				returnEntry = entry;
				break;
			}
		}
		
		//This should never execute
		return returnEntry.getKey();
		
	}
}
