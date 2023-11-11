package com.fexl.deckedout.cards;

import com.fexl.deckedout.event.EventTypes;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;

public class FuzzyBunnySlippers extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 1;
	public final boolean preGame = true;
	
	public FuzzyBunnySlippers(CardBuilder builder) {
		super(builder);
	}
	
	public int maxLevel = 1;
	
	@Override
	public void actions() {
		//WORK ON get official artifactObtained signal
		boolean artifactObtained = true;
		
		//Cards won't grant speed if the artifact has been obtained
		events.EFFECT_EVENT.register((effect) -> {
			if(effect == MobEffects.MOVEMENT_SPEED && artifactObtained) {
				return InteractionResult.FAIL;
			}
			return InteractionResult.PASS;
		});
		
		//Every staircase unlocked blocks 4 Clank.
		events.LEVEL_EVENT.register((level, type) -> {
			//Only activate once per level
			if(type.equals(EventTypes.Level.PLAYER_LEVEL_LOWER)&& level > maxLevel) {
				maxLevel += 1;
				clank.addClankBlock(4);
			}
			return InteractionResult.PASS;
		});
	}
}
