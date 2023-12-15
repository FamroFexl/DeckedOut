/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.fexl.deckedout.game.cards.Card;
import com.fexl.deckedout.game.cards.Cards;

import net.minecraft.resources.ResourceLocation;

/**
 * Stores a {@link com.fexl.deckedout.game.DOPlayer}'s {@link com.fexl.deckedout.game.cards.Card}s.
 */
public class Deck {
	private Cards cards;

	private ArrayList<Card> deckCards;
	private ArrayList<Card> remainingCards;
	private ArrayList<Card> preGameCards;
	
	/**
	 * Generate the {@link com.fexl.deckedout.game.DOPlayer}'s card processing lists and {@link com.fexl.deckedout.game.Deck#preGameCards}.
	 */
	public Deck(Cards cards) {
		this.cards = cards;
		
		remainingCards = new ArrayList<>(deckCards);
		
		//Remove maxed cards
		remainingCards = this.removeMaxedCards(remainingCards);
		
		for(Card card : remainingCards) {
			if(card.preGame == true) {
				preGameCards.add(card);
				remainingCards.remove(card);
			}
		}
	}
	
	/**
	 * Get a random card from the remaining deck.
	 * @return a random card is a valid card is still in the deck
	 * @return null if no valid cards exist or if the deck is empty
	 */
	public Card getNextCard() {
		
		//The deck is empty
		if(remainingCards.size() == 0) {
			return null;
		}
		
		//Get a random card from the remaining deck
		Card randomCard = remainingCards.get((new Random()).nextInt(remainingCards.size()));
		
		//Remove the card from the remaining deck
		remainingCards.remove(randomCard);
		
		//If the card is ethereal, remove it from the DOPlayer's deck
		if(randomCard.ethereal) {
			deckCards.remove(randomCard);
		}
		
		return randomCard;
	}
	
	public ArrayList<Card> getRemainingCards() {
		return remainingCards;
	}
	
	public ArrayList<Card> getDeck() {
		return deckCards;
	}
	
	/**
	 * Remove cards from a deck that exceed the max count of their type
	 */
	public ArrayList<Card> removeMaxedCards(ArrayList<Card> cards) {
		HashMap<Card, Integer> cardIndex = new HashMap<Card, Integer>();
		
		//For all cards in the input
		for(Card card : cards) {
			//Increment the card count if it is contained in the cardIndex
			if(cardIndex.containsKey(card)) {
				cardIndex.put(card, cardIndex.get(card) + 1);
			}
			//Put the card in the index and initialize it
			else {
				cardIndex.put(card, 1);
			}
			//Check the cardIndex value for the card doesn't exceed its value
			if(cardIndex.get(card) > card.maxCount) {
				//Remove the overflow card from the count
				cardIndex.put(card, cardIndex.get(card) - 1);
				
				//Remove the overflow card
				cards.remove(card);
			}
		}
		
		return cards;
	}
	
	/**
	 * Add {@link com.fexl.deckedout.game.cards.Cards#stumble} cards into the deck.
	 */
	public void addStumbleCards(int count) {
		for(int i=0; i < count; i++) {
			remainingCards.add(cards.CARDS.get(new ResourceLocation("stumble")));
		}
	}
	
	public void addCard(Card card) {
		remainingCards.add(card);
	}
	
	/**
	 * Get {@link com.fexl.deckedout.game.cards.Card}'s from the deck that should be played before the game starts.
	 */
	public ArrayList<Card> getPreGameCards() {
		//Remove ethereal cards
		for(Card card : preGameCards) {
			if(card.ethereal) { 
				deckCards.remove(card);
			}
		}
		
		return preGameCards;
	}
}
