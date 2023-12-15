package com.fexl.deckedout.game.cards;

public class EyesOnThePrize extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	public EyesOnThePrize(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Grants extra avaliable purchase option at frost ember shop and game end
		this.addClank(2);
		this.addHazard(3);
	}
}
