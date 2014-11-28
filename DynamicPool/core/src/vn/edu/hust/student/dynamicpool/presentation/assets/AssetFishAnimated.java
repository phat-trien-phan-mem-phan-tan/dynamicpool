package vn.edu.hust.student.dynamicpool.presentation.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetFishAnimated {
	private String rightSpritePath;
	private String leftSpritePath;
	private int rightItems;
	private int leftItems;
	private Texture rightTexture;
	private Texture leftTexture;
	private TextureRegion[] rightFrames;
	private TextureRegion[] leftFrames;
	private Animation rightAnimation;
	private Animation leftAnimation;
	private float frameDuration;

	public AssetFishAnimated(String rightSpritePath, int rightItems,
			String leftSpritePath, int leftItems, float frameDuration) {
		this.rightSpritePath = rightSpritePath;
		this.leftSpritePath = leftSpritePath;
		this.rightItems = rightItems;
		this.leftItems = leftItems;
		this.frameDuration = frameDuration;
	}

	public void load(AssetManager assetManager) {
		assetManager.load(rightSpritePath, Texture.class);
		assetManager.load(leftSpritePath, Texture.class);
	}

	public void bind(AssetManager assetManager) {
		rightTexture = assetManager.get(rightSpritePath);
		leftTexture = assetManager.get(leftSpritePath);
		bindRightTexture();
		bindLeftTexture();
	}

	private void bindRightTexture() {
		TextureRegion[][] tmp = TextureRegion.split(rightTexture,
				rightTexture.getWidth() / rightItems,
				rightTexture.getHeight());
		rightFrames = new TextureRegion[rightItems];
		for (int i = 0; i < rightItems; i++) {
			rightFrames[i] = tmp[0][i];
		}
		rightAnimation = new Animation(frameDuration, rightFrames);
	}

	private void bindLeftTexture() {
		TextureRegion[][] tmp2 = TextureRegion.split(leftTexture,
				leftTexture.getWidth() / leftItems,
				leftTexture.getHeight());
		leftFrames = new TextureRegion[leftItems];
		for (int i = 0; i < leftItems; i++) {
			leftFrames[i] = tmp2[0][i];
		}
		leftAnimation = new Animation(frameDuration, leftFrames);
	}

	public Animation getLeftAnimation() {
		return leftAnimation;
	}

	public Animation getRightAnimation() {
		return rightAnimation;
	}
}
