package com.fexl.deckedout.game.zones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;


public class Zone {
	//A zone can only be composed of two coordinates
	public List<BlockPos> zone = Arrays.asList(new BlockPos[2]);
	
	//Valid spawn blocks
	private transient ArrayList<Block> validBlocks = new ArrayList<Block>();
	
	//Valid holding spawn blocks
	private transient ArrayList<Block> validBaseBlocks = new ArrayList<Block>();
	
	public Zone(BlockPos blockPos1, BlockPos blockPos2) {
		zone.set(0, blockPos1);
		zone.set(1, blockPos2);
	}
	
	public Zone(BlockPos blockPos) {
		//One-block zone
		this(blockPos, blockPos);
	}
	
	public ArrayList<BlockPos> getZoneBlocks() {
		//Store all zone positions
		ArrayList<BlockPos> zonePositions = new ArrayList<BlockPos>();
		
		//Calculate all zone positions
		for(int x = Math.min(zone.get(0).getX(), zone.get(1).getX()); x <= Math.max(zone.get(0).getX(), zone.get(1).getX()); x++) {
			for(int y = Math.min(zone.get(0).getY(), zone.get(1).getY()); y <= Math.max(zone.get(0).getY(), zone.get(1).getY()); y++) {
				for(int z = Math.min(zone.get(0).getZ(), zone.get(1).getZ()); z <= Math.max(zone.get(0).getZ(), zone.get(1).getZ()); z++) {
					zonePositions.add(new BlockPos(x, y, z));
				}
			}
		}
		
		return zonePositions;
	}
	
	public boolean isBlockWithinZone(BlockPos blockPos) {
		//Check if the block is within the zone blocks
		for(BlockPos zoneBlock : this.getZoneBlocks()) {
			if(blockPos == zoneBlock) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isValidZoneBlock(BlockPos blockPos) {
		//Make sure the block is actually in the zone
		if(!this.isBlockWithinZone(blockPos)) {
			return false;
		}
		
		//Get block at selected coords
		Block mainBlock = Minecraft.getInstance().level.getBlockState(blockPos).getBlock();
		//Get block below
		Block baseBlock = Minecraft.getInstance().level.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock();
		//Check the block itself can contain the spawn (is air or non-collidable)
		for(Block scanBlock : validBlocks) {
			if(mainBlock == scanBlock) {
				for(Block bottomBlock : validBaseBlocks) {
					//Check the block underneath it can support it
					if(baseBlock == bottomBlock) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	public ArrayList<BlockPos> getValidZoneBlocks() {
		
		//Store all valid zone positions
		ArrayList<BlockPos> validZonePositions = new ArrayList<BlockPos>(); 
		
		for(BlockPos zonePosition : this.getZoneBlocks()) {
			if(this.isValidZoneBlock(zonePosition)) {
				validZonePositions.add(zonePosition);
			}
		}
		
		return validZonePositions;
	}
	
	public BlockPos getRandValidZoneBlock() {
		//Store all valid zone positions
		ArrayList<BlockPos> validZonePositions = this.getValidZoneBlocks();
		
		//If the zone has no valid positons
		if(!(validZonePositions.size() > 0)) {
			//Return a theoretically invalid position (even with Cubic Chunks mod or other), indicating the zone has no valid blocks
			//return new BlockPos(0, Integer.MIN_VALUE, 0);
			return null;
		}
		
		int randElement = new Random().nextInt(validZonePositions.size() - 1);
		
		return new BlockPos(validZonePositions.get(randElement));
	}
	
	protected final void spawnEvent() {
		
	}
	
	
}
