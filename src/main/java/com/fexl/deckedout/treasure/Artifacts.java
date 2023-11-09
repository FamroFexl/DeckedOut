package com.fexl.deckedout.treasure;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class Artifacts {

	public static ResourceKey<Registry<Item>> artifactsKey = ResourceKey.createRegistryKey(new ResourceLocation("deckedout", "artifacts"));
	public static Registry<Item> ARTIFACTS = FabricRegistryBuilder.createSimple(artifactsKey).attribute(RegistryAttribute.MODDED).buildAndRegister();
	
	//Item artifact = Artifacts.register("artifact", new Item()):
}
