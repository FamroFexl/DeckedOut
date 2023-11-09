package com.fexl.deckedout.event;

import com.fexl.deckedout.SpawnType;
import com.fexl.deckedout.cards.Card;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;

public class Events {
	public final Event<ClankEvent> CLANK_EVENT = EventFactory.createArrayBacked(ClankEvent.class, callbacks -> registry -> {
		for (ClankEvent event : callbacks) {
			InteractionResult result = event.interact(registry);
			if(result != InteractionResult.PASS) {
				return result;
			}
		}
		return InteractionResult.PASS;
	});
	
	public final Event<CardEvent> CARD_EVENT = EventFactory.createArrayBacked(CardEvent.class, callbacks -> (card, type) -> {
		for (CardEvent event: callbacks) {
			InteractionResult result = event.interact(card, type);
			if(result != InteractionResult.PASS) {
				return result;
			}
		}
		return InteractionResult.PASS;
	});
	
	public final Event<HazardEvent> HAZARD_EVENT = EventFactory.createArrayBacked(HazardEvent.class, callbacks -> (type) -> {
		for(HazardEvent event: callbacks) {
			InteractionResult result = event.interact(type);	
			if(result != InteractionResult.PASS) {
				return result;
			}
		}		
		return InteractionResult.PASS;
	});
	
	public final Event<TreasureEvent> TREASURE_EVENT = EventFactory.createArrayBacked(TreasureEvent.class, callbacks -> (block, type) -> {
				for(TreasureEvent event: callbacks) {
					InteractionResult result = event.interact(block, type);
					if(result != InteractionResult.PASS) {
						return result;
					}
				}
				return InteractionResult.PASS;
			});
	
	public final Event<LevelEvent> LEVEL_EVENT = EventFactory.createArrayBacked(LevelEvent.class, callbacks -> (level, type) -> {
		for(LevelEvent event: callbacks) {
			InteractionResult result = event.interact(level, type);
			if(result != InteractionResult.PASS) {
				return result;
			}
		}
		return InteractionResult.PASS;
	});
	
	public final Event<EffectEvent> EFFECT_EVENT = EventFactory.createArrayBacked(EffectEvent.class, callbacks -> (effect, type) -> {
		for(EffectEvent event: callbacks) {
			InteractionResult result = event.interact(effect, type);
			if(result != InteractionResult.PASS) {
				return result;
			}
		}
		return InteractionResult.PASS;
	});
	
	
	
	@FunctionalInterface
	public interface ClankEvent {
		InteractionResult interact(SpawnType.Clank type);
	}
	
	//@FunctionalInterface
	public interface CardEvent {
		InteractionResult interact(Card card, SpawnType.Card type);
	}
	
	@FunctionalInterface
	public interface HazardEvent {
		InteractionResult interact(SpawnType.Hazard type);
	}
	
	@FunctionalInterface
	public interface TreasureEvent {
		InteractionResult interact(BlockPos block, SpawnType.Treasure type);
	}
	
	@FunctionalInterface
	public interface LevelEvent {
		InteractionResult interact(int level, SpawnType.Level type);
	}
	
	@FunctionalInterface
	public interface EffectEvent {
		InteractionResult interact(MobEffect effect, SpawnType.Effect type);
	}
}