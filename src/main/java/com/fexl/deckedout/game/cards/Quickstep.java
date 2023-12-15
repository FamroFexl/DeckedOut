package com.fexl.deckedout.game.cards;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;

public class Quickstep extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 0;
	
	private boolean cardPlayed;
	
	public Quickstep(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addClankBlock(2);
		//Add 15 seconds of Speed II
		effects.addSpecificEffect(doPlayer, MobEffects.MOVEMENT_SPEED, 15, 2);
		
		cardPlayed = false;
		
		//Card replay
		doPlayer.getDeck().getNextCard();
		events.CARD_EVENT.register((card, type) -> {
			//Only plays if it hasn't already activated and the card isn't ethereal
			if(!(cardPlayed && card.ethereal)) {
				cardPlayed = true;
				//Reinsert the card for replay
				doPlayer.getDeck().addCard(card);
			}
			
			return InteractionResult.PASS;
		});
	}
}
