package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

public class AssetLoadingScreen {
	private BitmapFont defaultFont;
	public void load(AssetManager assetManager) {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		FreeTypeFontLoaderParameter size1Params = new FreeTypeFontLoaderParameter();
		size1Params.fontFileName = AppConst.DEFAUFT_FONT;
		size1Params.fontParameters.size =  AppConst.LOADING_FONT_SIZE;
		assetManager.load(AppConst.LOADING_FONT_NAME, BitmapFont.class, size1Params);
	}

	public void bind(AssetManager assetManager) {
		defaultFont = assetManager.get(AppConst.LOADING_FONT_NAME, BitmapFont.class);
	}
	
	public BitmapFont getDefaultFont() {
		return defaultFont;
	}
}
