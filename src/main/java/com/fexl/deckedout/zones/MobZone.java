/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.zones;

import net.minecraft.core.BlockPos;

/**
 * Defines where dangerous mobs such as Ravagers and Wardens can spawn
 */
public class MobZone extends DOZone {

	public MobZone(BlockPos blockPos1, BlockPos blockPos2) {
		super(blockPos1, blockPos2);
	}

}
