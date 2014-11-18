package vn.edu.hust.student.dynamicpool.presentation;

import java.util.List;

import vn.edu.hust.student.dynamicpool.GameCenter;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayer;
import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUICollection;
import vn.edu.hust.student.dynamicpool.presentation.screen.GameScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.LoadingScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.MainMenuScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.SplashScreen;
import vn.edu.hust.student.dynamicpool.tests.presentation.BLLTest;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
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

	public WorldController(GameCenter game) {
		this.game = game;
		this.businessLogicLayer = new BLLTest();
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
//		Gdx.graphics.setDisplayMode(desktopDisplayMode.width,
//				desktopDisplayMode.height, true);
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
		businessLogicLayer.createFish(FishType.FISH1, ETrajectoryType.LINE, 100, 100);
	}
}
