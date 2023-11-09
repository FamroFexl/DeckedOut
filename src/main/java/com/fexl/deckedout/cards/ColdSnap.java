package com.fexl.deckedout.cards;

import net.minecraft.world.InteractionResult;

public class ColdSnap extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	private static int cardsPlayed = 0;
	private static boolean active = true;
	
	public ColdSnap(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Generate 1 hazard each for the next 3 cards played
		events.CARD_EVENT.register((card, type) -> {
			//Already finished
			if(!active) {
				return InteractionResult.PASS;
			}
			
			//Cards complete
			if(cardsPlayed == 3) {
				active = false;
			}
			
			cardsPlayed += 1;
			hazard.addHazard(1);
			return InteractionResult.PASS;
		});
		
		//Frost Ember drops double (for whole game? for current queuedEmbers?)
	}
}
