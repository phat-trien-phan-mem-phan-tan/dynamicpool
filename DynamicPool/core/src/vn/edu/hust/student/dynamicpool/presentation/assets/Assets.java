package vn.edu.hust.student.dynamicpool.presentation.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	public AssetSplashScreen splashScreen = new AssetSplashScreen();
	public AssetMainMenuScreen mainMenuScreen = new AssetMainMenuScreen();
	public AssetLoadingScreen loadingScreen = new AssetLoadingScreen();
	public AssetGameScreen gameScreen = new AssetGameScreen();

	private Assets() {
		
	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		initSplashScreenAssets(assetManager);
	}

	public void initSplashScreenAssets(AssetManager assetManager) {
		splashScreen.load(assetManager);
		assetManager.finishLoading();
		splashScreen.bind(assetManager);
	}

	public void initMainMenuAssets() {
		mainMenuScreen.load(assetManager);
		assetManager.finishLoading();
		mainMenuScreen.bind(assetManager);
	}

	public void initLoadingAssets() {
		loadingScreen.load(assetManager);
		assetManager.finishLoading();
		loadingScreen.bind(assetManager);
	}

	public void initGameAssets() {
		gameScreen.load(assetManager);
		assetManager.finishLoading();
		gameScreen.bind(assetManager);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Khong the load '" + asset.fileName + "'",
				(Exception) throwable);
	}
}