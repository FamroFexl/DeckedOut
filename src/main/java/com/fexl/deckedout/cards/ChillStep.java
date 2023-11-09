package com.fexl.deckedout.cards;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;

public class ChillStep extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	private static int count = 0;
	
	public ChillStep(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		count = 0;
		//Future sneak cards generate 2 Frost Embers each
		events.CARD_EVENT.register((card, type) -> {
			if(card == Cards.CARDS.get(new ResourceLocation("sneak")) && count <= 3) {
				ember.addEmbers(2);
				count += 1;
			}
			return InteractionResult.PASS;
		});
	}
}