package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetMainMenuScreen {
	private TextureRegionDrawable mainMenuBackgroundDrawable;
	private TextureRegionDrawable createHostDrawable;
	private TextureRegionDrawable joinHostDrawable;

	public void load(AssetManager assetManager) {
		assetManager.load(AppConst.MAIN_MEMU_BACKGROUND_TEXTURE, Texture.class);
		assetManager.load(AppConst.CREATE_HOST_BTN_TEXTURE, Texture.class);
		assetManager.load(AppConst.JOIN_HOST_BTN_TEXTURE, Texture.class);
	}

	public void bind(AssetManager assetManager) {
		bindMainMenuBackground(assetManager);
		bindCreateHost(assetManager);
		bindJoinHost(assetManager);
	}

	private void bindMainMenuBackground(AssetManager assetManager) {
		Texture mainMenuBackgroundTexture = assetManager.get(AppConst.MAIN_MEMU_BACKGROUND_TEXTURE);
		TextureRegion region = new TextureRegion(mainMenuBackgroundTexture);
		mainMenuBackgroundDrawable = new TextureRegionDrawable(region);
	}
	
	private void bindCreateHost(AssetManager assetManager) {
		Texture createHostTexture = assetManager.get(AppConst.CREATE_HOST_BTN_TEXTURE);
		TextureRegion region = new TextureRegion(createHostTexture);
		createHostDrawable = new TextureRegionDrawable(region);
	}
	
	private void bindJoinHost(AssetManager assetManager) {
		Texture joinHostTexture = assetManager.get(AppConst.JOIN_HOST_BTN_TEXTURE);
		TextureRegion region = new TextureRegion(joinHostTexture);
		joinHostDrawable = new TextureRegionDrawable(region);
	}

	public TextureRegionDrawable getMainMenuBackgroundDrawable() {
		return mainMenuBackgroundDrawable;
	}
	
	public TextureRegionDrawable getCreateHostDrawable() {
		return createHostDrawable;
	}
	
	public TextureRegionDrawable getJoinHostDrawable() {
		return joinHostDrawable;
	}
}
