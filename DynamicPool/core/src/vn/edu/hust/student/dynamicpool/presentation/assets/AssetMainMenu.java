package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetMainMenu {
	
	private Texture mainMenuBackgroundTexture;
	private Texture createHostTexture;
	private Texture joinHostTexture;

	public void load(AssetManager assetManager) {
		assetManager.load(AppConst.MAIN_MEMU_BACKGROUND_TEXTURE, Texture.class);
		assetManager.load(AppConst.CREATE_HOST_BTN_TEXTURE, Texture.class);
		assetManager.load(AppConst.JOIN_HOST_BTN_TEXTURE, Texture.class);
	}

	public void bind(AssetManager assetManager) {
		mainMenuBackgroundTexture = assetManager.get(AppConst.MAIN_MEMU_BACKGROUND_TEXTURE);
		createHostTexture = assetManager.get(AppConst.CREATE_HOST_BTN_TEXTURE);
		joinHostTexture = assetManager.get(AppConst.JOIN_HOST_BTN_TEXTURE);
	}

	public Texture getMainMenuBackgroundTexture() {
		return mainMenuBackgroundTexture;
	}
	
	public Texture getCreateHostTexture() {
		return createHostTexture;
	}
	
	public Texture getJoinHostTexture() {
		return joinHostTexture;
	}
}
