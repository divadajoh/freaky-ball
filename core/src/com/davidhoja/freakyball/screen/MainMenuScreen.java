package com.davidhoja.freakyball.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.davidhoja.freakyball.utils.FileHandler;
import com.davidhoja.freakyball.utils.StaticHelper;
import com.davidhoja.freakyball.utils.TextureContainer;

public class MainMenuScreen implements Screen {
	
//  == INSTANCE VARIABLES == //
	private FallingBall creator;
	
	private Stage backgroundStage;
	private Stage foregroundStage;
	
	private Table difficultyTable;
	private Table menuTable;
	private Table settingsTable;
	
	private TextureAtlas atlas;
	private Skin skin;
	
	
	

	
	
	private TextButton easyButton;
	private TextButton moderateButton;
	private TextButton hardButton;
	private TextButton extremeButton;
	private TextButton classicButton;
	private TextButton arcadeButton;
	private TextButton playButton;
	private TextButton settingsButton;
	private TextButton backButton1;
	private TextButton backButton2;
	private TextButton backButton3;
	private TextButton soundButton;
	
	private TextButtonStyle etbs;
	private TextButtonStyle mtbs;
	private TextButtonStyle htbs;
	private TextButtonStyle extbs;
	private TextButtonStyle ctbs;
	private TextButtonStyle atbs;
	private TextButtonStyle playb;
	private TextButtonStyle settb;
	private TextButtonStyle backtbs;
	private TextButtonStyle ctb;
	private TextButtonStyle cab;
	private TextButtonStyle sonb;
	private TextButtonStyle soffb;
	
	private Viewport viewport;
	private Camera camera;
	private FileHandler fHandler;
	
	private Sound clickSound;

	
	private Image background;
	private Image backgroundTwo;
	private Image freakyImage;
	private Image ballImage;

	
	
//	== PRIMITIVE VARIABLES == //
	private boolean fromMMToDiffMenu = false;
	private boolean fromDiffMenuToMM = false;
	private boolean fromSettingsToMM = false;
	private boolean fromMMtoSettings = false;
	
	private boolean introTextAnimated;
	private boolean gameNameVisible = true;
	private boolean isSoundOn = true;
	private boolean inGame = false;
	
	private int WIDTH = StaticHelper.WIDTH;
	private int HEIGHT = StaticHelper.HEIGHT;
	
	private boolean startClassicGame = true;
	
	
//  == LISTS == //
	private ArrayList<Image> holdsBackgrounds = new ArrayList<Image>();
	
	public MainMenuScreen(FallingBall creator) {
		Gdx.graphics.setVSync(true);
		this.creator = creator;
		
		background = new Image(TextureContainer.backgroundTexture);
		backgroundTwo = new Image(TextureContainer.backgroundTextureTwo);
		freakyImage  = new Image(TextureContainer.FreakyText);
		ballImage = new Image(TextureContainer.ballText);
		
		
		background.setBounds(0, 0, WIDTH, HEIGHT);
		backgroundTwo.setBounds(0, -HEIGHT, WIDTH, HEIGHT);
		freakyImage.setBounds(1060, 1650, freakyImage.getWidth(), freakyImage.getHeight());
		ballImage.setBounds(- ballImage.getWidth(), 1500, 
							freakyImage.getWidth(), freakyImage.getHeight());
		
		
		holdsBackgrounds.add(background);
		holdsBackgrounds.add(backgroundTwo);

		
		camera = new OrthographicCamera();
		viewport = new FillViewport(1080, 1920, camera);
		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("MainMenu/click.wav"));
		
