package com.davidhoja.freakyball.handler;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.davidhoja.freakyball.entity.Ball;
import com.davidhoja.freakyball.entity.Dirt;
import com.davidhoja.freakyball.screen.GameScreen;

public class DirtHandler {
	
//	== PRIMITIVE VARIABLES == //
	private int startingYPos = 0;
	private int WIDTH = Gdx.graphics.getWidth();
	private int HEIGHT = Gdx.graphics.getHeight();
	
	private int dirtLength = 244;
	
//	== INSTANCE VARIABLES == //
	private GameScreen father;
	private Ball ball;

//  == PRIMITIVE VARIABLES == //
	private int SPEED = 0;
	
//  == CONTAINERS == //
	private ArrayList<Dirt> holdsDirt;
	
	public DirtHandler(GameScreen father, int diffLevel) {
		this.father = father;
		holdsDirt = new ArrayList<Dirt>();	
		boolean isOnRightSide = true;
		
		this.ball = father.getBall();
		
		if(father.isClassicGame()){
		
			switch(diffLevel) {
			case 1:
				SPEED = 11;
				break;
			case 2:
				SPEED = 13;
				dirtLength = 242;
				break;
			case 3:
				SPEED = 15;
				dirtLength = 242;
				break;
			case 4:
				SPEED = 18;
				break;
			}
		
		}
		
		float dirtCount = 11;
		for(int dI = 0; dI <= dirtCount; dI++) {	
			Dirt dirt = new Dirt(0,0);
			holdsDirt.add(dirt);
			
			dirt.setSpeed(SPEED);
			
			if(dI == 0 || dI == 1) {
				startingYPos = 255;
				dirt.setTexture(new Texture(Gdx.files.internal("dirtGrass.png")));
			}else {
				startingYPos += dirtLength; // 255
				dirt.setTexture(father.getDirtTexture());
			}
		
			
			if(isOnRightSide) {
				dirt.setX(952);
				dirt.setY(startingYPos);
				isOnRightSide = false;
			}else {
				dirt.setX(0);
				dirt.setY(startingYPos);
				isOnRightSide = true;
			}
			
			
		}
	}
	
	public void handle() {
		
			for(int i = 0; i < holdsDirt.size(); i++){
				Dirt dirt = holdsDirt.get(i);
				dirt.draw();
				
				
				if(father.hasIntroFinished() && father.getBall().getY() > 249){
				   dirt.move();
				  
				   
					if(dirt.getY() < - 500) {
					dirt.setY(2000);
					}
				}

				
				
				if(ball.getX() > 890) {
					father.setUseAccelerometer(false);
					ball.setXMovingDelta(-20);
					
					ball.setBallAfterBounceLocation(
						father.generateRandom(0 + dirt.getTextureWidth(), 
						WIDTH - dirt.getTextureWidth() * 2));
					ball.setCollisionAtRight(true);
					father.setCollisionStatus(false);
					ball.setLastHitTime(System.currentTimeMillis());
					
				}else if(ball.getX() < 110) {
					father.setUseAccelerometer(false);
					ball.setXMovingDelta(20);
					ball.setBallAfterBounceLocation(
							father.generateRandom(0 + dirt.getTextureWidth() * 2, 
							WIDTH - dirt.getTextureWidth() - ball.getTextureWidth()));
					ball.setCollisionAtRight(false);
					father.setCollisionStatus(false);
					ball.setLastHitTime(System.currentTimeMillis());
				}
				
				
	
		}
	}



}
