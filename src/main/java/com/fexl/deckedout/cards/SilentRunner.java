package com.fexl.deckedout.cards;

import java.util.Random;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;

public class SilentRunner extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 1;
	public final boolean preGame = true;
	
	public SilentRunner(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Permanent once played
		events.CLANK_EVENT.register((type) -> {
			//If the remaining time on the player's speed buff is >= 15, block half a clank
			if(doPlayer.getEffect(MobEffects.MOVEMENT_SPEED).getDuration() >= 15 && (new Random()).nextInt(2) == 1) {
				//Block the clank
				return InteractionResult.FAIL;
			}
			
			return InteractionResult.PASS;
		});
	}
}
