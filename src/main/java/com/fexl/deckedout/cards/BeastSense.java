package com.fexl.deckedout.cards;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;

public class BeastSense extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	private int playedCards = 0;
	private boolean active = false;
	
	public BeastSense(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addClank(1);
		
		active = true;
		//Make all the beasts glow for the next three cards played
		events.CARD_EVENT.register((card, type) -> {
			if(!active) {
				return InteractionResult.PASS;
			}
			
			//CHANGE TARGETS TO BEASTS
			effects.setTargets(null);
			effects.addGroupEffect(MobEffects.GLOWING, 5, 1);
			
			if(playedCards == 3) {
				active = false;
				playedCards = 0;
				return InteractionResult.PASS;
			}
			playedCards += 1;
			
			return InteractionResult.PASS;
		});
	}
}
