package com.fexl.deckedout.cards;

public class Deepfrost extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	public Deepfrost(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Every staircase unlocked adds 6 Frost Embers up to level 4 (max 18)
		if(doPlayer.getLevel() >= 3) {
			this.addEmbers(18);
		}
		else {
			this.addEmbers(doPlayer.getLevel()*6);
		}
	}
}
