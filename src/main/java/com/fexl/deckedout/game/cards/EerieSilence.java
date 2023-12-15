package com.fexl.deckedout.game.cards;

import net.minecraft.world.InteractionResult;

public class EerieSilence extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	private boolean skipNextCard = true;
	
	public EerieSilence(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addClankBlock(8);
		this.addHazardBlock(2);
		
		//Skip next played card
		events.CARD_EVENT.register((card, type) -> {
			if(skipNextCard) {
				skipNextCard = false;
				return InteractionResult.FAIL;
			}
			return InteractionResult.PASS;
		});
	}
}
