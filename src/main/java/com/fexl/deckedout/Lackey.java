/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * A {@link com.fexl.deckedout.DOPlayer} helper who generates {@link com.fexl.deckedout.Clank} in exchange for picking up {@link com.fexl.deckedout.treasure.Treasure} drops, luring Mobs, and defending the {@link com.fexl.deckedout.DOPlayer}.
 */
public class Lackey extends User {

	public Lackey(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
		super(level, blockPos, f, gameProfile);
	}

	

}
