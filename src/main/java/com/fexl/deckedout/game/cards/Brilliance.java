package com.fexl.deckedout.game.cards;

import com.fexl.deckedout.game.event.ScheduleEvent;

public class Brilliance extends Card {
	public final Rarity rarity = Rarity.RARE;
	public final int maxCount = 3;
	
	public Brilliance(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		//Quickdraw once every 10 seconds twice
		for(int i = 10; i <= 20; i += 10) {
			schedEvent.addEvent(new GetNextCard(), ScheduleEvent.secToTick(i));
		}
	}
		
	class GetNextCard implements Runnable {

		@Override
		public void run() {
			doPlayer.getDeck().getNextCard();
		}
		
	}
}
