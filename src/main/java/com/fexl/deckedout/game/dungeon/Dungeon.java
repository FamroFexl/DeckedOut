/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game.dungeon;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fexl.deckedout.game.zones.DOZone;

/**
 * Stores the game components of loadable dungeon maps for reference
 */
public class Dungeon {
	private int maxClank = 16;
	private int maxClankBlock = -1;
	private int maxHazardBlock = -1;
	
	//Set the tag for an ember
	public String frostEmberItem = null;
		
	//Index is the level, value is the maximum amount of treasure spawnable on that level
	public LinkedHashMap<Integer, Integer> maxTreasure = new LinkedHashMap<Integer, Integer>();
	
	//Index is the level, value is the maximum amount of embers spawnable on that level
	public LinkedHashMap<Integer, Integer> maxEmbers = new LinkedHashMap<Integer, Integer>();
	
	//List of TreasureZones
	public ArrayList<DOZone> treasureZones = new ArrayList<DOZone>();
	
	//List of ClankZones
	public ArrayList<DOZone> clankZones = new ArrayList<DOZone>();
	
	//List of SpawnZones
	public ArrayList<DOZone> mobZones = new ArrayList<DOZone>();
	
	//List of ArtifactZones
	public ArrayList<DOZone> artifactZones = new ArrayList<DOZone>();
	
	public Dungeon() {
	}
	
	public void setMaxTreasure(int level, int maxTreasure) {
		//Level cannot be less than one
		if(level < 1) {
			return;
		}
		
		//Set the level's max treasure
		this.maxTreasure.put(level, maxTreasure);
	}
	
	public int getMaxTreasure(int level) {
		return maxTreasure.get(level);
	}
	
	public int getMaxEmbers(int level) {
		return maxEmbers.get(level);
	}
	
	public void setMaxClank(int clank) {
		this.maxClank = clank;
	}
	
	public int getMaxClank() {
		return this.maxClank;
	}
	
	public void setMaxClankBlock(int clankBlock) {
		this.maxClankBlock = clankBlock;
	}
	
	public int getMaxClankBlock() {
		return this.maxClankBlock;
	}
	
	public void setMaxHazardBlock(int hazardBlock) {
		this.maxHazardBlock = hazardBlock;
	}
	
	public int getMaxHazardBlock() {
		return this.maxHazardBlock;
	}
	
	public void addTreasureZone(DOZone zone) {
		this.treasureZones.add(zone);
	}
	
	public boolean removeTreasureZone(DOZone zone) {
		return this.treasureZones.remove(zone);
	}
	
	public void addClankZone(DOZone zone) {
		this.clankZones.add(zone);
	}
	
	public boolean removeClankZone(DOZone zone) {
		return this.clankZones.remove(zone);
	}
	
	public void addMobZone(DOZone zone) {
		this.mobZones.add(zone);
	}
	
	public boolean removeMobZone(DOZone zone) {
		return this.mobZones.remove(zone);
	}
	
}
