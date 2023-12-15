package com.fexl.deckedout.game.cards;

public class AdrenalineRush extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	public AdrenalineRush(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addHazard(1);
		
		//For the next 20 seconds, each heart beat generates a treasure drop
	}
}
