/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game;

import java.util.ArrayList;
import java.util.Random;

import com.fexl.deckedout.game.cards.Card;
import com.fexl.deckedout.game.cards.Cards;
import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.dungeon.GameDungeon;
import com.fexl.deckedout.game.event.Events;
import com.fexl.deckedout.game.event.ScheduleEvent;

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
	
	public ScheduleEvent scheduledEvents;
	
	public Effects effects;
	
	public Cards cards;
	
	public Deck deck;
	
	public Minecraft minecraft;
	
	public GameDungeon gameDungeon;
	
	public Dungeon dungeon;
	
	public Artifact artifact;
	
	public DOGame(DOPlayer doPlayer, Minecraft minecraft) {
		this.doPlayer = doPlayer;
		this.minecraft = minecraft;
	}
	
	/**
	 * Perform pre-game actions.
	 */
	private void preInit() {
		doPlayer.setCurrentDOLevel(1);
		deck = doPlayer.getDeck();
		
		clank = new Clank(events, dungeon);
		hazard = new Hazard(events, dungeon, doPlayer);
		
		scheduledEvents = new ScheduleEvent(minecraft);
		
		//Hazard Schedule
		scheduledEvents.addRepeatingEvent(()->{hazard.addHazard(1);}, ScheduleEvent.secToTick(25));
		
		//Card play schedule
		scheduledEvents.addRepeatingEvent(()->{deck.getNextCard().play();}, ScheduleEvent.secToTick(30));
		
		//Artifact detection schedule
		scheduledEvents.addRepeatingEvent(()->{artifact.checkCompass();}, 1);
		
		//Clank detection schedule
		scheduledEvents.addRepeatingEvent(()->{clank.checkClank();}, ScheduleEvent.secToTick(1));
		
		cards = new Cards(new Card.CardBuilder().setClank(clank).setDOPlayer(doPlayer).setEffects(effects).setCards(cards).setHazard(hazard).setEvents(events).setSchedEvent(scheduledEvents));
	}
	
	/**
	 * Startup constantly running game events, such as {@link com.fexl.deckedout.game.Hazard}, 
	 * {@link com.fexl.deckedout.game.Clank} {@link com.fexl.deckedout.game.User} detection through {@link com.fexl.deckedout.game.event.ScheduleEvent}s,
	 * or 30-second {@link com.fexl.deckedout.game.cards.Card} plays from the {@link com.fexl.deckedout.game.DOPlayer}'s {@link com.fexl.deckedout.game.Deck}.
	 */
	private void gameEvents() {
		this.preInit();
		ServerTickEvents.START_SERVER_TICK.register((worldTick) -> {
			scheduledEvents.checkEvents(worldTick.getTickCount());
		});	
	}
	
	/**
	 * Startup the game, involving teleporting the {@link com.fexl.deckedout.game.User}s to their starting positions.
	 */
	public void start() {
		this.preInit();
		//Play preGame cards FIRST
		for(Card card : deck.getPreGameCards()) {
			card.play();
		}
		
		//Teleport the DOPlayer and Lackeys to the starting position
		
		//Spawn the starting mobs for floor 1
		gameDungeon.spawnMobs(1);
		
		//Startup scheduled events
		this.gameEvents();
		
		
	}
	
	/**
	 * Save the {@link com.fexl.deckedout.game.DOPlayer}'s state, teleport the {@link com.fexl.deckedout.game.User}s, destroy the dungeon instance, etc.
	 */
	public void end() {
		
	}
}
