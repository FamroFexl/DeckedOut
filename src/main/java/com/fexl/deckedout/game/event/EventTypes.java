/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game.event;

/**
 * Define {@link com.fexl.deckedout.game.event.Events} dungeon targets.
 */
public final class EventTypes {
	public interface EventMethods {
		public Event getType();
	}
	
	/**
	 * Classifications of spawnables.
	 */
	public static enum Event {
		CLANK,
		HAZARD,
		CARD,
		LEVEL,
		ITEM,
		MOB;
	}
	
	/**
	 * Types of dungeon {@link com.fexl.deckedout.game.Clank} spawnables.
	 */
	public static enum Clank implements EventMethods {
		ADD_CLANK(Event.CLANK),
		ADD_CLANK_BLOCK(Event.CLANK);
		
		private final Event type;
		
		private Clank(Event type) {
			this.type = type;
		}

		@Override
		public Event getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Types of dungeon {@link com.fexl.deckedout.game.Hazard} spawnables.
	 */
	public static enum Hazard implements EventMethods {
		ADD_HAZARD(Event.HAZARD),
		ADD_HAZARD_BLOCK(Event.HAZARD);
		
		private final Event type;
		
		private Hazard(Event type) {
			this.type = type;
		}

		@Override
		public Event getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Types of {@link com.fexl.deckedout.game.cards.Card} spawnables.
	 */
	public static enum Card implements EventMethods {
		CARD_PLAYED(Event.CARD);
		
		private final Event type;
		
		private Card(Event type) {
			this.type = type;
		}

		@Override
		public Event getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Dungeon {@link com.fexl.deckedout.game.DOPlayer} level changes.
	 */
	public static enum Level implements EventMethods {
		PLAYER_LEVEL_LOWER(Event.LEVEL),
		PLAYER_LEVEL_HIGHER(Event.LEVEL);
		
		private final Event type;
		
		private Level(Event type) {
			this.type = type;
		}

		@Override
		public Event getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Spawnable Items
	 */
	public static enum Item implements EventMethods {
		TREASURE(Event.ITEM),
		FROST_EMBER(Event.ITEM),
		ARTIFACT(Event.ITEM);
		
		private final Event type;
		
		private Item(Event type) {
			this.type = type;
		}

		@Override
		public Event getType() {
			return this.type;
		}
	}
	
	/**
	 * Mob spawning.
	 */
	public static enum Mob implements EventMethods {
		MOB_SPAWN(Event.MOB);
		
		private final Event type;
		
		private Mob(Event type) {
			this.type = type;
		}

		@Override
		public Event getType() {
			return this.type;
		}
	}
}
