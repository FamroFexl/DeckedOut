/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout;

/**
 * Define {@link com.fexl.deckedout.event.Events} dungeon targets.
 */
public final class SpawnType {
	public interface SpawnMethods {
		public Spawnables getType();
	}
	
	/**
	 * Classifications of spawnables.
	 */
	public static enum Spawnables {
		MOB,
		TREASURE,
		CLANK,
		HAZARD,
		CARD,
		LEVEL,
		EFFECT;
	}
	
	/**
	 * Types of {@link com.fexl.deckedout.treasure.Treasure} spawnables.
	 */
	public static enum Treasure implements SpawnMethods {
		COIN(Spawnables.TREASURE),
		EMBER(Spawnables.TREASURE),
		CROWN(Spawnables.TREASURE),
		KEY(Spawnables.TREASURE),
		ARTIFACT(Spawnables.TREASURE);

		private final Spawnables type;
		
		private Treasure(Spawnables type) {
			this.type = type;
		}

		public Spawnables getType() {
			return this.type;
		}
	}
	
	/**
	 * Types of mob spawnables.
	 */
	public static enum Mobs implements SpawnMethods {
		RAVAGER(Spawnables.MOB),
		WARDEN(Spawnables.MOB);

		private final Spawnables type;
		
		private Mobs(Spawnables type) {
			this.type = type;
		}
		
		public Spawnables getType() {
			return this.type;
		}
		
		
	}
	
	/**
	 * Types of dungeon {@link com.fexl.deckedout.Clank} spawnables.
	 */
	public static enum Clank implements SpawnMethods {
		ADD_CLANK(Spawnables.CLANK),
		ADD_CLANK_BLOCK(Spawnables.CLANK);
		
		private final Spawnables type;
		
		private Clank(Spawnables type) {
			this.type = type;
		}

		@Override
		public Spawnables getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Types of dungeon {@link com.fexl.deckedout.Hazard} spawnables.
	 */
	public static enum Hazard implements SpawnMethods {
		ADD_HAZARD(Spawnables.HAZARD),
		ADD_HAZARD_BLOCK(Spawnables.HAZARD);
		
		private final Spawnables type;
		
		private Hazard(Spawnables type) {
			this.type = type;
		}

		@Override
		public Spawnables getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Types of {@link com.fexl.deckedout.cards.Card} spawnables.
	 */
	public static enum Card implements SpawnMethods {
		CARD_PLAYED(Spawnables.CARD);
		
		private final Spawnables type;
		
		private Card(Spawnables type) {
			this.type = type;
		}

		@Override
		public Spawnables getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Types of {@link com.fexl.deckedout.DOLevel} spawnables.
	 */
	public static enum Level implements SpawnMethods {
		PLAYER_LEVEL_LOWER(Spawnables.LEVEL),
		PLAYER_LEVEL_HIGHER(Spawnables.LEVEL);
		
		private final Spawnables type;
		
		private Level(Spawnables type) {
			this.type = type;
		}

		@Override
		public Spawnables getType() {
			return this.type;
		}
		
	}
	
	/**
	 * Types of {@link com.fexl.deckedout.Effects} spawnables.
	 */
	public static enum Effect implements SpawnMethods {
		JUMP_BOOST_2(Spawnables.EFFECT),
		SPEED_2(Spawnables.EFFECT),
		REGENERATION_2(Spawnables.EFFECT),
		GENERAL(Spawnables.EFFECT);
		
		private final Spawnables type;
		
		private Effect(Spawnables type) {
			this.type = type;
		}

		@Override
		public Spawnables getType() {
			return this.type;
		}
		
	}
}
