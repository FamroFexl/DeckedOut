/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.zones;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

/**
 * Defines where dangerous mobs such as Ravagers and Wardens can spawn
 */
public class MobZone extends DOZone {
	public int count;
	
	public MobZone(BlockPos blockPos1, BlockPos blockPos2) {
		super(blockPos1, blockPos2);
	}
	
	public void spawnMobs() {
		CompoundTag mobID = new CompoundTag();
		mobID.putString("id", (String) this.rate.getRate());
		
		//*Mob is selected by ResourceLocation
		//LivingEntity(EntityType.byString(mob_id), minecraft.level)
		//LivingEntity.setPos(x, y, z);
	}

}
