package com.davidhoja.freakyball.animation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.davidhoja.freakyball.screen.GameScreen;

public class Animator implements ApplicationListener{
	protected int FRAME_COLUMNS;
	protected int FRAME_ROWS;
	protected float ANIMATION_SPEED;
	
	protected float xPos;
	protected float yPos;
	
	protected float stateTime;
	
	
	protected com.badlogic.gdx.graphics.g2d.Animation animation;
	protected Texture spritesheet;
	protected TextureRegion[] frames;
	protected SpriteBatch painter;
	protected TextureRegion currentFrame;
	protected static GameScreen father;
	
	
	public static float SPEED_FAST = 0.030f;
	public static float SPEED_MEDIUM = 0.050f;
	public static float SPEED_SLOW = 0.080f;
	public static float SPEED_VERY_SLOW = 0.10f;
	
	public Animator(Texture texture, int FRAME_COLUMNS, int FRAME_ROWS) {
		this.FRAME_COLUMNS = FRAME_COLUMNS;
		this.FRAME_ROWS = FRAME_ROWS;
		painter = new SpriteBatch();
		
		ANIMATION_SPEED = SPEED_SLOW;
		
		spritesheet = texture;
		
		TextureRegion[][] tmp = TextureRegion.split(spritesheet, spritesheet.getWidth() / FRAME_COLUMNS, spritesheet.getHeight() / FRAME_ROWS);
		
		frames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];
		int tempIndex = 0;
		
		 for (int i = 0; i < FRAME_ROWS; i++) {
	            for (int j = 0; j < FRAME_COLUMNS; j++) {
	                frames[tempIndex++] = tmp[i][j];
	            }
	        }
	        animation = new Animation(ANIMATION_SPEED, frames); 
	        stateTime = 0f; 
		
		
	}
	


	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render() {
	        stateTime += Gdx.graphics.getDeltaTime();           
	        currentFrame = animation.getKeyFrame(stateTime, true);
	        painter.begin();
	        painter.draw(currentFrame, xPos, yPos);            
	        painter.end();
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	
//  == GETTERS AND SETTERS == //
	public float getAnimationSpeed() {
		return ANIMATION_SPEED;
	}
	
	public void setAnimationSpeed(float animationSpeed) {
		this.ANIMATION_SPEED = animationSpeed;
	}
	
	public int getNumberOfColumns() {
		return FRAME_COLUMNS;
	}
	
	public int getNumberOfRows() {
		return FRAME_ROWS;
	}
	
	public void setX(float xPos) {
		this.xPos = xPos;
	}
	
	public void setY(float yPos) {
		this.yPos = yPos;
	}
	
	public float getX() {
		return this.xPos;
	}
	
	public float getY() {
		return this.yPos;
	}
	
	public static void passGameScreenObject(GameScreen gs) {
		father = gs;
	}
	



}
