package vn.edu.hust.student.dynamicpool.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import vn.edu.hust.student.dynamicpool.GameCenter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dynamic Pool";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new GameCenter(), config);
	}
}