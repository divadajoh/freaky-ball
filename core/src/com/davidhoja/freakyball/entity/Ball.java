package com.davidhoja.freakyball.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.davidhoja.freakyball.utils.ConsoleLog;
import com.davidhoja.freakyball.utils.StaticHelper;
import com.davidhoja.freakyball.utils.TextureContainer;

public class Ball extends Entity {
	
//  == PRIMITIVE VARIABLES == //
	
	private int ballAfterBounceLocation;
	private int rotationDelta;
	private boolean collisionAtRight;
	private boolean introDone = false;
	private boolean isVisible = true;
	public boolean introAtRigth;
	public int SPEED = 12;

	private long lastHit;
	
	
//  == INSTANCE VARIABLES == //

	
	
	public Ball(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		sprite = new Sprite(TextureContainer.ballTexture);
		sprite.setY(yPos);
		
		int generatedNum = father.generateRandom(0,2);
		
		if(generatedNum == 1) {
			this.xPos = 1100;
			xMovingDelta = -5;
			setRotationDelta(-4);
			setIntroAtRight(true);
		} else{
			this.xPos = -100;
			xMovingDelta = 5;
			setRotationDelta(4);
			setIntroAtRight(false);
		}
		
	}
	
	
	// == RESPONSIBLE FOR MOVING THE BALL == //
	
	
	public void moveBall() {
		
		if(Gdx.input.isTouched()){
			if(System.currentTimeMillis() - lastHit > 200){
				if(StaticHelper.WIDTH / 2 > Gdx.input.getX()) {
					xMovingDelta = -SPEED;
				} else {
					xMovingDelta = SPEED;
				}
			}
		}
		
		sprite.setX(xPos);
		xPos += xMovingDelta;
		
		sprite.setY(yPos);
		yPos += yMovingDelta;
	
		
		if(! introDone){
			handleIntro();
		}
		
	}
	
	public void draw() {
		if(isVisible){
		   painter.setProjectionMatrix(father.getCamera().combined);
		   painter.begin();
		   sprite.rotate(rotationDelta);
		   sprite.draw(painter);
		   painter.end();
		}
	}
	
	
//  == HANDLES THE INTRO WHEN THE BALL COMES IN == //
	private void handleIntro() {
		if(introAtRigth){
			if(xPos < 910 && xPos > 499) {
				if(yPos < 300) {
					yMovingDelta = 7;
				}else {
					father.setIntroFinished(true);
					yMovingDelta = 0;
				}	
				
				if(xPos == 500) {
					xMovingDelta = 0;
					introDone = true;
				}
			}  
			
		} else{
			
			if(xPos > 110 && xPos < 501) {
					if(yPos < 300) {
						yMovingDelta = 7;
						
					}else {
						father.setIntroFinished(true);
						yMovingDelta = 0;
					}
						
					if(xPos == 500) {
						xMovingDelta = 0;
						introDone = true;
					}
						
						
		    }	

			
		 }
	}
	

	
	
//  == GETTERS AND SETTERS == //
	public int getBallAfterBounceLocation() {
		return ballAfterBounceLocation;
	}
	
	public void setBallAfterBounceLocation(int loc){ 
		ballAfterBounceLocation = loc;
	}
	
	public void setCollisionAtRight(boolean ballCollidedAtRight) {
		collisionAtRight = ballCollidedAtRight;
		
	}
	
	public void setRotationDelta(int rotationDelta) {
		this.rotationDelta = rotationDelta;
	}
	
	public void setIntroAtRight(boolean introAtRight) {
		this.introAtRigth = introAtRight;
	}
	
	public void setSpeed(int SPEED) {
		this.SPEED = SPEED;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(sprite.getX(), sprite.getY(), sprite.getTexture().getWidth() - 40, sprite.getTexture().getHeight() - 30);
	}
	
	public void setLastHitTime(long lastHit) {
		this.lastHit = lastHit;
	}
	
}
