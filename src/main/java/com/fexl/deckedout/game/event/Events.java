package com.fexl.deckedout.game.event;

import com.fexl.deckedout.game.cards.Card;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Mob;

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
	
	public final Event<ItemEvent> ITEM_EVENT = EventFactory.createArrayBacked(ItemEvent.class, callbacks -> (block, tag, type) -> {
				for(ItemEvent event: callbacks) {
					InteractionResult result = event.interact(block, tag, type);
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
	
	public final Event<EffectEvent> EFFECT_EVENT = EventFactory.createArrayBacked(EffectEvent.class, callbacks -> (effect) -> {
		for(EffectEvent event: callbacks) {
			InteractionResult result = event.interact(effect);
			if(result != InteractionResult.PASS) {
				return result;
			}
		}
		return InteractionResult.PASS;
	});

	public final Event<MobEvent> MOB_EVENT = EventFactory.createArrayBacked(MobEvent.class, callbacks -> (blockPos, tag) -> {
		for(MobEvent event: callbacks) {
			InteractionResult result = event.interact(blockPos, tag);
			if(result != InteractionResult.PASS) {
				return result;
			}
		}
		return InteractionResult.PASS;
	});
	
	
	
	@FunctionalInterface
	public interface ClankEvent {
		InteractionResult interact(EventTypes.Clank type);
	}
	
	//@FunctionalInterface
	public interface CardEvent {
		InteractionResult interact(Card card, EventTypes.Card type);
	}
	
	@FunctionalInterface
	public interface HazardEvent {
		InteractionResult interact(EventTypes.Hazard type);
	}
	
	@FunctionalInterface
	public interface ItemEvent {
		InteractionResult interact(BlockPos block, CompoundTag tag, EventTypes.Item type);
	}
	
	@FunctionalInterface
	public interface LevelEvent {
		InteractionResult interact(int level, EventTypes.Level type);
	}
	
	@FunctionalInterface
	public interface EffectEvent {
		InteractionResult interact(MobEffect effect);
	}
	
	@FunctionalInterface
	public interface MobEvent {
		InteractionResult interact(BlockPos block, CompoundTag tag);
	}
}
