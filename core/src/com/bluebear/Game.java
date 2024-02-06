package com.bluebear;

import com.badlogic.gdx.Screen;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.screens.GameScreen;
import com.bluebear.ui.screens.MainMenuScreen;
import com.bluebear.ui.settings.Settings;

public class Game extends com.badlogic.gdx.Game {
	private MainMenuScreen mainMenuScreen;
	@Override
	public void create () {
		Settings.initialize();
		SkinLoader.loadUITextures();

		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
	}
	public void createGame () {
		setScreen(new GameScreen(this));
	}
	public void backToMainMenu () {
		Screen current = getScreen();
		setScreen(mainMenuScreen);
		current.dispose();
	}
	@Override
	public void dispose () {
		mainMenuScreen.dispose();
	}

}
