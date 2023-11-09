/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.zones;

import net.minecraft.core.BlockPos;

/**
 * Allows multiple zone types to be grouped together for simplicity
 */
public class SharedZone extends DOZone {

	public SharedZone(BlockPos blockPos1, BlockPos blockPos2) {
		super(blockPos1, blockPos2);
	}

}
