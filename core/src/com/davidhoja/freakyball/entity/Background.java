package com.davidhoja.freakyball.entity;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Background extends Entity {
	
// == LISTS == //
	
	
	public Background(int xPos, int yPos, Texture texture) {
		Texture text = texture;
		sprite = new Sprite(text);
		sprite.setX(xPos);
		sprite.setY(yPos);
	}
	
	public void move(){ 
		sprite.setY((sprite.getY() + 4));
		
		if(sprite.getY() > father.HEIGHT) {
			sprite.setY(- father.HEIGHT +4);
		}
	}
	
	public void draw() {
		painter.begin();
		painter.draw(sprite, sprite.getX(), sprite.getY(), father.WIDTH, father.HEIGHT);
		painter.end();
	}
	
}
