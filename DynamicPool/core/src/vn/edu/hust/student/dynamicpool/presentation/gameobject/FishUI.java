package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import vn.edu.hust.student.dynamicpool.bll.IFish;

public abstract class FishUI {
	private IFish fishBLL;
	private float time;

	public FishUI(IFish fish) {
		this.fishBLL = fish;
	}

	public float getX() {
		return fishBLL.getPoint().getX();
	}

	public float getY() {
		return fishBLL.getPoint().getY();
	}

	public float getAngle() {
		return fishBLL.getPoint().getAngle();
	}

	public EDirection getDirection() {
		int a = (int) getAngle();
		a = ((a + 45) % 360) / 90;
		return EDirection.values()[a];
	}
	
	public float getScale() {
		return 1f;
	}
	public float getTime() {
		return time;
	}

	public abstract void init();
	public abstract void render(SpriteBatch batch);

	public void update(float deltaTime) {
		time += deltaTime;
		fishBLL.update(deltaTime);
	}
}