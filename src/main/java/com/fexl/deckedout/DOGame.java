/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import java.util.ArrayList;

import com.fexl.deckedout.cards.Card;
import com.fexl.deckedout.cards.Cards;
import com.fexl.deckedout.event.Events;
import com.fexl.deckedout.event.ScheduleEvent;
import com.fexl.deckedout.treasure.Ember;
import com.fexl.deckedout.treasure.Treasure;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

/**
 * Used to initialize a new game instance.
 */
public class DOGame {
	//In-game spawn location
	private BlockPos spawnPos;
	
	//Decked Out Player
	public DOPlayer doPlayer; //assign current player
	
	//DOPlayer Lackeys
	public ArrayList<Lackey> lackeys;
	
	//Game Events
	public Events events;
	
	//Dungeon Clank
	public Clank clank;
	
	//Dungeon Hazard
	public Hazard hazard;
	
	//Dungeon Treasure
	public Treasure treasure;
	
	//Dungeon Embers
	public Ember embers;
	
	public ScheduleEvent scheduledEvents;
	
	public Effects effects;
	
	public Cards cards;
	
	public Deck deck;
	
	public Minecraft minecraft;
	
	public DOGame(DOPlayer doPlayer, Minecraft minecraft) {
		this.doPlayer = doPlayer;
		this.minecraft = minecraft;
	}
	
	/**
	 * Perform pre-game actions.
	 */
	private void preInit() {
		doPlayer.setLevel(1);
		deck = doPlayer.getDeck();
		clank = new Clank(events);
		hazard = new Hazard(events);
		treasure = new Treasure(events, minecraft);
		embers = new Ember(events, minecraft);
		scheduledEvents = new ScheduleEvent(minecraft);
		cards = new Cards(new Card.CardBuilder().setClank(clank).setEmber(embers).setDOPlayer(doPlayer).setEffects(effects).setHazard(hazard).setTreasure(treasure).setEvents(events).setSchedEvent(scheduledEvents));
	}
	
	/**
	 * Startup constantly running game events, such as {@link com.fexl.deckedout.Hazard}, 
	 * {@link com.fexl.deckedout.Clank} {@link com.fexl.deckedout.User} detection through {@link com.fexl.deckedout.event.ScheduleEvent}s,
	 * or 30-second {@link com.fexl.deckedout.cards.Card} plays from the {@link com.fexl.deckedout.DOPlayer}'s {@link com.fexl.deckedout.Deck}.
	 */
	private void gameEvents() {
		this.preInit();
		ServerTickEvents.START_SERVER_TICK.register((worldTick) -> {
			scheduledEvents.checkEvents(worldTick.getTickCount());
		});	
	}
	
	/**
	 * Startup the game, involving teleporting the {@link com.fexl.deckedout.User}s to their starting positions.
	 */
	public void start() {
		this.preInit();
		//Play preGame cards FIRST
		for(Card card : deck.getPreGameCards()) {
			card.play();
		}
		
		//Startup scheduled events
		this.gameEvents();
		
		
	}
	
	/**
	 * Save the {@link com.fexl.deckedout.DOPlayer}'s state, teleport the {@link com.fexl.deckedout.User}s, destroy the dungeon instance, etc.
	 */
	public void end() {
		
	}
}
