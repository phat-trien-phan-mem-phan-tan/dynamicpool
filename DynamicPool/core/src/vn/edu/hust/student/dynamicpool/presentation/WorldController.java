package vn.edu.hust.student.dynamicpool.presentation;

import java.util.*;

import vn.edu.hust.student.dynamicpool.*;
import vn.edu.hust.student.dynamicpool.bll.*;
import vn.edu.hust.student.dynamicpool.dal.server.logic.*;
import vn.edu.hust.student.dynamicpool.dal.utils.*;
import vn.edu.hust.student.dynamicpool.model.*;
import vn.edu.hust.student.dynamicpool.presentation.assets.*;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.*;
import vn.edu.hust.student.dynamicpool.presentation.screen.*;

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
	private DeviceInfoScreen deviceInfoScreen;
	private FishUICollection fishUICollection = new FishUICollection();
	private int addingFishStep = 0;
	private FishType selectedFishType = FishType.FISH1;
	private float size;
	private String errorMessage = "";
	private PresentationVoidCallback newClientRegisterEventCallback = new PresentationVoidCallback() {
		@Override
		public void callback() {
			newClientRegisterCallbackHander();
		}
	};

	public WorldController(GameCenter game) {
		this.game = game;
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
		creatClientBusinessLogicLayer();
		PresentationBooleanCallback callback = new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {
				joinHostCallbackHander(isSuccess, error);
			}
		};
		this.businessLogicLayer.joinHost(key, callback);
		showLoadingScreen();
	}

	private void creatClientBusinessLogicLayer() {
		this.businessLogicLayer = new BusinessLogicLayerImpl();
	}

	protected void joinHostCallbackHander(boolean isSuccess, Exception error) {
		if (isSuccess) {
			loadDeviceInfoScreenResource();
			showDeviceInforScreen();
			loadGameResources();
		} else {
			setErrorMessage(error == null ? "Cannot join host" : error
					.getMessage());
			showMainMenuScreen();
		}
	}

	private void showGameScreen() {
		game.setScreen(gameScreen);
	}

	private void showLoadingScreen() {
		game.setScreen(loadingScreen);
	}

	private void showFullScreen() {
		/*
		 * DisplayMode desktopDisplayMode =
		 * Gdx.graphics.getDesktopDisplayMode();
		 * Gdx.graphics.setDisplayMode(desktopDisplayMode.width,
		 * desktopDisplayMode.height, true);
		 */
	}

	private void loadGameResources() {
		Assets.instance.initGameAssets();
		WorldRenderer worldRenderer = game.getWorldRenderer();
		gameScreen = new GameScreen(worldRenderer, this);
	}

	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void createHost() {
		createHostBusinessLogicLayer();
		PresentationBooleanCallback callback = new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {
				createHostCallbackHander(isSuccess, error);
			}
		};
		this.businessLogicLayer.createHost(callback);
		showLoadingScreen();
	}

	private void createHostBusinessLogicLayer() {
		this.businessLogicLayer = new BusinessLogicLayerServerImpl(
				this.newClientRegisterEventCallback);
	}

	protected void createHostCallbackHander(boolean isSuccess, Exception error) {
		System.out.println("Create host callback: " + isSuccess);
		if (isSuccess) {
			loadDeviceInfoScreenResource();
			showDeviceInforScreen();
			loadHostGameResources();
		} else {
			setErrorMessage(error == null ? "Cannot create host" : error
					.getMessage());
//			showMainMenuScreen();
		}
	}

	private void loadHostGameResources() {
		Assets.instance.initGameAssets();
		WorldRenderer worldRenderer = game.getWorldRenderer();
		gameScreen = new HostGameScreen(worldRenderer, this);
	}

	private void loadDeviceInfoScreenResource() {
		WorldRenderer worldRenderer = game.getWorldRenderer();
		deviceInfoScreen = new DeviceInfoScreen(worldRenderer, this);
	}

	private void showDeviceInforScreen() {
		game.setScreen(deviceInfoScreen);
	}

	public boolean saveScreenSizeByInch(String screenSize) {
		if (isValidScreenSize(screenSize)) {
			sendDeviceInfoToServer();
			showFullScreen();
//			showLoadingScreen();
			return true;
		}
		return false;
	}

	private void sendDeviceInfoToServer() {
		DisplayMode desktopDisplayMode = Gdx.graphics.getDesktopDisplayMode();
		PresentationBooleanCallback sendDeviceInfoCallback = new PresentationBooleanCallback() {
			@Override
			public void callback(boolean isSuccess, Exception error) {
				showGameScreen();
			}
		};
		DeviceInfo deviceInfo = new DeviceInfo(desktopDisplayMode.width,
				desktopDisplayMode.height, this.size);
		businessLogicLayer.addDevide(deviceInfo, sendDeviceInfoCallback);
	}

	private boolean isValidScreenSize(String screenSize) {
		try {
			size = Float.parseFloat(screenSize);
			if (size < 0 || size > 30)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
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

	public void addFishButtonClick() {
		if (this.addingFishStep == 0) {
			this.addingFishStep = 1;
		} else {
			this.addingFishStep = 0;
		}
		InputProcessor selectFishInputProcessor = gameScreen
				.getSelectFishInputProcessor();
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
		InputProcessor selectTrajectoryInputProcessor = gameScreen
				.getSelectTrajectoryInputProcessor();
		this.setGameInputProcessor(selectTrajectoryInputProcessor);
	}

	public boolean isShowSelectTrajectoryButtons() {
		return addingFishStep == 2;
	}

	public void selectTrajectory(ETrajectoryType trajectoryType) {
		createFish(selectedFishType, trajectoryType);
		cancelAddFish();
	}

	private void createFish(FishType fishType, ETrajectoryType trajectoryType) {
		businessLogicLayer.createFish(fishType, trajectoryType,
				FishUIFactory.getWith(fishType),
				FishUIFactory.getHeight(fishType));
	}

	protected void newClientRegisterCallbackHander() {
		System.out.println("new client registered");
	}

	public String getKey() {
		return businessLogicLayer.getKeyOfHost();
	}
}