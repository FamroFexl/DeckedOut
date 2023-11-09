package com.fexl.deckedout.treasure;

import com.fexl.deckedout.event.Events;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class Treasures {
	public Minecraft minecraft;
	public Events events;
	
	public static ResourceKey<Registry<Treasure>> treasureKey = ResourceKey.createRegistryKey(new ResourceLocation("deckedout", "treasures"));
	public static Registry<Treasure> TREASURES = FabricRegistryBuilder.createSimple(treasureKey).attribute(RegistryAttribute.MODDED).buildAndRegister();
	
	public Treasure register(String string, Treasure treasure) {
		return Registry.register(TREASURES, string, treasure);
	}
	
	public Treasures(Events events, Minecraft minecraft) {
		this.minecraft = minecraft;
	}
	
	Treasure coin = this.register("coin", new Coin(events, minecraft));
	Treasure key = this.register("key", new Key(events, minecraft));
	Treasure crown = this.register("crown", new Crown(events, minecraft));
	Treasure ember = this.register("ember", new Ember(events, minecraft));
}