		fHandler = new FileHandler();
		
		
	}
	



	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		backgroundStage.act();
		backgroundStage.draw();
		handleBackground();
		
		foregroundStage.act();
		foregroundStage.draw();
		
		
		if(fromMMToDiffMenu) {
			mMenuToDiffMenuAnimation();
			animateTextUp();
		}else if(fromDiffMenuToMM) {
			diffMenuToMainMenuAnimation();
			animateTextDown();
		}else if(fromSettingsToMM) {
			animateTextDown();
			animateFromSettingsToMM();
		}else if(fromMMtoSettings) {
			animateTextUp();
			animateFromMMtoSettings();
		}
		
		if(! introTextAnimated) {
			animateText();
		}
	}


	public void resize(int width, int height) {

		viewport.update(width, height);


	}

	public void show() {
		foregroundStage = new Stage(viewport);
		backgroundStage = new Stage();

		
		Gdx.input.setCatchBackKey(true);
	
		Gdx.input.setInputProcessor(foregroundStage);
		
		atlas =new TextureAtlas(Gdx.files.internal("MainMenu/button.pack"));
		skin = new Skin(atlas);
		
		difficultyTable = new Table(skin);
		difficultyTable.setBounds(1080 + 200, 1920 / 2, 100, 100);
		
		
		settingsTable = new Table(skin);
		settingsTable.setBounds(1080 + 300, 1920 / 2, 100, 100);
		menuTable = new Table(skin);
		menuTable.setBounds(1080 / 2 - 64 , 1920 / 2, 100  , 100);
		
		
		
		
//		== TEXT BUTTON STYLES == //
		
		playb = new TextButtonStyle();
		playb.up = skin.getDrawable("playbutton1");
		playb.pressedOffsetX = 1;
		playb.pressedOffsetY = -1;
		playb.font = new BitmapFont();
		
		settb = new TextButtonStyle();
		settb.up = skin.getDrawable("settingsbutton1");
		settb.pressedOffsetX = 1;
		settb.pressedOffsetY = -1;
		settb.font = new BitmapFont();
		
		etbs = new TextButtonStyle();
		etbs.up = skin.getDrawable("easybtn1");
		etbs.pressedOffsetX = 1;
		etbs.pressedOffsetY = -1;
		etbs.font = new BitmapFont();
		
		mtbs = new TextButtonStyle();
		mtbs.up = skin.getDrawable("moderatebtn1");
		mtbs.pressedOffsetX = 1;
		mtbs.pressedOffsetY = -1;
		mtbs.font = new BitmapFont();
		
		htbs = new TextButtonStyle();
		htbs.up = skin.getDrawable("hardbtn1");
		htbs.pressedOffsetX = 1;
		htbs.pressedOffsetY = -1;
		htbs.font = new BitmapFont();
		
		extbs = new TextButtonStyle();
		extbs.up = skin.getDrawable("extremebtn1");
		extbs.pressedOffsetX = 1;
		extbs.pressedOffsetY = -1;
		extbs.font = new BitmapFont();
		

		ctbs = new TextButtonStyle();
		ctbs.up = skin.getDrawable("classicbtn1");
		ctbs.pressedOffsetX = 1;
		ctbs.pressedOffsetY = -1;
		ctbs.font = new BitmapFont();
		

		atbs = new TextButtonStyle();
		atbs.up = skin.getDrawable("arcadebtn1");
		atbs.pressedOffsetX = 1;
		atbs.pressedOffsetY = -1;
		atbs.font = new BitmapFont();
		
		backtbs = new TextButtonStyle();
		backtbs.up = skin.getDrawable("backbtn2");
		backtbs.pressedOffsetX = 1;
		backtbs.pressedOffsetY = -1;
		backtbs.font = new BitmapFont();
		
		
		sonb = new TextButtonStyle();
		sonb.up = skin.getDrawable("sOnButton");
		sonb.pressedOffsetX = 1;
		sonb.pressedOffsetY = -1;
		sonb.font = new BitmapFont();
		
		
		soffb = new TextButtonStyle();
		soffb.up = skin.getDrawable("sOffButton");
		soffb.pressedOffsetX = 1;
		soffb.pressedOffsetY = -1;
		soffb.font = new BitmapFont();
		
		
		
		
		
//		== BUTTONS == //
		
		easyButton = new TextButton("", etbs);
		easyButton.pad(20);
		moderateButton = new TextButton("", mtbs);
		moderateButton.pad(2);
		hardButton = new TextButton("", htbs);
		hardButton.pad(1);
		extremeButton = new TextButton("", extbs);
		extremeButton.pad(20);
		backButton1 = new TextButton("", backtbs);
		backButton1.pad(20);
		backButton2 = new TextButton("", backtbs);
		backButton2.pad(20);
		backButton3 = new TextButton("", backtbs);
		backButton3.pad(20);
		
		
		classicButton = new TextButton("", ctbs);
		classicButton.pad(20);
		arcadeButton = new TextButton("", atbs);
		arcadeButton.pad(20);
		
		playButton = new TextButton("", playb);
		playButton.pad(20);
		settingsButton = new TextButton("", settb);
		settingsButton.pad(20);
		
		
		
		difficultyTable.add(easyButton);
		difficultyTable.row();
		difficultyTable.add(moderateButton);
		difficultyTable.row();
		difficultyTable.add(hardButton);
		difficultyTable.row();
		difficultyTable.add(extremeButton);
		difficultyTable.row();
		difficultyTable.row();
		difficultyTable.add(backButton1);
		difficultyTable.debug();
		
		
		
		if(FileHandler.getBooleanValue("sound") == true) {
			soundButton = new TextButton("", sonb);		
		}else {
			soundButton = new TextButton("", soffb);
		}
		settingsTable.add(soundButton);
		settingsTable.row();
		settingsTable.add(backButton3);
		settingsTable.debug();
		
		
		
		menuTable.add(playButton);
		menuTable.row();
		menuTable.add(settingsButton);
		menuTable.debug();
		
	
		foregroundStage.addActor(menuTable);
		foregroundStage.addActor(settingsTable);
		foregroundStage.addActor(difficultyTable);
		foregroundStage.addActor(freakyImage);
		foregroundStage.addActor(ballImage);
		
		backgroundStage.addActor(background);
		backgroundStage.addActor(backgroundTwo);
		
		
		// == BUTTON HANDLING == //
		
		playButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				fromMMToDiffMenu = true;
				playClickSound();
			}
		});
		
		settingsButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				fromMMtoSettings = true;
				playClickSound();
			}
		});

		
		backButton1.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				fromDiffMenuToMM = true;
				playClickSound();
			}
		});
		
		
		backButton3.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				fromSettingsToMM = true;
				playClickSound();
			}
		});
		
		
		
		easyButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if(! inGame ){
					creator.startClassicGame(1);
				}

			}
		});
		
		moderateButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if(! inGame) {
					creator.startClassicGame(2);
				}
			}
		});
		
		hardButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if(! inGame) {
					creator.startClassicGame(3);
				}
			}
		});
		
		extremeButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if(! inGame) {
					creator.startClassicGame(4);
				}
			}
		});
	
