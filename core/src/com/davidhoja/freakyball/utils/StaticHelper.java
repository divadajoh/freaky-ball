package com.davidhoja.freakyball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.davidhoja.freakyball.screen.GameScreen;

public class StaticHelper {
	
//  == INTERGERS == //
	public static int WIDTH;
	public static int HEIGHT;
	public static int BALL_SPEED;
	public static int OBSTACLE_SPEED;
	
//  == BOOLEANS == //
	public static boolean USING_ACCELEROMETER;
	public static boolean IS_SOUND_ON;

//  == INSTANCE VARIABLES == //
	public static Image DIFFICULTY_IMAGE;
	public static GameScreen father;
	
	
	static {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		father = null;
		
		BALL_SPEED = 0;
		OBSTACLE_SPEED = 0;
		DIFFICULTY_IMAGE = null;	

	}
}
