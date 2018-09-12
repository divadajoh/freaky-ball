package com.davidhoja.freakyball.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.davidhoja.freakyball.screen.GameScreen;
import com.davidhoja.freakyball.utils.TextureContainer;

public class Obstacle extends Entity {
	
//  == PRIMITIVE VARIABLES == //
	private int SPEED = 0;
	private boolean isOnRight = false;
	
	public Obstacle(int xPos, int yPos, boolean isClassicGame) {
		this.xPos = xPos;
		this.yPos = yPos;
			sprite = new Sprite(TextureContainer.obstacleTexture);
		sprite.setX(xPos);
		SPEED = -10;
		
	}
	
	public void move() {
		sprite.setY(yPos);
		yPos += SPEED;
//		draw();
		
	}
	
	public void draw() {
		   painter.setProjectionMatrix(father.getCamera().combined);
		   painter.begin();
		   sprite.draw(painter);
		   painter.end();
	}
//  == GETTERS AND SETTERS == //
	public void setSpeed(int SPEED) {
		if(SPEED > 0) {
			SPEED = - SPEED;
		}
		
		this.SPEED= SPEED;
	}
	
	
	public void setIsOnRight(boolean isOnRight) {
		this.isOnRight = isOnRight;
	}
	
	public int getSpeed() {
		return SPEED;
	}
	
	public boolean isOnRight() {
		boolean isRight = false;
		if(xPos > 200) {
			isRight = true;
		}
		return isRight;
	}
}
