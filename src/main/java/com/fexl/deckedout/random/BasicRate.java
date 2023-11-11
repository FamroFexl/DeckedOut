/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.random;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.minecraft.resources.ResourceLocation;

/**
 * Defines standard game rates
 */
public class BasicRate extends PercentageRate {
	public static BasicRate STANDARD_TREASURE_RATE = new BasicRate(new LinkedHashMap<Integer, Object>() {
		{ 
			put(60, new ResourceLocation("coin"));
			put(25, new ResourceLocation("key"));
			put(15, new ResourceLocation("crown"));
		}
	});
	
	public BasicRate(LinkedHashMap<Integer, Object> rates) {
		super(rates);
	}
	
	public BasicRate modifyRate(BasicRate rate, int[] percentage) {
		ArrayList<Object> keySet = new ArrayList<Object>(rate.rates.keySet());
		
		LinkedHashMap<Integer, Object> returnRate = new LinkedHashMap<Integer, Object>();
		
		for(int i = 0; i < percentage.length; i++) {
			//The returnRate cannot have more components than the modified rate
			if(i > keySet.size()) {
				break;
			}
			
			returnRate.put(percentage[i], keySet.get(i));
		}
		
		return new BasicRate(returnRate);
	}

}
