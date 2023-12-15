package com.fexl.deckedout.commands;

public class GameCommands {
	//Commands
	//--------------------------OUTSIDE GAME ONLY--------------------
	// /do select_dungeon [file]						//select the dungeon layout to use
	// /do player [player]								//select the dungeon player
	// /do game start									//start the game
	// /dgn load [file]									//Load a dungeon from a json file
	// /dgn save [file]									//Save the dungeon to a json file (if no argument is given, it defaults to the file given upon loading)
	// /dgn zone pos1 									//set the first zone param
	// /dgn zone pos2 									//set the second zone param
	// /dgn zone [add/remove] [level] pos 				//removes or adds the zone defined by the coords (level isn't needed if the zone is being removed)
	// /dgn zone [add/remove] [level] x1 y1 z1 x2 y2 z2 //(level isn't needed if the zone is being removed)
	// /dgn zone search [inside/closest] 				//inside returns a zone if the player is standing within one, closest the closest zone
	// /dgn zone remove search 							//remove the zone that came up in the most recent search (doesn't work if search command isn't within some chat lines)
	
	
	//--------------------------IN GAME ONLY-------------------
	// /do game end										//end the game for the specified player
	// /do game treasure [decrement/increment]			//modify treasure values for game
	// /do game embers [decrement/increment]			//modify ember values for game
	// /do game clank [decrement/increment]				//modify clank values for game
	// /do game hazard [decrement/increment]			//modify hazard values for game
}
