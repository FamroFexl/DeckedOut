/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.event;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class ScheduleEvent {
	//Hold tasks for execution
	private ArrayList<ArrayList<Object>> tasks = new ArrayList<ArrayList<Object>>();
	
	private Minecraft minecraft;
	
	public ScheduleEvent(Minecraft minecraft) {
		this.minecraft = minecraft;
	}
	
	//Add a basic event that runs once
	public void addEvent(Runnable task, long executeTick) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(task);
		list.add(minecraft.level.getGameTime() + executeTick);
		
		tasks.add(list);
	}
	
	//Add an event that repeats after an amount of time
	public void addRepeatingEvent(Runnable task, long tickDelay) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(task);
		list.add(minecraft.level.getGameTime() + tickDelay);
		list.add(tickDelay);
		
		tasks.add(list);
	}
	
	
	//Remove a repeating event if it isn't needed anymore
	public boolean removeRepeatingEvent(ArrayList<Object> event) {
		for(ArrayList<Object> task : tasks) {
			if(event.equals(task)) {
				tasks.remove(task);
				return true;
			}
		}
		
		return false;
	}
	
	//Execute tasks
	public void checkEvents(int currentTick) {
		for(ArrayList<Object> task : tasks) {
			if(((Integer)task.get(1)) == currentTick) {
				//Run the task
				((Runnable)task.get(0)).run();
				
				//The event is a repeating event
				if(task.size() > 2) {
					//Repeat the event in its tickDelay
					task.set(1, currentTick + ((Integer) task.get(2)));
				}
				else {
					//Remove the task
					tasks.remove(task);
				}
			}
		}
	}
	
	public static long tickToSec(long ticks) {
		//Good enough estimate
		return ticks / 20;
	}
	
	public static long secToTick(long sec) {
		return sec * 20;
	}
	
	public long getServerTick() {
		return minecraft.level.getGameTime();
	}
	
}
