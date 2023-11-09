package com.fexl.deckedout.cards;

public class PorkchopPower extends Card {
	public final Rarity rarity = Rarity.SPECIAL;
	public final int maxCount = 1; //Unknown (probably 1)
	public final boolean ethereal = true;
	public final boolean preGame = true;
	
	public PorkchopPower(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Give the player two porkchops at the start of the run
	}
}
