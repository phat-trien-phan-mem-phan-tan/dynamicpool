package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetSplashScreen {
	private Texture splashScreenTexture;
	
	public AssetSplashScreen() {
		
	}
	
	public void load(AssetManager assetManager) {
		assetManager.load(AppConst.SPLASH_SCREEN_TEXTURE, Texture.class);
	}
	
	public void bind(AssetManager assetManager) {
		this.splashScreenTexture = assetManager.get(AppConst.SPLASH_SCREEN_TEXTURE);
	}

	public Texture getSplashScreenTexture() {
		return splashScreenTexture;
	}	
}
