package com.fexl.deckedout.cards;

public class Swagger extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	public Swagger(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addTreasure(10);
		this.addEmbers(10);
		
		//Add two stumble cards into the deck
		doPlayer.getDeck().addStumbleCards(2);
	}
}
