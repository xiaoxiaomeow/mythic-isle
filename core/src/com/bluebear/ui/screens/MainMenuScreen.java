package com.bluebear.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bluebear.Game;
import com.bluebear.ui.ScreenWithPopups;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.localization.LocalizedTextButton;

public class MainMenuScreen extends ScreenWithPopups {
    public MainMenuScreen(Game game) {
        super(game);

        Texture backgroundTexture = new Texture(Gdx.files.internal("ui/background.jpg"));
        background.setDrawable(new TextureRegionDrawable(backgroundTexture));

        TextButton newGameButton = new LocalizedTextButton("MainMenuNewGame");
        TextButton loadGameButton = new LocalizedTextButton("MainMenuLoadGame");
        TextButton settingsButton = new LocalizedTextButton("MainMenuSettings");
        TextButton exitButton = new LocalizedTextButton("MainMenuExit");

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.createGame();
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Add buttons to the table
        mainTable.add(newGameButton).padBottom(20).row();
        mainTable.add(loadGameButton).padBottom(20).row();
        mainTable.add(settingsButton).padBottom(20).row();
        mainTable.add(exitButton);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
