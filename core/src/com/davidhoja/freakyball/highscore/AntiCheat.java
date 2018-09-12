package com.davidhoja.freakyball.highscore;

import com.davidhoja.freakyball.utils.ConsoleLog;

public class AntiCheat {
//	== PRIMITIVE VARIABLES == //
	private int OLD_HIGHSCORE = 0;
	private int NEW_HIGHSCORE = 0;
	
	public AntiCheat(int currentHighscore) {
		OLD_HIGHSCORE = currentHighscore;
	} 
	
	public boolean checkForHighscoreHacks(int NEW_HIGHSCORE) {
		boolean isHacking = false;
			
			if(NEW_HIGHSCORE - OLD_HIGHSCORE > 2) {
				isHacking = true;
				ConsoleLog.add("SCORE HACK DETECTED", "Score jumped from " 
								+ OLD_HIGHSCORE + " to " + NEW_HIGHSCORE);
			}else {
				OLD_HIGHSCORE = NEW_HIGHSCORE - 1;
			}
		
		return isHacking;
	} 
	

}
