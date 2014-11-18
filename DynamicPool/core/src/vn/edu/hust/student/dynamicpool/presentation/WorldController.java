package vn.edu.hust.student.dynamicpool.presentation;

import java.util.List;

import vn.edu.hust.student.dynamicpool.GameCenter;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayer;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayerImpl;
import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUICollection;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUIFactory;
import vn.edu.hust.student.dynamicpool.presentation.screen.GameScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.LoadingScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.MainMenuScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.SplashScreen;
import vn.edu.hust.student.dynamicpool.tests.presentation.BLLTest;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class WorldController {
	private Timer timer = new Timer();
	private GameCenter game = null;
	private BusinessLogicLayer businessLogicLayer = null;
	private SplashScreen splashScreen = null;
	private MainMenuScreen mainMenuScreen = null;
	private LoadingScreen loadingScreen = null;
	private GameScreen gameScreen = null;
	private FishUICollection fishUICollection = new FishUICollection();
	private int addingFishStep = 0;
	private FishType selectedFishType = FishType.FISH1;

	public WorldController(GameCenter game) {
		this.game = game;
		this.businessLogicLayer = new BusinessLogicLayerImpl();
	}

	public void init() {
		showSplashScreen();
	}

	public void showSplashScreen() {
		WorldRenderer worldRenderer = game.getWorldRenderer();
		splashScreen = new SplashScreen(worldRenderer);
		game.setScreen(splashScreen);
		waitFewSecondsAndShowMenus();
	}

	private void waitFewSecondsAndShowMenus() {
		timer.scheduleTask(new Task() {
			@Override
			public void run() {
				showMainMenuScreen();
			}
		}, AppConst.DELAY_TIME);
		loadMainMenuScreenResources();
		loadLoadingResources();
	}

	private void loadMainMenuScreenResources() {
		Assets.instance.initMainMenuAssets();
		WorldRenderer worldRenderer = game.getWorldRenderer();
		mainMenuScreen = new MainMenuScreen(worldRenderer, this);
	}

	public void showMainMenuScreen() {
		game.setScreen(mainMenuScreen);
	}

	private void loadLoadingResources() {
		Assets.instance.initLoadingAssets();
		WorldRenderer worldRenderer = game.getWorldRenderer();
		loadingScreen = new LoadingScreen(worldRenderer);
	}

	public void joinHost(String key) {
		PresentationBooleanCallback callback = new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {
				joinHostCallbackHander(isSuccess, error);
			}
		};
		this.businessLogicLayer.joinHost(key, callback);
		showFullScreen();
		showLoadingScreen();
		loadGameResources();
	}

	protected void joinHostCallbackHander(boolean isSuccess, Exception error) {
		showGameScreen();
	}

	private void showGameScreen() {
		game.setScreen(gameScreen);
	}

	private void showLoadingScreen() {
		game.setScreen(loadingScreen);
	}

	private void showFullScreen() {
		DisplayMode desktopDisplayMode = Gdx.graphics.getDesktopDisplayMode();
		Gdx.graphics.setDisplayMode(desktopDisplayMode.width,
				desktopDisplayMode.height, true);
	}

	private void loadGameResources() {
		Assets.instance.initGameAssets();
		WorldRenderer worldRenderer = game.getWorldRenderer();
		gameScreen = new GameScreen(worldRenderer, this);
	}

	public void createHost() {
		PresentationBooleanCallback callback = new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {
				createHostCallbackHander(isSuccess, error);
			}
		};
		this.businessLogicLayer.createHost(callback);
		showFullScreen();
		showLoadingScreen();
		loadGameResources();
	}

	protected void createHostCallbackHander(boolean isSuccess, Exception error) {
		showGameScreen();
	}

	public FishUICollection getFishUICollection() {
		return fishUICollection;
	}

	public List<IFish> getFishs() {
		List<IFish> fishs = businessLogicLayer.getFishs();
		return fishs;
	}

	public void updateFishsCordinate(float deltaTime) {
		businessLogicLayer.update(deltaTime);
	}

	public void exit() {
		businessLogicLayer.exit();
		Gdx.app.exit();
	}

	public void createFish1() {
		businessLogicLayer.createFish(FishType.FISH1, ETrajectoryType.LINE,
				100, 100);
	}

	public void addFishButtonClick() {
		if (this.addingFishStep == 0) {
			this.addingFishStep = 1;
		} else {
			this.addingFishStep = 0;
		}
		InputProcessor selectFishInputProcessor = gameScreen.getSelectFishInputProcessor();
		this.setGameInputProcessor(selectFishInputProcessor);
	}

	public boolean isShowSelectFishButtons() {
		return addingFishStep == 1;
	}

	public void setGameInputProcessor(InputProcessor inputProcessor) {
		Gdx.input.setInputProcessor(inputProcessor);
	}

	public void cancelAddFish() {
		this.addingFishStep = 0;
		this.selectedFishType = FishType.FISH1;
	}

	public void selectFish(FishType fishType) {
		this.selectedFishType = fishType;
		this.addingFishStep = 2;
		InputProcessor selectTranjectoryInputProcessor = gameScreen.getSelectTranjectoryInputProcessor();
		this.setGameInputProcessor(selectTranjectoryInputProcessor);
	}

	public boolean isShowSelectTranjectoryButtons() {
		return addingFishStep == 2;
	}

	public void selectTranjectory(ETrajectoryType trajectoryType) {
		createFish(selectedFishType, trajectoryType);
		cancelAddFish();
	}

	private void createFish(FishType fishType, ETrajectoryType trajectoryType) {
		businessLogicLayer.createFish(fishType, trajectoryType,
				FishUIFactory.getWith(fishType),
				FishUIFactory.getHeight(fishType));
	}
}
