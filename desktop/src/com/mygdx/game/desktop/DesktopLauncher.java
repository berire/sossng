package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.IActivityRequestHandler;
import com.mygdx.game.SOSGame;

public class DesktopLauncher  {
	static IActivityRequestHandler handler;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SOSGame.TITLE;
		config.width = 480;
		config.height = 760;
		new LwjglApplication(new SOSGame(handler), config);
	}

}
