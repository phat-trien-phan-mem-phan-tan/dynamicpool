package vn.edu.hust.student.dynamicpool.presentation.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
