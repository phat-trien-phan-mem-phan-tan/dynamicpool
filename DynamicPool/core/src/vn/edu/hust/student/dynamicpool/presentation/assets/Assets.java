package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	private AssetFishAnimated fish1;
	public AssetBackgroundPool background = new AssetBackgroundPool();
	public AssetSplashScreen splashScreen = new AssetSplashScreen();
	public AssetMainMenu mainMenu = new AssetMainMenu();

	// Singleton: Xem lai dinh nghia trong Java nhe
	private Assets() {
//		fish1 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_1_TEXTURE, 8,
//				AppConst.VERTICAL_FISH_1_TEXTURE, 8,
//				AppConst.FISH_ANIMATED_DURATION);
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
		mainMenu.load(assetManager);
		assetManager.finishLoading();
		mainMenu.bind(assetManager);
	}

	public void loadGameAssets() {
		loadAssets();
		assetManager.finishLoading();
		bindAssets();
	}

	private void loadAssets() {
		background.load(assetManager);
		fish1.load(assetManager);
	}

	private void bindAssets() {
		background.bind(assetManager, 0, 0, 800, 480);
		fish1.bind(assetManager);
	}

	public AssetBackgroundPool getBackgroundAsset() {
		return background;
	}

	public AssetFishAnimated getFish1Asset() {
		return fish1;
	}

	@Override
	public void dispose() {
		// Xoa du lieu khoi bo nho
		assetManager.dispose();
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		Gdx.app.error(TAG, "Khong the load '" + asset.fileName + "'",
				(Exception) throwable);
	}

	public class AssetBackgroundPool {
		public Texture mainBackgroundTexture;
		public TextureRegion mainBackgroundRegion;

		public AssetBackgroundPool() {
		}

		public void load(AssetManager assetManager) {
			assetManager.load(AppConst.BACKGROUND_TEXTURE, Texture.class);
		}

		public void bind(AssetManager assetManager, int x, int y, int width,
				int height) {
			mainBackgroundTexture = assetManager
					.get(AppConst.BACKGROUND_TEXTURE);
			mainBackgroundTexture.setFilter(TextureFilter.Linear,
					TextureFilter.Linear);
			mainBackgroundRegion = new TextureRegion(mainBackgroundTexture, x,
					y, width, height);
		}
	}

	public class AssetFishAnimated {
		private String horizontalPath;
		private String verticalPath;
		private int horizontalItems;
		private int verticalItems;
		private Texture hozizontalTexture;
		private Texture verticalTexture;
		private TextureRegion[] verticalFrames;
		private TextureRegion[] horizontalFrames;
		private Animation verticalAnimation;
		private Animation horizontalAnimation;
		private float frameDuration;

		public AssetFishAnimated(String horizontalPath, int horizontalItems,
				String verticalPath, int verticalItems, float frameDuration) {
			this.horizontalPath = horizontalPath;
			this.verticalPath = verticalPath;
			this.horizontalItems = horizontalItems;
			this.verticalItems = verticalItems;
			this.frameDuration = frameDuration;
		}

		public void load(AssetManager assetManager) {
			assetManager.load(horizontalPath, Texture.class);
			assetManager.load(verticalPath, Texture.class);
		}

		public void bind(AssetManager assetManager) {
			hozizontalTexture = assetManager.get(horizontalPath);
			verticalTexture = assetManager.get(verticalPath);
			bindHorizontal();
			bindVertical();
		}

		private void bindHorizontal() {
			TextureRegion[][] tmp = TextureRegion.split(hozizontalTexture,
					hozizontalTexture.getWidth() / horizontalItems,
					hozizontalTexture.getHeight());
			horizontalFrames = new TextureRegion[horizontalItems];
			for (int i = 0; i < horizontalItems; i++) {
				horizontalFrames[i] = tmp[0][i];
			}
			horizontalAnimation = new Animation(frameDuration, horizontalFrames);
		}

		private void bindVertical() {
			TextureRegion[][] tmp2 = TextureRegion.split(verticalTexture,
					verticalTexture.getWidth() / verticalItems,
					verticalTexture.getHeight());
			verticalFrames = new TextureRegion[verticalItems];
			for (int i = 0; i < verticalItems; i++) {
				verticalFrames[i] = tmp2[0][i];
			}
			verticalAnimation = new Animation(frameDuration, verticalFrames);
		}

		public Animation getVerticalAnimation() {
			return verticalAnimation;
		}

		public Animation getHorizontalAnimation() {
			return horizontalAnimation;
		}
	}
}