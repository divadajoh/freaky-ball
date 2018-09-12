package com.davidhoja.freakyball.screen;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.davidhoja.freakyball.FallingBall;
import com.davidhoja.freakyball.animation.Animator;
import com.davidhoja.freakyball.entity.Background;
import com.davidhoja.freakyball.entity.Ball;
import com.davidhoja.freakyball.entity.Entity;
import com.davidhoja.freakyball.entity.Obstacle;
import com.davidhoja.freakyball.handler.DirtHandler;
import com.davidhoja.freakyball.handler.ObstacleHandler;
import com.davidhoja.freakyball.highscore.HighscoreSystem;
import com.davidhoja.freakyball.utils.StaticHelper;
import com.davidhoja.freakyball.utils.TextureContainer;

public class GameScreen implements Screen {

	// == PRIMITIVE VARIABLES == //
	public static int WIDTH = Gdx.graphics.getWidth();
	public static int HEIGHT = Gdx.graphics.getHeight();

	private int playerScore = 0;
	private int sum;
	private int highScore;
	private int highscorePosX = 500;
	private int highscorePosY = 1800;
	private int difficulty;

	private boolean useAccelerometer = false;
	private boolean introFinished = false;
	private boolean menuVisible = false;
	private boolean collision = false;
	private boolean isClassicGame = false;
	private boolean touchDown = false;

	private Texture dirtTexture;
	private Texture ballTexture;
	private Texture obstacleTexture;
	private Texture backgroundTexture;
	private Texture backgroundTwoTexture;

	// == INSTANCE VARIABLES == //

	private FallingBall creator;
	private Ball ball;
	private Texture background;
	private DirtHandler dHandler;
	private ObstacleHandler oHandler;
	private HighscoreSystem highscoreBot;

	private Stage deathStage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table deathTable;
	private TextButton menuButton;
	private TextButton againButton;
	private TextButtonStyle mtbs;
	private TextButtonStyle atbs;

	private Viewport viewport;
	private Camera mCamera;

	private Image diffImage;
	private Image scoreImage;
	private Image highscoreImage;

	private SpriteBatch painter;
	private BitmapFont font;
	private BitmapFont hsFont;
	private OrthographicCamera camera;
	private Sound obsSound = Gdx.audio.newSound(Gdx.files
			.internal("obsSound.wav"));
	public ArrayList<Background> holdsBackgrounds = new ArrayList<Background>();
	private GestureDetector gd;

	private Sound gameOverSound;


	

