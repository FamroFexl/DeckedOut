/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.event;

/**
 * Define {@link com.fexl.deckedout.event.Events} dungeon targets.
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
		LEVEL;
	}
	
	/**
	 * Types of dungeon {@link com.fexl.deckedout.Clank} spawnables.
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
	 * Types of dungeon {@link com.fexl.deckedout.Hazard} spawnables.
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
	 * Types of {@link com.fexl.deckedout.cards.Card} spawnables.
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
	 * Dungeon {@link com.fexl.deckedout.DOPlayer} level changes.
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
}
