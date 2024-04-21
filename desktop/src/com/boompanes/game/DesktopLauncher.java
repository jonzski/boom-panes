package com.boompanes.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.App;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		App app = new App();
		config.setForegroundFPS(App.FPS);
		config.setTitle(App.TITLE);
		config.setWindowedMode(App.RES_WIDTH, App.RES_HEIGHT);
		config.setResizable(App.RESIZABLE);
		config.useVsync(App.VSYNC);
		new Lwjgl3Application(app, config);
	}
}