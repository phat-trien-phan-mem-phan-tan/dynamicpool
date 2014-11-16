package vn.edu.hust.student.dynamicpool.presentation;

import vn.edu.hust.student.dynamicpool.GameCenter;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayer;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayerImpl;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.screen.LoadingScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.MainMenuScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.SplashScreen;
import vn.edu.hust.student.dynamicpool.tests.presentation.BLLTest;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class WorldController {
	private Timer timer = new Timer();
	private GameCenter game;
	private BusinessLogicLayer businessLogicLayer;
	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private Screen loadingScreen;
	
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
	}

	protected void joinHostCallbackHander(boolean isSuccess, Exception error) {
		showLoadingScreen();
	}

	private void showLoadingScreen() {
		game.setScreen(loadingScreen);
	}

	public void createHost() {
		PresentationBooleanCallback callback = new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {
				createHostCallbackHander(isSuccess, error);
			}
		};
		this.businessLogicLayer.createHost(callback);
	}

	protected void createHostCallbackHander(boolean isSuccess, Exception error) {
		showLoadingScreen();
	}
}
