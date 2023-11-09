package com.fexl.deckedout.cards;

import net.minecraft.world.effect.MobEffects;

public class LootAndScoot extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	public LootAndScoot(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addTreasure(7);
		//Add 15 seconds of Speed II
		effects.addSpecificEffect(doPlayer, MobEffects.MOVEMENT_SPEED, 15, 2);
	}
}
