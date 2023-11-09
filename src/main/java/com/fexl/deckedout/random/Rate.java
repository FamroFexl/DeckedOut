package com.fexl.deckedout.random;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * A weighted chance system that returns an object when queried, similar to loot tables
 */
public class Rate {
	public LinkedHashMap<Integer, Object> rates = new LinkedHashMap<Integer, Object>();
	
	public Rate(LinkedHashMap<Integer, Object> rates) {
		this.rates = rates;
	}
	
	public void setRate(LinkedHashMap<Integer, Object> rates) {
		this.rates = rates;
	}
	
	/**
	 * Returns the {@link java.lang.Object} associated with a rate.
	 */
	public Object getRate() {
		int total = 0;
		
		for(Entry<Integer, Object> entry : rates.entrySet()) {
			total += entry.getKey();
		}
		
		//Calculate a random rate
		int randInt = (new Random()).nextInt(total) + 1;
		
		int totalInt = 0;
		
		Entry<Integer, Object> returnEntry = null;
		
		//Based off the number, get the position it falls into in the rates list
		for(Entry<Integer, Object> entry : rates.entrySet()) {
			totalInt += entry.getKey();
			if(randInt <= totalInt) {
				returnEntry = entry;
				break;
			}
		}
		
		//This should never execute
		return returnEntry.getValue();
		
	}
}
