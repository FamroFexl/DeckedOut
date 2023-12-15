/*
 *Copyright (c) 2023 Famro Fexl
 * SPDX-License-Identifier: MIT
*/
package com.fexl.deckedout.game;

/**
 * Determines the game difficulty, which influences the {@link com.fexl.deckedout.game.treasure.Artifact} {@link com.fexl.deckedout.DOLevel}.
 */
public class Difficulty {
	//Game difficulty
	private Options difficulty;
	
	public enum Options {
		EASY,
		MEDIUM,
		HARD,
		DEADY,
		DEEPFROST;
	}
	
	public Difficulty(Options option) {
		difficulty = option;
	}
	
	public Options getDifficulty() {
		return difficulty;
	}
	
	
}
