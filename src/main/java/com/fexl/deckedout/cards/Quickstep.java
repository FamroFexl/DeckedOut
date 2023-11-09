package com.fexl.deckedout.cards;

import net.minecraft.world.effect.MobEffects;

public class Quickstep extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 0;
	
	public Quickstep(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addClankBlock(2);
		//Add 15 seconds of Speed II
		effects.addSpecificEffect(doPlayer, MobEffects.MOVEMENT_SPEED, 15, 2);
		//Quickdraw
		doPlayer.getDeck().getNextCard();
	}
}
