/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game.dungeon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import com.fexl.deckedout.game.HazardList;
import com.fexl.deckedout.game.zones.DOZone;

import net.minecraft.core.BlockPos;

/**
 * Stores the game components of loadable dungeon maps for reference
 */
public class Dungeon {
	private int maxClank = 16;
	private int maxClankBlock = -1;
	private int maxHazardBlock = -1;
	
	//DOPlayer and Lackey starting position
	public DOZone startingZone;
	
	//Set the tag for an ember
	public String frostEmberItem = null;
		
	//Index is the level, value is the maximum amount of treasure spawnable on that level
	public LinkedHashMap<Integer, Integer> maxTreasure = new LinkedHashMap<Integer, Integer>();
	
	//Index is the level, value is the maximum amount of embers spawnable on that level
	public LinkedHashMap<Integer, Integer> maxEmbers = new LinkedHashMap<Integer, Integer>();
	
	//A list of hazards activated with commands
	public LinkedHashMap<String, HazardList> hazardCommands = new LinkedHashMap<String, HazardList>();
	
	//Treasure spawn zones
	public ArrayList<DOZone> treasureZones = new ArrayList<DOZone>();
	
	//Clank detection zones
	public ArrayList<DOZone> clankZones = new ArrayList<DOZone>();
	
	//Mob spawn zones
	public ArrayList<DOZone> mobZones = new ArrayList<DOZone>();
	
	//Artifact retrieval zones
	public ArrayList<DOZone> artifactZones = new ArrayList<DOZone>();
	
	public static <T extends DOZone> T randomLevelZone(int level, ArrayList<T> zones) {
		ArrayList<T> levelZones = getLevelZones(level, zones);
		
		return levelZones.get((new Random()).nextInt(levelZones.size()));
	}
	
	public static <T extends DOZone> ArrayList<T> getLevelZones(int level, ArrayList<T> zones) {
		ArrayList<T> levelZones = new ArrayList<T>();
		
		for(T zone : zones) {
			if(zone.level == level) {
				levelZones.add(zone);
			}
		}
		
		return levelZones;
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
