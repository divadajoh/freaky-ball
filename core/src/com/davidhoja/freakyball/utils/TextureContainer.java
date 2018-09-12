package com.davidhoja.freakyball.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TextureContainer {

	public static Texture backgroundTexture;
	public static Texture backgroundTextureTwo;
	public static Texture FreakyText;
	public static Texture ballText;
	public static Texture oAracadeTexture;
	public static Texture dirtTexture;
	public static Texture ballTexture;
	public static Texture obstacleTexture;
	public static Texture backgroundTwoTexture;
	
	public static Texture easyimg;
	public static Texture moderateimg;
	public static Texture hardimg;
	public static Texture extremeimg;
	public static Texture scoreimg;
	public static Texture highscoreimg;
	
	public static Texture coinTexture;
	

	
	static {
		backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));
		backgroundTextureTwo = new Texture(Gdx.files.internal("background2.jpg"));
		FreakyText = new Texture(Gdx.files.internal("MainMenu/freakytext.png"));
		ballText = new Texture(Gdx.files.internal("MainMenu/balltext.png"));
		
		easyimg = new Texture(Gdx.files.internal("DeathMenu/easyimg.png"));
		moderateimg = new Texture(Gdx.files.internal("DeathMenu/moderateimg.png"));
		hardimg = new Texture(Gdx.files.internal("DeathMenu/hardimg.png"));
		extremeimg = new Texture(Gdx.files.internal("DeathMenu/extremeimg.png"));
		scoreimg = new Texture(Gdx.files.internal("DeathMenu/scoreimg.png"));
		highscoreimg = new Texture(Gdx.files.internal("DeathMenu/highscoreimg.png"));
		
		oAracadeTexture = new Texture(Gdx.files.internal("arcadeObstacle.png"));
		
		
		dirtTexture = new Texture(Gdx.files.internal("dirtSheet.png"));
		ballTexture = new Texture(Gdx.files.internal("ball.png"));
		obstacleTexture = new Texture(Gdx.files.internal("obstacle.png"));
		backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));
		backgroundTwoTexture = new Texture(Gdx.files.internal("background2.jpg"));
		
		coinTexture = new Texture(Gdx.files.internal("coin_spritesheet.png"));
		
		ConsoleLog.add("TEXTURES LOADED", "The textures in the TextureContainer class have been loaded.");
		
		
	}

}
