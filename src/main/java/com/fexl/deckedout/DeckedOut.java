package com.fexl.deckedout;

import com.fexl.deckedout.event.ScheduleEvent;
import com.fexl.deckedout.zones.ClankZone;
import com.fexl.deckedout.zones.TreasureZone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;

public class DeckedOut implements ModInitializer {
	public static DOGame doGame;
	
	@Override
	public void onInitialize() {
	}
	
}