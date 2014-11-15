package vn.edu.hust.student.dynamicpool.presentation;

import vn.edu.hust.student.dynamicpool.GameCenter;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayer;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayerImpl;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.screen.MainMenuScreen;
import vn.edu.hust.student.dynamicpool.presentation.screen.SplashScreen;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class WorldController {
	private Timer timer = new Timer();
	private GameCenter game;
	private BusinessLogicLayer businessLogicLayer;
	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	
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
		loadMainMenuScreenResources();
		timer.scheduleTask(new Task() {	
			@Override
			public void run() {
				showMainMenuScreen();
			}
		}, AppConst.DELAY_TIME);
	}

	private void loadMainMenuScreenResources() {
		Assets.instance.initMainMenuAssets();
		WorldRenderer worldRenderer = game.getWorldRenderer();
		mainMenuScreen = new MainMenuScreen(worldRenderer);
	}

	public void showMainMenuScreen() {
		game.setScreen(mainMenuScreen);
	}
}
