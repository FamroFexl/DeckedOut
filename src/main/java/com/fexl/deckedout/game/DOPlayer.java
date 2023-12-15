package com.fexl.deckedout.game;

import java.util.ArrayList;

import com.fexl.deckedout.game.cards.Card;
import com.fexl.deckedout.game.event.EventTypes;
import com.fexl.deckedout.game.event.Events;
import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;

/**
 * The primary dungeon runner who provides the game {@link com.fexl.deckedout.game.Deck} and receives the awards.
 */
public class DOPlayer extends User {
	private Deck deck; //Player's deck
	private int crowns; //Number of crowns the player has
	private int tomes; //Number of tomes the player has
	private ArrayList<Card> cards; //extra cards not in the deck
	private int currentDOLevel;
	private final int selectedDOLevel;
	
	private Events events;
	
	public DOPlayer(Level level, BlockPos blockPos, float f, GameProfile gameProfile, int selectedDOLevel) {
		super(level, blockPos, f, gameProfile);
		this.selectedDOLevel = selectedDOLevel;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setCurrentDOLevel(int doLevel) {
		this.currentDOLevel = doLevel;
		
		if(doLevel > this.currentDOLevel) {
			InteractionResult result = events.LEVEL_EVENT.invoker().interact(doLevel, EventTypes.Level.PLAYER_LEVEL_LOWER);
		}
		else if(this.currentDOLevel < doLevel) {
			InteractionResult result = events.LEVEL_EVENT.invoker().interact(doLevel, EventTypes.Level.PLAYER_LEVEL_HIGHER);
		}
	}
	
	public int getCurrentDOLevel() {
		return currentDOLevel;
	}
	
	public int getSelectedDOLevel() {
		return selectedDOLevel;
	}

}
