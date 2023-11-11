/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import com.fexl.deckedout.event.Events;
import com.fexl.deckedout.treasure.FrostEmber;
import com.fexl.deckedout.zones.ClankZone;
import com.fexl.deckedout.zones.DOZone;
import com.fexl.deckedout.zones.SharedZone;
import com.fexl.deckedout.zones.MobZone;
import com.fexl.deckedout.zones.TreasureZone;
import com.fexl.deckedout.zones.Zone;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

/**
 * Stores the game components of loadable dungeon maps for reference
 */
public class Dungeon {
	private int maxClank = 16;
	private int maxClankBlock = -1;
	private int maxHazardBlock = -1;
	
	//Index is the level, value is the maximum amount of treasure spawnable on that level
	public LinkedHashMap<Integer, Integer> maxTreasure = new LinkedHashMap<Integer, Integer>();
					
	//Index is the level, value is the current amount of treasure spawned on that level
	public LinkedHashMap<Integer, Integer> currentTreasure = new LinkedHashMap<Integer, Integer>();
	
	//Index is the level, value is the maximum amount of embers spawnable on that level
	public LinkedHashMap<Integer, Integer> maxEmbers = new LinkedHashMap<Integer, Integer>();
	
	//Index is the level, value is the current amount of embers spawned on that level
	public LinkedHashMap<Integer, Integer> currentEmbers = new LinkedHashMap<Integer, Integer>();
	
	//List of TreasureZones
	public ArrayList<TreasureZone> treasureZones = new ArrayList<TreasureZone>();
	
	//List of ClankZones
	public ArrayList<ClankZone> clankZones = new ArrayList<ClankZone>();
	
	//List of SpawnZones
	public ArrayList<MobZone> mobZones = new ArrayList<MobZone>();
	
	//List of SharedZones
	public ArrayList<SharedZone> sharedZones = new ArrayList<SharedZone>();
	
	public DOPlayer player;
	public FrostEmber embers;
	
	public Dungeon(DOPlayer player, FrostEmber embers) {
		this.player = player;
		this.embers = embers;
	}
	
	private <T extends DOZone> T randomLevelZone(int level, ArrayList<T> zones) {
		ArrayList<T> levelZones = this.getLevelZones(level, zones);
		
		return levelZones.get((new Random()).nextInt(levelZones.size()));
	}
	
	private <T extends DOZone> ArrayList<T> getLevelZones(int level, ArrayList<T> zones) {
		ArrayList<T> levelZones = new ArrayList<T>();
		
		for(T zone : zones) {
			if(zone.level == level) {
				levelZones.add(zone);
			}
		}
		
		return levelZones;
	}
	
	public boolean spawnTreasure() {
		if(!this.addTreasure(1)) {
			return false;
		}
		
		//Get a random zone on the DOPlayer's level
		TreasureZone randomTreasureZone = this.randomLevelZone(player.getLevel(), treasureZones);
		
		//Spawn treasure on a random block within that zone
		randomTreasureZone.spawnTreasure();
		
		return true;
	}
	
	public boolean spawnEmber() {
		if(!this.addEmbers(1)) {
			return false;
		}
		
		//Get a random zone on the DOPlayer's level
		TreasureZone randomTreasureZone = this.randomLevelZone(player.getLevel(), treasureZones);
		
		//Spawn an ember on a random block within that zone
		embers.spawn(randomTreasureZone.getRandValidZoneBlock());
		
		return true;
	}
	
	public void spawnMobs(int level) {
		ArrayList<MobZone> levelZones = this.getLevelZones(level, mobZones);
		
		for(MobZone zone : levelZones) {
			zone.spawnMobs();
		}
	}
	
	public boolean addTreasure(int treasure) {
		//Check the level's max treasure hasn't been reached
		if(currentTreasure.get(player.getLevel()) >= maxTreasure.get(player.getLevel())) {
			return false;
		}
		
		//Increment the current treasure on that level
		currentTreasure.put(player.getLevel(), currentTreasure.get(player.getLevel()) + 1);
		
		return true;
	}
	
	public boolean addEmbers(int embers) {
		//Check the level's max embers hasn't been reached
		if(currentEmbers.get(player.getLevel()) >= maxEmbers.get(player.getLevel())) {
			return false;
		}
		
		//Increment the current embers on that level
		currentEmbers.put(player.getLevel(), currentEmbers.get(player.getLevel()) + 1);
		
		return true;
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
	
	public void addMobZone(MobZone zone) {
		this.mobZones.add(zone);
	}
	
	public boolean removeMobZone(MobZone zone) {
		return this.mobZones.remove(zone);
	}
	
	public void addSharedZone(SharedZone zone) {
		this.sharedZones.add(zone);
	}
	
	public boolean removeSharedZone(SharedZone zone) {
		return this.sharedZones.remove(zone);
	}
	
}
