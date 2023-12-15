package com.fexl.deckedout.game.cards;

import com.fexl.deckedout.game.event.EventTypes;

import net.minecraft.world.InteractionResult;

public class NimbleLooting extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	private boolean complete = false;

	public NimbleLooting(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addClankBlock(1);
		this.addTreasure(2);
		
		//For every clank blocked, add two treasure until clank increases
		events.CLANK_EVENT.register((type) -> {
			if(!(type == EventTypes.Clank.ADD_CLANK) && complete == false) {
				this.addTreasure(2);
				return InteractionResult.PASS;
			}
			
			//Exit card effects
			complete = true;
			return InteractionResult.PASS;
		});
	}
}
