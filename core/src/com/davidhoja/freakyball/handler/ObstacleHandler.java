package com.davidhoja.freakyball.handler;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.davidhoja.freakyball.entity.Ball;
import com.davidhoja.freakyball.entity.Obstacle;
import com.davidhoja.freakyball.screen.GameScreen;

public class ObstacleHandler {

	// == PRIMITIVE VARIABLES == //
	private int sum;
	private int lastGeneratedNum;
	private boolean isOnRight = true;
	private int SPEED = 0;
	private int lastYPos = 0;
	private int lastPos = 0;
	
	private int yRandStartingNum = 500;
	private int yRandEndingNum = 200;
	private int xStartingNumLeft = 70;
	private int xStartingNumRigth = 440;
	private int xEndingNumLeft = 57;
	private int xEndingNumRight = 60;

	private int distanceVar = 0;

	// == INSTANCE VARIABLES == //
	private Ball ball;
	private GameScreen father;
	private Random rand;

	// == LISTS == //
	public volatile ArrayList<Obstacle> holdsObstacles;
	
	// CLASSIC GAME CONSTRUCTOR == //
	public ObstacleHandler(GameScreen father, int numOfObstacles, int diffLevel) {
		this.father = father;
		holdsObstacles = new ArrayList<Obstacle>();

		rand = new Random();
		ball = father.getBall();

		switch (diffLevel) {
		case 1:
			SPEED = 11;
			yRandStartingNum = 500;
			yRandEndingNum = 500;
			xStartingNumLeft = 30;
			xEndingNumLeft = 20;
			xStartingNumRigth = 500;
			xEndingNumRight = 20;
			break;
		case 2:
			SPEED = 13;
			yRandStartingNum = 700;
			yRandEndingNum = 200;
			xStartingNumLeft = 30;
			xEndingNumLeft = 20;
			xStartingNumRigth = 500;
			xEndingNumRight = 20;
			break;
		case 3:
			SPEED = 15;
			yRandStartingNum = 500;
			yRandEndingNum = 300;
			break;
		case 4:
			SPEED = 18;
			yRandStartingNum = 500;
			yRandEndingNum = 350;
			break;
		}
		
		lastPos = generateRandom(0, 2);
		for (int i = 0; i < numOfObstacles; i++) {

	
			if (i == 0) {
				lastYPos = 2000;
			}
			
			if(lastPos == 0) {// The obstacle is on the left.
				lastYPos += 800;
				Obstacle tempObstacle = new Obstacle(generateRandom(xStartingNumLeft,  xEndingNumLeft), lastYPos, false);
				tempObstacle.setSpeed(SPEED);
				holdsObstacles.add(tempObstacle);
				lastPos = 1;
			} else {
				lastYPos += 800;
				Obstacle tempObstacle = new Obstacle(generateRandom(xStartingNumRigth,  xEndingNumRight), lastYPos, false);
				tempObstacle.setSpeed(SPEED);
				holdsObstacles.add(tempObstacle);
				lastPos = 0;
			}
			


	/*
			switch (obstaclePosition) {
			case 0:
				lastYPos += 800;
	
				holdsObstacles.add(new Obstacle(-850, lastYPos, false));
				holdsObstacles.add(new Obstacle(350 + distanceVar, lastYPos,
						false));
				break;
	
			case 1:
				lastYPos += 800;
				holdsObstacles.add(new Obstacle(-800 - distanceVar, lastYPos,
						false));
				holdsObstacles.add(new Obstacle(400 + distanceVar, lastYPos,
						false));
				break;
	
			case 2:
				lastYPos += 800;
				holdsObstacles.add(new Obstacle(-700 - distanceVar, lastYPos,
						false));
				holdsObstacles.add(new Obstacle(500 + distanceVar, lastYPos,
						false));
				break;
	
			case 3:
				lastYPos += 800;
				holdsObstacles.add(new Obstacle(-600 - distanceVar, lastYPos,
						false));
				holdsObstacles.add(new Obstacle(600 + distanceVar, lastYPos,
						false));
				break;
	
			case 4:
				lastYPos += 800;
				holdsObstacles.add(new Obstacle(-500 - distanceVar, lastYPos,
						false));
				holdsObstacles.add(new Obstacle(700 + distanceVar, lastYPos,
						false));
				break;
	
			case 5:
				lastYPos += 800;
				holdsObstacles.add(new Obstacle(-400 - distanceVar, lastYPos,
						false));
				holdsObstacles.add(new Obstacle(800 + distanceVar, lastYPos,
						false));
				break;
	
			case 6:
				lastYPos += 800;
				holdsObstacles.add(new Obstacle(-300 - distanceVar, lastYPos,
						false));
				holdsObstacles.add(new Obstacle(900, lastYPos, false));
				break;
	
			}
	*/
		}
		
	}


	public void handle() {
		for (int i = 0; i < holdsObstacles.size(); i++) {
			Obstacle o = holdsObstacles.get(i);
			// == OBSTACLE TELEPORTING == //
			if (o.getY() < -100) {
				if (i == 0) {
					o.setY(holdsObstacles.get(holdsObstacles.size() - 1).getY()
							+ generateRandom(yRandStartingNum, yRandEndingNum));
				} else {
					o.setY(holdsObstacles.get(i - 1).getY()
							+ generateRandom(yRandStartingNum, yRandEndingNum));
				}

			}
			
			

			// == GAME OVER HANDLING == //
			if (!father.isMenuVisible())
				if (ball.getBounds().overlaps(o.getBounds())) {
					father.setMenuVisible(true); ball.setVisible(false);
					 Gdx.input.setInputProcessor(father.getDeathStage());
					 father.playGameOverSound(); father.setHighscorePosX(570);
					 father.setHighscorePosY(1046);
				}

			// == BALL MOVEMENT == //
			o.move();
			// == BALL DRAWING == //
			o.draw();

			// == SCORE INCREASED == //
			if (o.getY() > ball.getY() && o.getY() < ball.getY() + SPEED) {

				if (!father.isMenuVisible()) {
					father.playScoreSound();
					father.setPlayerScore(father.getPlayerScore() + 1);
				}
			}
		}

	}

	private int generateRandom(int startingNum, int endingNum) {
		sum = startingNum + rand.nextInt(endingNum);
		return sum;
	}

	public synchronized ArrayList<Obstacle> getObstacleContainer() {
		return holdsObstacles;
	}

}
