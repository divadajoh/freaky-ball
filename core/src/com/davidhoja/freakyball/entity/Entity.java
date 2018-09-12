package com.davidhoja.freakyball.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.davidhoja.freakyball.screen.GameScreen;

public abstract class Entity {

//	== PRIMITIVE VARIABLES == //
	protected float xPos;
	protected float yPos;
	protected float xMovingDelta;
	protected float yMovingDelta;
	protected static GameScreen father;
	
//	== INSTANCE VARIABLES == //
	protected SpriteBatch painter;
	protected Texture texture;
	protected Sprite sprite;
	
//	== KONSTRUCTOR == //
	public Entity() {
		painter = new SpriteBatch();
	}
	
	public void move() {
		
	}
	
	
	
//  == GETTERS AND SETTERS == //
	public float getX() {
		return xPos;
	}
	
	public float getY() {
		return yPos;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public SpriteBatch getPainter() {
		return painter;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos,sprite.getWidth(), sprite.getHeight());
	}
	
	public void setX(float xPos) {
		sprite.setX(xPos);
		this.xPos = xPos;
	}
	
	public void setY(float yPos) {
		sprite.setY(yPos);
		this.yPos = yPos;
	}
	
	public int getTextureWidth() {
		int textureWidth = sprite.getTexture().getWidth();
		return textureWidth;
	}
	
	public int getTextureHeight() {
		int textureHeight = texture.getHeight();
		return textureHeight;
	}
	
	public float getXMovingDelta() {
		return xMovingDelta;
	}
	
	public float getYMovingDelta() {
		return yMovingDelta;
	}
	
	public void setXMovingDelta(int xMovingDelta) {
		this.xMovingDelta = xMovingDelta;
	}
	
	public void setYMovingDelta(int yMovingDelta) {
		this.yMovingDelta = yMovingDelta;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
		sprite.setTexture(texture);
	}
	
	public float getSpriteWidth() {
		return sprite.getWidth();
	}
	
	
	// == STATIC METHODS == //
	
	public static void passGameScreenObject(GameScreen gs) {
		father = gs;
	}

	
	
	

}
