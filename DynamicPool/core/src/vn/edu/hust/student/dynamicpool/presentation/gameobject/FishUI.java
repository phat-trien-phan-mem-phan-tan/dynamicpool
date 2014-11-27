package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.model.IFish;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class FishUI {
	private IFish fishBLL;
	private float time;

	public FishUI(IFish fish) {
		this.fishBLL = fish;
	}

	public float getX() {
		return fishBLL.getBoundary().getMinX();
	}

	public float getY() {
		return fishBLL.getBoundary().getMinY();
	}

	public float getAngle() {
		return fishBLL.getAngle();
	}

	public EDirection getDirection() {
		int a = (int) getAngle();
		a = a % 360;
		if (a == 90) return EDirection.TOP;
		if (a == 270) return EDirection.BOTTOM;
		if (a > 90 && a < 270) return EDirection.LEFT;
		return EDirection.RIGHT;
	}
	
	public float getScale() {
		return 1f;
	}
	public float getTime() {
		return time;
	}

	public abstract void initFishAsset();
	public abstract void render(SpriteBatch batch);

	public void update(float deltaTime) {
		time += deltaTime;
		fishBLL.update(deltaTime);
	}
}
