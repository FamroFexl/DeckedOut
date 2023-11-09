/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.random;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Requires input rates to add up to an integer value of 100. A rate that doesn't add up to 100 returns null for the remaining percentage.
 */
public class PercentageRate extends Rate {

	public PercentageRate(LinkedHashMap<Integer, Object> rates) {
		super(rates);
		this.rates = this.ratesToPercentage(rates);
	}
	
	@Override
	public void setRate(LinkedHashMap<Integer, Object> rates) {
		this.rates = this.ratesToPercentage(rates);
	}
	
	//Transform integer rates into percentage rates
	private LinkedHashMap<Integer, Object> ratesToPercentage(LinkedHashMap<Integer, Object> rates) {
		LinkedHashMap<Integer, Object> finalRate = new LinkedHashMap<Integer, Object>();
		
		int total = 0;
		
		//Summarize the total of all the keys
		for(Entry<Integer, Object> entry : rates.entrySet()) {
			total += entry.getKey();
		}
		
		//Total cannot be more than 100%
		if(total > 100) {
			return null;
		}
		
		//Turn the keys into a proportional percentage of their total
		for(Entry<Integer, Object> entry : rates.entrySet()) {
			finalRate.put((entry.getKey()/total)*100, entry.getValue());
		}
		
		if(total < 100) {
			//If the total percentage is less than 100%, assume the creator wants the remaining percentage to do nothing
			finalRate.put(100-total, null);
		}

		return finalRate;
	}
}
