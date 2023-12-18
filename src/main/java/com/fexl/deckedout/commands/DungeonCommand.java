package com.fexl.deckedout.commands;

import com.fexl.deckedout.game.HazardList;
import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.random.Rate;
import com.fexl.deckedout.game.zones.DOZone;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;

import static net.minecraft.commands.Commands.*;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class DungeonCommand {
	private static Dungeon dungeon;
	private static BlockPos pos1;
	private static BlockPos pos2;
	private static LinkedHashMap<String, Integer> rateMap;
	
	//dungeon command for dungeon creation
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralCommandNode<CommandSourceStack> dungeonCommand = dispatcher
		.register(literal("dungeon")
				.requires(css -> css.hasPermission(2))
				.then(literal("load")
						//Load a json file without the extension; if the file does not exist, it will be created
						.then(argument("[file]", StringArgumentType.string())
								
						)
				)
				.then(literal("save")
						//Save to the original file loaded, if that file exists
						.executes(null)
						//Save to a new file, or the original file, if explicitly specified
						.then(argument("[file]", StringArgumentType.string())
								
						)
				)
				//Create a new zone using the inputs from the "pos1", "pos2", and "rate" subcommands
				//This command resets the "pos1", "pos2", and "rate" subcommands after they are consumed
				.then(literal("zone")
						//Return the zone the player is currently standing in
						.executes(cc -> {
							
							return 1;
						})
						//Set the zone name (for reference purposes only)
						.then(argument("name", StringArgumentType.string())
								//Return the zones specified by the name
								.executes(null)
								
								//Enter a character for the zone type. T/t : Treasure, C/c : Clank, A/a : Artifact, M/m : Mob, S/s : Spawn (only one allowed)
								.then(argument("type", StringArgumentType.string())
										//If the type is S/s : Spawn, there is no need for a level
										.executes(cc -> {
											String type = StringArgumentType.getString(cc, "type").toLowerCase();
											
											//The zone type is not spawn, so it is invalid syntax
											if(!(type.equals("s") || type.equals("spawn"))) {
												return 0;
											}
											
											DOZone zone = new DOZone(pos1, pos2);
											zone.setName(StringArgumentType.getString(cc, "name"));
											zone.setLevel(1);
											zone.setRate(new Rate<String>(rateMap));
											
											return 1;
										})
										
										//Set the zone level
										.then(argument("level", IntegerArgumentType.integer(1))
												.executes(cc -> {
													String type = StringArgumentType.getString(cc, "type");
													
													DOZone zone = new DOZone(pos1, pos2);
													zone.setName(StringArgumentType.getString(cc, "name"));
													zone.setLevel(IntegerArgumentType.getInteger(cc, "level"));
													zone.setRate(new Rate<String>(rateMap));
													
													switch(type.toLowerCase()) {
														case "t":
															dungeon.treasureZones.add(zone);
															break;
														case "treasure":
															dungeon.treasureZones.add(zone);
															break;
														case "c":
															dungeon.clankZones.add(zone);
															break;
														case "clank":
															dungeon.clankZones.add(zone);
															break;
														case "a":
															dungeon.artifactZones.add(zone);
															break;
														case "artifact":
															dungeon.artifactZones.add(zone);
															break;
														case "m":
															dungeon.mobZones.add(zone);
															break;
														case "mob":
															dungeon.mobZones.add(zone);
															break;
														case "s":
															zone.setLevel(1);
															dungeon.startingZone = zone;
															break;
														case "spawn":
															zone.setLevel(1);
															dungeon.startingZone = zone;
															break;
														default:
															return 0;
													}
													
													//Reset rateMap
													rateMap = null;
													
													return 1;
												})
										)
								)
						)
				)
				//Set the rate of a new zone or hazard
				.then(literal("rate")
						//Return the active rate
						.executes(cc -> {
							sendMessage(cc, "Rate:");
							printRate(cc, new Rate<String>(rateMap));
							return 1;
						})
						
						//Add to the existing rate if it exists or create a new one
						.then(literal("add")
								//Set the object's chance of being chosen
								.then(argument("weight", IntegerArgumentType.integer(0))
										//Enter the nbt that will be executed if the rate is chosen
										.then(argument("nbt", StringArgumentType.string())
												.executes(cc -> {
													rateMap.put(StringArgumentType.getString(cc, "nbt"), IntegerArgumentType.getInteger(cc, "weight"));
													return 1;
												})
										)
								)
						)
				)
				//Set the first position of a new zone
				.then(literal("pos1")
						//Player position is implied if no blockPos is given
						.executes(cc -> {
							pos1 = cc.getSource().getEntity().blockPosition();
							return 1;
						})
						
						//Enter a specific blockPos
						.then(argument("blockPos", BlockPosArgument.blockPos())
							.executes(cc -> {
								pos1 = BlockPosArgument.getBlockPos(cc, "blockPos");
								return 1;
							})
						)
				)
				//Set the second position of a new zone
				.then(literal("pos2")
						//Player position is implied if no blockPos is given
						.executes(cc -> {
							pos2 = cc.getSource().getEntity().blockPosition();
							return 1;
						})
						
						//Enter a specific blockPos
						.then(argument("blockPos", BlockPosArgument.blockPos())
							.executes(cc -> {
								pos2 = BlockPosArgument.getBlockPos(cc, "blockPos");
								return 1;
							})
						)
						
				)
				//Add a hazard using the "rate" subcommands
				.then(literal("hazard")
						//Hazard name (for reference purposes only)
						.then(argument("name", StringArgumentType.string())
								//Get the hazard specified by the name
								.executes(cc -> {
									HazardList list = dungeon.hazardCommands.get(StringArgumentType.getString(cc, "name"));
									sendMessage(cc, "Hazard (min: " + list.getMin() + ", max: " + list.getMax() + "):");
									sendMessage(cc, "Hazard Min: " + list.getMin() + ", Hazard Max: " + list.getMax() + ", Hazard Rate:");
									
									//Print out the rates
									printRate(cc, list.getRate());
										
									return 1;
								})
								
								//The minimum number of hazards that should activate
								.then(argument("min", IntegerArgumentType.integer(0))
										//The max number of hazards that should activate
										.then(argument("max", IntegerArgumentType.integer(1))
												.executes(cc -> {
													dungeon.hazardCommands.put(
															StringArgumentType.getString(cc, "name"), 
															new HazardList(
																	new Rate<String>(rateMap),
																	IntegerArgumentType.getInteger(cc, "min"),
																	IntegerArgumentType.getInteger(cc, "max")
															)
													);
													//Reset the rateMap
													rateMap = null;
													
													return 1;
												})	
										)
								)
						)
				)
				//Set the maximum amount of treasure for a specific level
				.then(literal("maxLevelTreasure")
						//The treasure spawn level
						.then(argument("level", IntegerArgumentType.integer(1))
								//Return the treasure amount currently on the level
								.executes(cc -> sendMessage(cc, "Max Embers on level [" + IntegerArgumentType.getInteger(cc, "level") + "]: " + dungeon.maxTreasure.get(IntegerArgumentType.getInteger(cc, "level"))))
								
								//The max treasure spawn amount
								.then(argument("treasureMax", IntegerArgumentType.integer(0))
										.executes(cc -> {
											dungeon.maxTreasure.put(IntegerArgumentType.getInteger(cc, "level"), IntegerArgumentType.getInteger(cc, "treasureMax"));
											return 1;
										})
								)
						)
				)
				//Set the maximum amount of embers for a specific level
				.then(literal("maxLevelEmbers")
						//The ember spawn level
						.then(argument("level", IntegerArgumentType.integer(1))
								//Return the ember amount currently on the level
								.executes(cc -> sendMessage(cc, "Max Embers on level [" + IntegerArgumentType.getInteger(cc, "level") + "]: " + dungeon.maxEmbers.get(IntegerArgumentType.getInteger(cc, "level"))))
								
								//The max ember spawn amount
								.then(argument("emberMax", IntegerArgumentType.integer(0))
										.executes(cc -> {
											dungeon.maxEmbers.put(IntegerArgumentType.getInteger(cc, "level"), IntegerArgumentType.getInteger(cc, "emberMax"));
											return 1;
										})
								)
						)
				)
				//Set the max clank
				.then(literal("maxClank")
						//Return the maxClank
						.executes(cc -> sendMessage(cc, "Max Clank: " + dungeon.getMaxClank()))
						
						//The max clank can get to
						.then(argument("max", IntegerArgumentType.integer(0))
								.executes(cc -> {
									dungeon.setMaxClank(IntegerArgumentType.getInteger(cc, "max"));
									return 1;
								})
						)
				)
				//Set the max clank block
				.then(literal("maxClankBlock")
						//Return the maxClankBlock
						.executes(cc -> sendMessage(cc, "Max Clank Block: " + dungeon.getMaxClankBlock()))
						
						//The max clank block can get to
						.then(argument("max", IntegerArgumentType.integer(0))
								.executes(cc -> {
									dungeon.setMaxClankBlock(IntegerArgumentType.getInteger(cc, "max"));
									return 1;
								})
						)
				)
				//Set the max hazard block
				.then(literal("maxHazardBlock")
						//Return the maxHazardBlock
						.executes(cc -> sendMessage(cc, "Max Hazard Block: " + dungeon.getMaxHazardBlock()))
						
						//The max hazard block can get to
						.then(argument("max", IntegerArgumentType.integer(0))
								.executes(cc -> {
									dungeon.setMaxHazardBlock(IntegerArgumentType.getInteger(cc, "max"));
									return 1;
								})
						)
				)
				
		);	
		dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)literal("dgn").requires(css -> css.hasPermission(2))).redirect(dungeonCommand));
	}
	
	private static int sendMessage(CommandContext<CommandSourceStack> context, String text) {
		context.getSource().sendSystemMessage(MutableComponent.create(ComponentContents.EMPTY)
			.append(MutableComponent.create(new LiteralContents(text))
					.withStyle(style -> style
							.withColor(ChatFormatting.GREEN)
					)
			)
		);
		
		return 1;
	}
	
	private static void printRate(CommandContext<CommandSourceStack> context, Rate<String> rate) { 
		for(Entry<String, Integer> entry : rate.getRate().entrySet()) {
			sendMessage(context, "> Weight: " + entry.getValue() + ", Entry: " + entry.getKey());
		}
	}
	
	
}
