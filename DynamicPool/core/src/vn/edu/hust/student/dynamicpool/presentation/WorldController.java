package vn.edu.hust.student.dynamicpool.presentation;

import vn.edu.hust.student.dynamicpool.GameCenter;
import vn.edu.hust.student.dynamicpool.presentation.screen.SplashScreen;

import com.badlogic.gdx.utils.Timer;

public class WorldController {
	Timer timer = new Timer();
	GameCenter game;
	SplashScreen splashScreen;
	public WorldController(GameCenter game) {
		this.game = game;
	}
	
	public void init() {
		showSplashScreen();
	}
	
	public void showSplashScreen() {
		WorldRenderer worldRenderer = game.getWorldRenderer();
		splashScreen = new SplashScreen(worldRenderer);
		game.setScreen(splashScreen);
		waitFewSecondsAndShowMenus();
	}

	private void waitFewSecondsAndShowMenus() {
		
	}
}
