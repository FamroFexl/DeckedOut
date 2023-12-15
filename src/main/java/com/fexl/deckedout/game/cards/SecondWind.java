package com.fexl.deckedout.game.cards;

import net.minecraft.world.effect.MobEffects;

public class SecondWind extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	public SecondWind(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Add 15 seconds of Speed II
		effects.addSpecificEffect(doPlayer, MobEffects.MOVEMENT_SPEED, 15, 2);
		
		//Add 15 seconds of Regeneration II
		effects.addSpecificEffect(doPlayer, MobEffects.REGENERATION, 15, 2);
	}
}