//	   === SETTINGS BUTTONS ===  //
		soundButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if(StaticHelper.IS_SOUND_ON){
					soundButton.setStyle(soffb);
					StaticHelper.IS_SOUND_ON = false;
					FileHandler.putBooleanValue("sound", false);
				}else {
					soundButton.setStyle(sonb);
					StaticHelper.IS_SOUND_ON = true;
					FileHandler.putBooleanValue("sound", true);
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
		menuTable.remove();
		settingsTable.remove();
		difficultyTable.remove();
	}
	
//  == HANDLERS == //
	public void handleBackground() {
		
		for(Image i : holdsBackgrounds) {
			if(i.getY() > HEIGHT) {
				i.setY(- HEIGHT + 2);
			}
			
			i.setY(i.getY() + 2);
		}
	}
	
// == ANIMATIONS == //
		/*freakyImage.moveBy(0, 15);
		ballImage.moveBy(0, 15); */
	
	
	
	
	private void mMenuToDiffMenuAnimation() {
		if(difficultyTable.getX() > (540 - 64)){
			menuTable.moveBy(-30, 0);
			difficultyTable.moveBy(-30, 0);
		}else {
			fromMMToDiffMenu = false;
		}
		
	}
	
	private void diffMenuToMainMenuAnimation() {
		if(menuTable.getX() < (540 - 64)){
			menuTable.moveBy(30, 0);
			difficultyTable.moveBy(30, 0);
		}else {
			fromDiffMenuToMM = false;
		}
		
	}
	
	private void animateFromSettingsToMM() {
			if(menuTable.getX() < (540 - 64)) {
				settingsTable.moveBy(30, 0);
				menuTable.moveBy(30, 0);
			}else {
				fromSettingsToMM = false;	
				
			}
	}
	
	private void animateFromMMtoSettings() {
		if(settingsTable.getX() > (540 - 64)){
			menuTable.moveBy(-30, 0);
			settingsTable.moveBy(-30, 0);
			}else {
				fromMMtoSettings = false;
			}
	}
	
	private void animateText() { // 1700, 280
		if(freakyImage.getX()  > 280) {
			freakyImage.moveBy(-30, 0);
			ballImage.moveBy(30, 0);
		}else{
			introTextAnimated = true;
		}
		
	}
	
	private void animateTextUp() {
		if(ballImage.getY() < (2100)) {
			freakyImage.moveBy(0, 20);
			ballImage.moveBy(0, 20);
		}else {
			gameNameVisible = false;
		}
	}
	
	private void animateTextDown() {
		if(ballImage.getY() > 1500) {
			freakyImage.moveBy(0, -20);
			ballImage.moveBy(0, -20);
		} else {
			gameNameVisible = true;
		}
	}
	
	
	
// == SOUND == //
	private void playClickSound() {
		if(StaticHelper.IS_SOUND_ON){
		clickSound.play();
		}
	}
	
	
	

}
