/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fexl.deckedout.zones.ClankZone;
import com.fexl.deckedout.zones.SharedZone;
import com.fexl.deckedout.zones.MobZone;
import com.fexl.deckedout.zones.TreasureZone;

/**
 * Stores the game components of loadable dungeon maps for reference
 */
public class Dungeon {
	private int maxClank = 16;
	private int maxClankBlock = -1;
	private int maxHazardBlock = -1;
	
	//Index is the level, value is the maximum amount of treasure spawnable on that level
	public LinkedHashMap<Integer, Integer> maxTreasure = new LinkedHashMap<Integer, Integer>();
					
	//Index is the level, value is the current amount of treasure spawnable on that level
	public LinkedHashMap<Integer, Integer> currentTreasure = new LinkedHashMap<Integer, Integer>();
	
	//List of TreasureZones
	public ArrayList<TreasureZone> treasureZones = new ArrayList<TreasureZone>();
	
	//List of ClankZones
	public ArrayList<ClankZone> clankZones = new ArrayList<ClankZone>();
	
	//List of SpawnZones
	public ArrayList<MobZone> spawnZones = new ArrayList<MobZone>();
	
	//List of SharedZones
	public ArrayList<SharedZone> sharedZones = new ArrayList<SharedZone>();
	
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
	
	public void addTreasureZone(TreasureZone zone) {
		this.treasureZones.add(zone);
	}
	
	public boolean removeTreasureZone(TreasureZone zone) {
		return this.treasureZones.remove(zone);
	}
	
	public void addClankZone(ClankZone zone) {
		this.clankZones.add(zone);
	}
	
	public boolean removeClankZone(ClankZone zone) {
		return this.clankZones.remove(zone);
	}
	
	public void addSpawnZone(MobZone zone) {
		this.spawnZones.add(zone);
	}
	
	public boolean removeSpawnZone(MobZone zone) {
		return this.spawnZones.remove(zone);
	}
	
	public void addSharedZone(SharedZone zone) {
		this.sharedZones.add(zone);
	}
	
	public boolean removeSharedZone(SharedZone zone) {
		return this.sharedZones.remove(zone);
	}
	
}
