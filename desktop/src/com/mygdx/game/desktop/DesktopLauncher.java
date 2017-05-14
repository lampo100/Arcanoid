package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;

		ModelManager model = new ModelManager();
		Controller controller = new Controller(model);
		new LwjglApplication(new ArcanoidGame(model, controller), config);
	}
}
