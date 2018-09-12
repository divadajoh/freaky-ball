package com.davidhoja.freakyball.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidhoja.freakyball.screen.GameScreen;
import com.davidhoja.freakyball.utils.TextureContainer;

public class Dirt extends Entity {
	
	private int SPEED = 0;
	
	
//   == KONSTRUCTOR == //
	public Dirt(float xPos, float yPos) {

		this.xPos = xPos;
		this.yPos = yPos;
		texture = TextureContainer.dirtTexture;
		sprite = new Sprite(texture);
		SPEED = -10;
		sprite.setX(xPos);
		sprite.setY(yPos);

	}
	
	public void move() {
		sprite.setY(yPos);
		yPos += SPEED;
	}
	
	public void draw() {
		   painter.setProjectionMatrix(father.getCamera().combined);
		   painter.begin();
		   sprite.draw(painter);
		   painter.end();
	}
	
	public void setSpeed(int SPEED) {
		if(SPEED > 0) {
			SPEED = - SPEED;
		}
		this.SPEED  = SPEED;
	}
	

	
} 