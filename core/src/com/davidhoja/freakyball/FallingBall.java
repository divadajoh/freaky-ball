package com.davidhoja.freakyball;

import com.badlogic.gdx.Game;
import com.davidhoja.freakyball.screen.GameScreen;
import com.davidhoja.freakyball.screen.MainMenuScreen;
import com.davidhoja.freakyball.utils.ConsoleLog;
import com.davidhoja.freakyball.utils.FileHandler;
import com.davidhoja.freakyball.utils.StaticHelper;


public class FallingBall extends Game {

	// == STARTS THE GAME == //
	
	public GameScreen gameScreen;
	public MainMenuScreen mMenuScreen;
	
	private int diff = 0;
	
	public void create() {
//		gameScreen = new GameScreen(this);
		mMenuScreen = new MainMenuScreen(this);
		setScreen(mMenuScreen);
		initialize();
		
	}
	
	public void relaunch() {
		try{
		gameScreen = null;
		gameScreen = new GameScreen(this, diff);
		setScreen(gameScreen);
		} catch(Exception ex) {
			ConsoleLog.add("Relaunch() - Exception", ex.getMessage().toString());
		}
	}
	
	
	public void startClassicGame(int diff) {
		this.diff = diff;
		gameScreen = null;
		gameScreen = new GameScreen(this, diff);
		mMenuScreen.dispose();
		mMenuScreen = null;
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
			ConsoleLog.add("EXCEPTION", e.getMessage().toString());
		}
		setScreen(gameScreen);
	}
	
	public void gotoMainMenu() {
		mMenuScreen = null;
		mMenuScreen = new MainMenuScreen(this);
		setScreen(mMenuScreen);
	}
	
	
	private void initialize() {
		StaticHelper.IS_SOUND_ON = FileHandler.getBooleanValue("sound");
		StaticHelper.USING_ACCELEROMETER = FileHandler.getBooleanValue("usingAcc");
	}
	
	
}
