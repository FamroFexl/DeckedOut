/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game.cards;

/**
 * Uses a builder to create a basic {@link com.fexl.deckedout.game.cards.Card}
 */
public class BasicCard extends Card {
	public Rarity rarity = Rarity.SPECIAL;
	public int maxCount = 0;
	public boolean ethereal = false;
	public boolean preGame = false;
	private int clank = 0;
	private int clankBlock = 0;
	private int hazard = 0;
	private int hazardBlock = 0;
	private int treasure = 0;
	private int embers = 0;
	
	public BasicCard(CardBuilder cardBuilder) {
		super(cardBuilder);
	}
	
	@Override
	public void actions() {
		this.addClank(this.clank);
		this.addClankBlock(this.clankBlock);
		this.addHazard(this.hazard);
		this.addHazardBlock(this.hazardBlock);
		this.addTreasure(this.treasure);
		this.addEmbers(this.embers);
	}
		
	public BasicCard setRarity(Rarity rarity) {
		this.rarity = rarity;
		return this;
	}
		
	public BasicCard setMaxCount(int maxCount) {
		this.maxCount = maxCount;
		return this;
	}
		
	public BasicCard setEthereal() {
		this.ethereal = true;
		return this;
	}
		
	public BasicCard setPreGame() {
		this.preGame = true;
		return this;
	}
	
	public BasicCard setClank(int amount) {
		this.clank = amount;
		return this;
	}
		
	public BasicCard setClankBlock(int amount) {
		this.clankBlock = amount;
		return this;
	}
		
	public BasicCard setHazard(int amount) {
		this.hazard = amount;
		return this;
	}
	
	public BasicCard setHazardBlock(int amount) {
		this.hazardBlock = amount;
		return this;
	}
		
	public BasicCard setTreasure(int amount) {
		this.treasure = amount;
		return this;
	}
		
	public BasicCard setEmbers(int amount) {
		this.embers = amount;
		return this;
	}
}
