package com.fexl.deckedout.game.cards;

import java.util.Random;

import com.fexl.deckedout.game.event.EventTypes;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;

public class SuitUp extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 1;
	public final boolean preGame = true;
	
	public SuitUp(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Permanent
		//Spawn Diamond chestplate and leggings at entrance
		//Add Resistance II for the entire run
		effects.addSpecificEffect(doPlayer, MobEffects.DAMAGE_RESISTANCE, 2000, 2);
		
		//Each clank has a 25% chance of adding another clank for the entire run
		events.CLANK_EVENT.register(type -> {
			if(type == EventTypes.Clank.ADD_CLANK) {
				//25% chance
				if((new Random()).nextInt(4) == 0) {
					clank.addClank(1);
				}
			}
			return InteractionResult.PASS;
		});
	}
}
