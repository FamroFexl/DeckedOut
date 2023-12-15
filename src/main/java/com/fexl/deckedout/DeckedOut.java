package com.fexl.deckedout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fexl.deckedout.game.DOGame;
import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.event.Events;
import com.fexl.deckedout.game.event.ScheduleEvent;
import com.fexl.deckedout.game.random.Rate;
import com.fexl.deckedout.game.serializers.DOZoneTypeAdapterFactory;
import com.fexl.deckedout.game.serializers.DungeonTypeAdapterFactory;
import com.fexl.deckedout.game.serializers.RateTypeAdapter;
import com.fexl.deckedout.game.zones.DOZone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.icu.impl.UResource.Array;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class DeckedOut implements ModInitializer {
	public static DOGame doGame;
	
	@Override
	public void onInitialize() {
		LinkedHashMap<String, Integer> clank1 = new LinkedHashMap<String, Integer>();
		clank1.put("clank1", 9);
		clank1.put("clank2", 8);
		Rate<String> clankRate1 = new Rate<String>(clank1);
		
		DOZone clankZone1 = new DOZone(new BlockPos(1,2,3), new BlockPos(3,2,1));
		clankZone1.level = 3;
		clankZone1.rate = clankRate1;
		clankZone1.name = "ClankZone1";
		//----------------------------------------------
		LinkedHashMap<String, Integer> treasure1 = new LinkedHashMap<String, Integer>();
		treasure1.put("treasure1", 5);
		treasure1.put("treasure2", 7);
		Rate<String> treasureRate1 = new Rate<String>(treasure1);
		
		DOZone treasureZone1 = new DOZone(new BlockPos(5,6,7), new BlockPos(8,6,2));
		treasureZone1.level = 3;
		treasureZone1.rate = treasureRate1;
		treasureZone1.name = "TreasureZone1";
		//----------------------------------------------
		LinkedHashMap<String, Integer> artifact1 = new LinkedHashMap<String, Integer>();
		artifact1.put("artifact1", 0);
		artifact1.put("artifact2", 1);
		Rate<String> artifactRate1 = new Rate<String>(artifact1);
		
		DOZone artifactZone1 = new DOZone(new BlockPos(5,10,9), new BlockPos(7,8,4));
		artifactZone1.level = 3;
		artifactZone1.rate = artifactRate1;
		artifactZone1.name = "ArtifactZone1";
		//----------------------------------------------
		LinkedHashMap<String, Integer> artifact2 = new LinkedHashMap<String, Integer>();
		artifact2.put("artifact3", 10);
		artifact2.put("artifact4", 20);
		Rate<String> artifactRate2 = new Rate<String>(artifact1);
		
		DOZone artifactZone2 = new DOZone(new BlockPos(8,6,5), new BlockPos(9,8,7));
		artifactZone2.level = 5;
		artifactZone2.rate = artifactRate2;
		artifactZone2.name = "ArtifactZone2";
		//----------------------------------------------
		LinkedHashMap<String, Integer> mob1 = new LinkedHashMap<String, Integer>();
		mob1.put("mob1", 5);
		mob1.put("mob2", 89);
		Rate<String> mobRate1 = new Rate<String>(mob1);
		
		DOZone mobZone1 = new DOZone(new BlockPos(5,8,7), new BlockPos(9,7,3));
		mobZone1.level = 3;
		mobZone1.rate = mobRate1;
		mobZone1.name = "MobZone1";
		//----------------------------------------------
		Dungeon dungeon = new Dungeon();
		dungeon.setMaxClank(30);
		dungeon.setMaxClankBlock(24);
		dungeon.setMaxHazardBlock(4);
		
		LinkedHashMap<Integer, Integer> maxTreasure = new LinkedHashMap<Integer, Integer>();
		maxTreasure.put(1, 30);
		maxTreasure.put(2, 14);
		dungeon.maxTreasure = maxTreasure;
		
		LinkedHashMap<Integer, Integer> maxEmbers = new LinkedHashMap<Integer, Integer>();
		maxEmbers.put(1, 10);
		maxEmbers.put(2, 15);
		dungeon.maxEmbers = maxEmbers;
		
		dungeon.addClankZone(clankZone1);
		dungeon.addTreasureZone(treasureZone1);
		
		ArrayList<DOZone> artifactZones = new ArrayList<DOZone>();
		artifactZones.add(artifactZone1);
		artifactZones.add(artifactZone2);
		dungeon.artifactZones = artifactZones;
		
		dungeon.addMobZone(mobZone1);
		//----------------------------------------------
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Rate.class, new RateTypeAdapter()).registerTypeAdapterFactory(new DOZoneTypeAdapterFactory()).registerTypeAdapterFactory(new DungeonTypeAdapterFactory()).setPrettyPrinting().create();
		String to = gson.toJson(dungeon);
		System.out.println(to);
		Dungeon from = gson.fromJson(to, Dungeon.class);
	}
	
}