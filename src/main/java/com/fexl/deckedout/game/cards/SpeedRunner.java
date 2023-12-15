package com.fexl.deckedout.game.cards;

import net.minecraft.world.InteractionResult;

public class SpeedRunner extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 1;
	public final boolean preGame = true;
	
	private boolean embersAdded = false;
	
	public SpeedRunner(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Add 8 Frost Embers when the player goes to dungeon level 3
		events.LEVEL_EVENT.register((level, type) -> {
			if(level == 3 && !embersAdded) {
				this.addEmbers(8);
				embersAdded = true;
			}
			return InteractionResult.PASS;
		});
	}
}
