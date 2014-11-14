package vn.edu.hust.student.dynamicpool.presentation;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.BackgroundPool;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishOneUI;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUI;

public class WorldController {
	private static final String TAG = WorldController.class.getName();
	public BackgroundPool background;
	private List<FishUI> fishs;

	public WorldController() {
		init();
	}

	private void init() {
		background = new BackgroundPool();
		fishs = new ArrayList<FishUI>();
		initTest();
	}

	private void initTest() {
		Fish fish = new Fish();
		FishUI fishUI = new FishOneUI(fish);
		fishs.add(fishUI);
	}

	public void update(float deltaTime) {
		for (FishUI fishUI : fishs) {
			fishUI.update(deltaTime);
		}
	}
	
	public List<FishUI> getFishs() {
		return fishs;
	}
}
