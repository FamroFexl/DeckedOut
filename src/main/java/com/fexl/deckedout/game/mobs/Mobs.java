package com.fexl.deckedout.game.mobs;

import com.fexl.deckedout.game.event.Events;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;

public class Mobs {
	private Events events;
	
	public Mobs(Events events) {
		this.events = events;
	}

	public boolean spawn(BlockPos blockPos, CompoundTag mobTag) {
		InteractionResult result = events.MOB_EVENT.invoker().interact(blockPos, mobTag);
		if(!(result == InteractionResult.FAIL)) {
			
			return true;
		}
		return false;
	}
}
