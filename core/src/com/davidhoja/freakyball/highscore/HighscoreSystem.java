package com.davidhoja.freakyball.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.davidhoja.freakyball.utils.StaticHelper;
import com.davidhoja.freakyball.utils.TextureContainer;


public class HighscoreSystem {
	
//  == INSTANCE VARIABLES == //
	AntiCheat hackerKiller;
	Preferences prefs;
	Image diffImage;
	
//  == PRIMITIVE VARIABLES == //
	int ballSpeed = 0;
	int highscore = 0;
	
//  == CONSTANTS == //
	int DIFFICULTY;
	boolean CLASSIC_GAME;
	
//	== CONSTRUCTOR == //
	public HighscoreSystem(boolean classicGame, int difficulty) {
		CLASSIC_GAME = classicGame;
		DIFFICULTY = difficulty;
		
		
		prefs = Gdx.app.getPreferences("prefs"); 
		if(prefs.getInteger("hasPlayedBefore") == 0) {
			prefs.putInteger("hasPlayedBefore", 1);
			prefs.putInteger("easy", 1);
			prefs.putInteger("moderate", 1);
			prefs.putInteger("hard", 1);
			prefs.putInteger("extreme", 1);
			
			prefs.putInteger("aEasy", 1);
			prefs.putInteger("aModerate", 1);
			prefs.putInteger("aHard", 1);
			prefs.putInteger("aExtreme", 1);
			
			prefs.putInteger("usingAcc", 0);
			StaticHelper.USING_ACCELEROMETER = false;
		}
		initDifficulty();
		hackerKiller = new AntiCheat(getHighscore());
	}
	
	private void initDifficulty() {

		if(CLASSIC_GAME) {
			switch(DIFFICULTY) {
			case 1:
				ballSpeed = 14;
				diffImage = new Image(
						TextureContainer.easyimg);
				highscore = getValue("easy");
				
				break;
			case 2:
				ballSpeed = 15;
				diffImage = new Image(
						TextureContainer.moderateimg);
				highscore = getValue("moderate");
				break;
			case 3:
				ballSpeed = 16;
				diffImage = new Image(
						TextureContainer.hardimg);
				highscore = getValue("hard");
				break;
			case 4:
				ballSpeed = 17;
				diffImage = new Image(
						TextureContainer.extremeimg);
				highscore = getValue("extreme");
			
				break;
			}
		}else {
			switch(DIFFICULTY) {
			case 1:
				ballSpeed = 10;
				diffImage = new Image(
						TextureContainer.easyimg);
				highscore = getValue("aEasy");
				
				break;
			case 2:
				ballSpeed = 13;
				diffImage = new Image(
						TextureContainer.moderateimg);
				highscore = getValue("aModerate");
				break;
			case 3:
				ballSpeed = 16;
				diffImage = new Image(
						TextureContainer.hardimg);
				highscore = getValue("aHard");
				break;
			case 4:
				ballSpeed = 17;
				diffImage = new Image(
						TextureContainer.extremeimg);
				highscore = getValue("aExtreme");
				
				break;
			}
		}
		
		StaticHelper.BALL_SPEED = ballSpeed;
		StaticHelper.DIFFICULTY_IMAGE = diffImage;

	}
	
	
	
// == GETTERS AND SETTERS == //
	
	public int getValue(String ID) {
		return prefs.getInteger(ID);
	}
	
	public void setValue(String ID, int value) {
		prefs.putInteger(ID, value);
		prefs.flush();
	}
	
	public int getHighscore() {
		int highScore = 0;
		
		if(CLASSIC_GAME) {
			switch(DIFFICULTY) {
			case 1:
				highScore = getValue("easy");
				break;
			case 2:
				highScore = getValue("moderate");
				break;
			case 3:
				highScore = getValue("hard");
				break;
			case 4:
				highScore = getValue("extreme");
				break;
			}
		} else {
			switch(DIFFICULTY) {
			case 1:
				highScore = getValue("aEasy");
				break;
			case 2:
				highScore = getValue("aModerate");
				break;
			
			case 3:
				highScore = getValue("aHard");
				break;
			
			case 4:
				highScore = getValue("aExtreme");
				break;
			}
		}
		return highScore;
	}
	
	
public void setHighscore(int newHighscore) {
	if(hackerKiller.checkForHighscoreHacks(newHighscore)) {
		return;
	}
	
	if(CLASSIC_GAME) {
		switch(DIFFICULTY) {
		case 1:
			setValue("easy", newHighscore);
			break;
		
		case 2:

			setValue("moderate", newHighscore);
			break;
	
		case 3: 
			setValue("hard", newHighscore);
			break;
	
		case 4: 
			setValue("extreme", newHighscore);
			break;
		}
	} else {
		switch(DIFFICULTY) {
		case 1:
			setValue("aEasy", newHighscore);
			break;
			
		case 2:
			setValue("aModerate", newHighscore);
			break;
			
		case 3:
			setValue("aHard", newHighscore);
			break;
			
		case 4: 
			setValue("aExtreme", newHighscore);
			break;
		}
	}
}
	
	



}
