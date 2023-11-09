package com.fexl.deckedout;

import java.util.ArrayList;

import com.fexl.deckedout.cards.Card;
import com.fexl.deckedout.event.Events;
import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * The primary dungeon runner who provides the game {@link com.fexl.deckedout.Deck} and receives the awards.
 */
public class DOPlayer extends User {
	private Deck deck; //Player's deck
	private int crowns; //Number of crowns the player has
	private int tomes; //Number of tomes the player has
	private ArrayList<Card> cards; //extra cards not in the deck
	private int level;
	
	private Events events;
	
	public DOPlayer(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
		super(level, blockPos, f, gameProfile);
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void setLevel(int level) {
		this.level = level;
		
		if(level > this.level) {
			InteractionResult result = events.LEVEL_EVENT.invoker().interact(level, SpawnType.Level.PLAYER_LEVEL_LOWER);
		}
		else if(this.level < level) {
			InteractionResult result = events.LEVEL_EVENT.invoker().interact(level, SpawnType.Level.PLAYER_LEVEL_HIGHER);
		}
	}
	
	public int getLevel() {
		return level;
	}

}
