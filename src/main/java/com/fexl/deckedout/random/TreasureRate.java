/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.random;

import java.util.LinkedHashMap;

import net.minecraft.resources.ResourceLocation;

/**
 * Defines standard treasure rates
 */
public class TreasureRate extends PercentageRate {

	public static TreasureRate STANDARD_RATE = new TreasureRate(new LinkedHashMap<Integer, Object>() {
		{ 
			put(60, new ResourceLocation("coin"));
			put(25, new ResourceLocation("key"));
			put(15, new ResourceLocation("crown"));
		}
	});
	
	public TreasureRate(LinkedHashMap<Integer, Object> rates) {
		super(rates);
	}

}
