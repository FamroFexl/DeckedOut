package com.fexl.deckedout.cards;

public class Haste extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	public Haste(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Cards are drawn 10% faster for the remainder of the run
		
		//When creating card dispense system
	}
}
