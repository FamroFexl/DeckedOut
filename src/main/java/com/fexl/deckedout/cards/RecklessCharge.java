package com.fexl.deckedout.cards;

import com.fexl.deckedout.event.ScheduleEvent;

import net.minecraft.world.InteractionResult;

public class RecklessCharge extends Card {
	public final Rarity rarity = Rarity.UNCOMMON;
	public final int maxCount = 3;
	
	private long pastTick;
	private boolean clankValid;
	
	public RecklessCharge(CardBuilder builder) {
		super(builder);
	}
	
	@Override
	public void actions() {
		this.addHazard(2);
		
		pastTick = schedEvent.getServerTick();
		this.clankValid = true;
		
		//If clank is added within 8 seconds
		events.CLANK_EVENT.register((type) -> {
			//Only activate once
			if(!clankValid) {
				return InteractionResult.PASS;
			}
			this.clankValid = false;
			
			//Check if less than 8 seconds
			if(schedEvent.getServerTick() < pastTick + ScheduleEvent.secToTick(8)) {
				this.addEmbers(10);
			}
			return InteractionResult.PASS;
		});
	}
}
