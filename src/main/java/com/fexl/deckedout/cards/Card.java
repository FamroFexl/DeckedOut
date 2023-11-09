/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.cards;

import com.fexl.deckedout.Clank;
import com.fexl.deckedout.DOPlayer;
import com.fexl.deckedout.Effects;
import com.fexl.deckedout.Hazard;
import com.fexl.deckedout.SpawnType;
import com.fexl.deckedout.event.Events;
import com.fexl.deckedout.event.ScheduleEvent;
import com.fexl.deckedout.treasure.Ember;
import com.fexl.deckedout.treasure.Treasure;

import net.minecraft.world.InteractionResult;

/**
 * Defines playable cards that are used via a {@link com.fexl.deckedout.Deck}
 */
public class Card {
	
	public final Rarity rarity = Rarity.SPECIAL;
	public final int maxCount = 0;
	public final boolean ethereal = false;
	public final boolean preGame = false;
	
	public Events events;
	public Effects effects;
	public Clank clank;
	public Hazard hazard;
	public Treasure treasure;
	public Ember ember;
	public DOPlayer doPlayer;
	public ScheduleEvent schedEvent;
	
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
		this.treasure = builder.treasure;
		this.ember = builder.ember;
		this.effects = builder.effects;
		this.doPlayer = builder.doPlayer;
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
		treasure.addTreasure(amount);
	}
	
	protected void addEmbers(int amount) {
		ember.addEmbers(amount);
	}
	
	//Play the card
	public final void play() {
		InteractionResult result = events.CARD_EVENT.invoker().interact(this, SpawnType.Card.CARD_PLAYED);
		if(!(result == InteractionResult.FAIL)) {
			this.actions();
		}
	}
	
	//What you want the card to do
	protected void actions() {
		//A card can only be played every 6 seconds 
	}
	
	public static class CardBuilder {
		private Events events = null;
		private Effects effects = null;
		private Clank clank = null;
		private Hazard hazard = null;
		private Treasure treasure = null;
		private Ember ember = null ;
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
		
		public final CardBuilder setTreasure(Treasure treasure) {
			this.treasure = treasure;
			return this;
		}
		
		public final CardBuilder setEmber(Ember ember) {
			this.ember = ember;
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
