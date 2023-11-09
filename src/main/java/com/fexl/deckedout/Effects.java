package com.fexl.deckedout;

import java.util.ArrayList;

import com.fexl.deckedout.event.Events;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * Used to add {@link net.minecraft.world.effect.MobEffect}s to dungeon characters such as Mobs and {@link com.fexl.deckedout.User}s.
 */
public class Effects {
	private ArrayList<LivingEntity> targets;
	public Events events;
	
	public Effects(Events events) {
		this.events = events;
	}
	
	/**
	 * Affects a group of characters specifically, such as a group of Ravagers on {@link com.fexl.deckedout.DOLevel} 1, or some {@link com.fexl.deckedout.Lackey}s on {@link com.fexl.deckedout.DOLevel} 2.
	 */
	public void addGroupEffect(MobEffect mobEffect, int duration, int amplifier) {
		//effect, duration, amplifier, ambient, visible, showIcon, hiddenEffect
		for(LivingEntity entity : targets) {
			this.addSpecificEffect(entity, mobEffect, duration, amplifier);
			
		}
	}
	
	/**
	 * Targets an individual entity to receive a specific effect. This will most often be used to target the {@link com.fexl.deckedout.DOPlayer}
	 */
	public void addSpecificEffect(LivingEntity entity, MobEffect mobEffect, int duration, int amplifier) {
		InteractionResult result = events.EFFECT_EVENT.invoker().interact(mobEffect, SpawnType.Effect.GENERAL);
		if(!(result == InteractionResult.FAIL)) {
			entity.addEffect(new MobEffectInstance(mobEffect, duration, amplifier));
		}
	}
	
	public void setTargets(ArrayList<LivingEntity> targets) {
		this.targets = targets;
	}
}
