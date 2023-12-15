package com.fexl.deckedout.game.cards;

import net.minecraft.world.effect.MobEffects;

public class Sprint extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	public Sprint(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Add 60 seconds of Speed II
		effects.addSpecificEffect(doPlayer, MobEffects.MOVEMENT_SPEED, 60, 2);
	}
}
