package com.boompanes.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.BoomPanes;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(30);
		config.setTitle("Boom Panes");
		config.setWindowedMode(1280, 720); // Set resolution to 720p
		new Lwjgl3Application(new BoomPanes(), config);
	}
}