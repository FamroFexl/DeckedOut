/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import java.util.ArrayList;
import java.util.Random;

import com.fexl.deckedout.cards.Card;
import com.fexl.deckedout.cards.Cards;

import net.minecraft.resources.ResourceLocation;

/**
 * Stores a {@link com.fexl.deckedout.DOPlayer}'s {@link com.fexl.deckedout.cards.Card}s.
 */
public class Deck {

	private ArrayList<Card> deckCards;
	
	private ArrayList<Card> remainingCards;
	
	private ArrayList<Card> preGameCards;
	
	/**
	 * Generate the {@link com.fexl.deckedout.DOPlayer}'s card processing lists and {@link com.fexl.deckedout.Deck#preGameCards}.
	 */
	public Deck() {
		remainingCards = new ArrayList<>(deckCards);
		
		for(Card card : remainingCards) {
			if(card.preGame == true) {
				preGameCards.add(card);
				remainingCards.remove(card);
			}
		}
	}
	
	/**
	 * Get a random card from the remaining deck.
	 */
	public Card getNextCard() {
		int randomCard = (new Random()).nextInt(remainingCards.size());
		
		return remainingCards.remove(randomCard);
	}
	
	public ArrayList<Card> getRemainingCards() {
		return remainingCards;
	}
	
	public ArrayList<Card> getDeck() {
		return deckCards;
	}
	
	/**
	 * Add {@link com.fexl.deckedout.cards.Cards#stumble} cards into the deck.
	 */
	public void addStumbleCards(int count) {
		for(int i=0; i < count; i++) {
			remainingCards.add(Cards.CARDS.get(new ResourceLocation("stumble")));
		}
	}
	
	/**
	 * Get {@link com.fexl.deckedout.cards.Card}'s from the deck that should be played before the game starts.
	 */
	public ArrayList<Card> getPreGameCards() {
		return preGameCards;
	}
	
	public void load() {
		//Load the deck from file
	}
	
	public void save() {
		//Save the deck from file
	}
}
