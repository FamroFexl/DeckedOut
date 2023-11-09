/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.random;

import java.util.LinkedHashMap;

import com.fexl.deckedout.treasure.Treasures;

import net.minecraft.resources.ResourceLocation;

/**
 * Defines standard treasure rates
 */
public class TreasureRate extends PercentageRate {

	public static TreasureRate STANDARD_RATE = new TreasureRate(new LinkedHashMap<Integer, Object>() {
		{ 
			put(60, Treasures.TREASURES.get(new ResourceLocation("coin")));
			put(25, Treasures.TREASURES.get(new ResourceLocation("key")));
			put(15, Treasures.TREASURES.get(new ResourceLocation("crown")));
		}
	});
	
	public TreasureRate(LinkedHashMap<Integer, Object> rates) {
		super(rates);
	}

}