	// == CLASSIC GAME CONSTRUCTOR == //
	public GameScreen(FallingBall creator, int difficulty) {
		
		Gdx.graphics.setDisplayMode(1000, 1000, false);
		Gdx.graphics.setVSync(true);
		this.creator = creator;
		Entity.passGameScreenObject(this);
		Animator.passGameScreenObject(this);
		StaticHelper.father = this;

		this.difficulty = difficulty;
		isClassicGame = true;
        
		dirtTexture = new Texture(Gdx.files.internal("dirtSheet.png"));
		ballTexture = new Texture(Gdx.files.internal("ball.png"));
		obstacleTexture = new Texture(Gdx.files.internal("obstacle.png"));
		backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));
		backgroundTwoTexture = new Texture(
				Gdx.files.internal("background2.jpg"));
		
		

		holdsBackgrounds.add(new Background(0, 0, backgroundTexture));
		holdsBackgrounds.add(new Background(0, -HEIGHT, backgroundTwoTexture));

		scoreImage = new Image(TextureContainer.scoreimg);
		highscoreImage = new Image(TextureContainer.highscoreimg);

		ball = new Ball(0, 192);


		highscoreBot = new HighscoreSystem(true, difficulty);

		highScore = highscoreBot.getHighscore();

		dHandler = new DirtHandler(this, difficulty);
		oHandler = new ObstacleHandler(this, 15, difficulty);

		diffImage = StaticHelper.DIFFICULTY_IMAGE;
		diffImage.setBounds(70, 1100, diffImage.getWidth(),
				diffImage.getHeight());

		scoreImage.setX(200);
		scoreImage.setY(900);
		highscoreImage.setX(250);
		highscoreImage.setY(700);

		painter = new SpriteBatch();

		font = new BitmapFont();
		font.setScale(4, 4);

		hsFont = new BitmapFont();
		hsFont.setScale(4, 4);

		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1080, 1920);
		camera.update();

		mCamera = new OrthographicCamera();
		viewport = new FillViewport(1080, 1920, mCamera);

		gameOverSound = Gdx.audio.newSound(Gdx.files
				.internal("MainMenu/goSound.wav"));


	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for (int i = 0; i < holdsBackgrounds.size(); i++) {
			Background b = (Background) holdsBackgrounds.get(i);
			if (introFinished && ball.getY() > 249) {
				b.move();
			}

			b.draw();
		}


		painter.begin();
		painter.setProjectionMatrix(mCamera.combined);
		font.draw(painter, " " + playerScore, highscorePosX, highscorePosY);
		painter.end();

		if (isMenuVisible()) {
			painter.begin();
			painter.setProjectionMatrix(mCamera.combined);
			hsFont.draw(painter, "" + highScore, 750, 860);
			painter.end();
		}

		camera.update();

		ball.moveBall();

		ball.draw();

		if (playerScore > highScore) {
			highscoreBot.setHighscore(playerScore);
			font.setColor(Color.GREEN);
			hsFont.setColor(Color.GREEN);
		}
		
		oHandler.handle();
		
		dHandler.handle();

		if (menuVisible) {
			deathStage.act();
			deathStage.draw();
		}
		
		
		
		
		

	}

	public void resize(int width, int height) {
		viewport.update(width, height);

	}

	public void show() {
		deathStage = new Stage(viewport);

		Gdx.input.setCatchBackKey(true);
		atlas = new TextureAtlas("MainMenu/button.pack");
		skin = new Skin(atlas);

		deathTable = new Table();
		deathTable.setBounds(50, 500, 100, 100);

		mtbs = new TextButtonStyle();
		mtbs.up = skin.getDrawable("menubtn1");
		mtbs.pressedOffsetX = 1;
		mtbs.pressedOffsetY = -1;
		mtbs.font = new BitmapFont();

		atbs = new TextButtonStyle();
		atbs.up = skin.getDrawable("againbtn1");
		atbs.pressedOffsetX = 1;
		atbs.pressedOffsetY = -1;
		atbs.font = new BitmapFont();

		menuButton = new TextButton("", mtbs);
		menuButton.pad(20);
		againButton = new TextButton("", atbs);
		againButton.pad(20);

		menuButton.setX(50);
		againButton.setX(520);
		menuButton.setY(200);
		againButton.setY(200);

		deathStage.addActor(menuButton);
		deathStage.addActor(againButton);
		deathStage.addActor(deathTable);
		deathStage.addActor(diffImage);
		deathStage.addActor(scoreImage);
		deathStage.addActor(highscoreImage);

		menuButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (isMenuVisible())
					creator.gotoMainMenu();
			}
		});

		againButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (isMenuVisible())
					if (isClassicGame) {
						creator.relaunch();
						dispose();
					} else {
						dispose();
					}

			}
		});

	}

	public void hide() {

	}

	public void pause() {

	}

	public void resume() {

	}

	public void dispose() {
		againButton.remove();
		menuButton.remove();
		gameOverSound.stop();
		gameOverSound = null;
		dHandler = null;
		ball = null;

	}

	// == RANDOM GENERATION METHODS == //

	public int generateRandom(int startingNum, int endingNum) {
		Random rand = new Random();

		sum = startingNum + rand.nextInt(endingNum);
		return sum;
	}

	// == GETTERS AND SETTERS == //
	public Ball getBall() {
		return ball;
	}

	public void setUseAccelerometer(boolean accStatus) {
		useAccelerometer = accStatus;
	}

	public void addPlayerScore() {
		playerScore++;
	}

	public Texture getObstacleTexture() {
		return obstacleTexture;
	}

	public Texture getDirtTexture() {
		return dirtTexture;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public Camera getMCamera() {
		return mCamera;
	}

	public Texture getBallTexture() {
		return ballTexture;
	}

	private void initListener() {
		gd = new GestureDetector(new GestureListener() {
			@Override
			public boolean longPress(float x, float y) {
				return false;
			}

			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				return false;
			}

			@Override
			public boolean tap(float x, float y, int count, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean panStop(float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean zoom(float initialDistance, float distance) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pinch(Vector2 initialPointer1,
					Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		gd.setLongPressSeconds(1);
		Gdx.input.setInputProcessor(gd);
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public DirtHandler getDirtHandler() {
		return dHandler;
	}

	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}

	public Texture getBackgroundTwoTexture() {
		return backgroundTwoTexture;
	}

	public boolean hasIntroFinished() {
		return introFinished;
	}

	public void setIntroFinished(boolean introFinished) {
		this.introFinished = introFinished;
	}

	public FallingBall getCreator() {
		return creator;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void playScoreSound() {
		if (StaticHelper.IS_SOUND_ON) {
			obsSound.play(1);
		}
	}

	public void setMenuVisible(boolean menuVisible) {
		this.menuVisible = menuVisible;
	}

	public boolean isMenuVisible() {
		return menuVisible;
	}

	public Stage getDeathStage() {
		return deathStage;
	}

	public BitmapFont getScoreFont() {
		return font;
	}

	public int getHighscorePosX() {
		return highscorePosX;
	}

	public void setHighscorePosX(int highscorePosX) {
		this.highscorePosX = highscorePosX;
	}

	public int getHighscorePosY() {
		return highscorePosY;
	}

	public void setHighscorePosY(int highscorePosY) {
		this.highscorePosY = highscorePosY;
	}

	public Image getScoreImage() {
		return scoreImage;
	}

	public Image getHighscoreImage() {
		return highscoreImage;
	}

	public void playGameOverSound() {
		if (StaticHelper.IS_SOUND_ON) {
			gameOverSound.play();
		}
	}

	public void setCollisionStatus(boolean collision) {
		this.collision = collision;
	}

	public boolean isClassicGame() {
		return isClassicGame;
	}

	public synchronized ArrayList<Obstacle> getObstacleContainer() {
		ArrayList<Obstacle> oContainer = null;
		
		if (oHandler != null) {
			oContainer = oHandler.getObstacleContainer();
		}
		return oContainer;
	}


}
