/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game;

import com.fexl.deckedout.game.dungeon.Dungeon;
import com.fexl.deckedout.game.event.EventTypes;
import com.fexl.deckedout.game.event.Events;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

/**
 * Activates obstacles in the dungeon that slow down the {@link com.fexl.deckedout.game.User}s or redirect their path.
 */
public class Hazard {
	
	private int hazardBlock;
	
	private Events events;
	private Dungeon dungeon;
	private DOPlayer doPlayer;
	
	public Hazard(Events events, Dungeon dungeon, DOPlayer doPlayer) {
		this.events = events;
		this.dungeon = dungeon;
		this.doPlayer = doPlayer;
	}
	
	/**
	 * Adds an amount of hazard if there is no {@link com.fexl.deckedout.game.Hazard#hazardBlock} left.
	 */
	public void addHazard(int amount) {
		InteractionResult result = events.HAZARD_EVENT.invoker().interact(EventTypes.Hazard.ADD_HAZARD);
		if(!(result == InteractionResult.FAIL)) {
			//Hazard Block
			if(hazardBlock > 0) { 
				hazardBlock--;
				return;
			}
			//Randomly determine if and what hazard will trigger for the amount given
			String command = "";
			
			//Execute the hazard trigger command for the selected hazard
			String string = doPlayer == null ? "Sign" : doPlayer.getName().getString();
			Component component = doPlayer == null ? MutableComponent.create(new LiteralContents("Sign")) : doPlayer.getDisplayName();
			doPlayer.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, Vec3.ZERO, Vec2.ZERO, (ServerLevel)doPlayer.level(), 2, string, component, doPlayer.level().getServer(), doPlayer), command);
		}
	}
	
	/**
	 * Used to block {@link com.fexl.deckedout.game.Hazard} when it occurs.
	 */
	public void addHazardBlock(int amount) {
		InteractionResult result = events.HAZARD_EVENT.invoker().interact(EventTypes.Hazard.ADD_HAZARD_BLOCK);
		if(!(result == InteractionResult.FAIL)) {
			if(hazardBlock + amount > dungeon.getMaxHazardBlock()) {
				hazardBlock = dungeon.getMaxHazardBlock();
				return;
			}
			
			hazardBlock += amount;
		}
	}
}
