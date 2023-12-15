package com.fexl.deckedout.game.cards;

import net.minecraft.world.effect.MobEffects;

public class BoundingStrides extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	public BoundingStrides(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addHazardBlock(2);
		//Add 2 minutes of Jump Boost II
		effects.addSpecificEffect(doPlayer, MobEffects.JUMP, 120, 2);
	}
}
