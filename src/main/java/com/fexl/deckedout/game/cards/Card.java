/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game.cards;

import com.fexl.deckedout.game.Clank;
import com.fexl.deckedout.game.DOPlayer;
import com.fexl.deckedout.game.Effects;
import com.fexl.deckedout.game.Hazard;
import com.fexl.deckedout.game.dungeon.GameDungeon;
import com.fexl.deckedout.game.event.EventTypes;
import com.fexl.deckedout.game.event.Events;
import com.fexl.deckedout.game.event.ScheduleEvent;

import net.minecraft.world.InteractionResult;

/**
 * Defines playable cards that are used via a {@link com.fexl.deckedout.game.Deck}
 */
public class Card {
	
	public final Rarity rarity = Rarity.SPECIAL;
	public final int maxCount = 0;
	public final boolean ethereal = false;
	public final boolean preGame = false;
	
	protected Events events;
	protected Effects effects;
	protected Clank clank;
	protected Hazard hazard;
	protected GameDungeon gameDungeon;
	protected DOPlayer doPlayer;
	protected ScheduleEvent schedEvent;
	protected Cards cards;
	
	public static enum Rarity {
		COMMON,
		UNCOMMON,
		RARE,
		LEGENDARY,
		CROWN,
		BANE,
		SPECIAL;
	}
	
	public Card(CardBuilder builder) {
		this.events = builder.events;
		this.clank = builder.clank;
		this.hazard = builder.hazard;
		this.gameDungeon = builder.gameDungeon;
		this.cards = builder.cards;
		this.effects = builder.effects;
		this.doPlayer = builder.doPlayer;
		this.schedEvent = builder.schedEvent;
	}
	
	protected void addClank(int amount) {
		clank.addClank(amount);
	}
	
	protected void addClankBlock(int amount) {
		clank.addClankBlock(amount);
	}
	
	protected void addHazard(int amount) {
		hazard.addHazard(amount);
	}
	
	protected void addHazardBlock(int amount) {
		hazard.addHazardBlock(amount);
	}
	
	protected void addTreasure(int amount) {
		gameDungeon.addTreasure(amount);
	}
	
	protected void addEmbers(int amount) {
		gameDungeon.addEmbers(amount);
	}
	
	/**
	 * Plays the card
	 */
	public final void play() {
		InteractionResult result = events.CARD_EVENT.invoker().interact(this, EventTypes.Card.CARD_PLAYED);
		if(!(result == InteractionResult.FAIL)) {
			this.actions();
		}
	}
	
	/*
	 * Actions the card will perform
	 */
	protected void actions() {
		//A card can only be played every 6 seconds 
	}
	
	public static class CardBuilder {
		private Events events = null;
		private Effects effects = null;
		private Clank clank = null;
		private Hazard hazard = null;
		private GameDungeon gameDungeon = null;
		private Cards cards = null;
		private DOPlayer doPlayer = null;
		private ScheduleEvent schedEvent = null;
		
		public CardBuilder() {
			
		}
		
		public final CardBuilder setEvents(Events events) {
			this.events = events;
			return this;
		}
		
		public final CardBuilder setClank(Clank clank) {
			this.clank = clank;
			return this;
		}
		
		public final CardBuilder setHazard(Hazard hazard) {
			this.hazard = hazard;
			return this;
		}
		
		public final CardBuilder setGameDungeon(GameDungeon gameDungeon) {
			this.gameDungeon = gameDungeon;
			return this;
		}
		
		public final CardBuilder setCards(Cards cards) {
			this.cards = cards;
			return this;
		}
		
		public final CardBuilder setEffects(Effects effects) {
			this.effects = effects;
			return this;
		}
		
		public final CardBuilder setDOPlayer(DOPlayer doPlayer) {
			this.doPlayer = doPlayer;
			return this;
		}
		
		public final CardBuilder setSchedEvent(ScheduleEvent schedEvent) {
			this.schedEvent = schedEvent;
			return this;
		}
		
		public final Card build() {
			return new Card(this);
		}
	}
}
