package com.bluebear.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.Game;
import com.bluebear.file.Settings;
import com.bluebear.ui.popups.SaveLoadPopup;
import com.bluebear.ui.popups.SettingsPopup;
import com.bluebear.ui.resolution.ResizableCell;
import com.bluebear.ui.resolution.SkinLoader;
import com.bluebear.ui.widgets.PointerButton;

public class MainMenuScreen extends ScreenWithPopups {
    public MainMenuScreen(Game game) {
        super(game);

        background.setDrawable(SkinLoader.getUIDrawable("UI_MainMenu"));

        Table buttonTable = new Table();
        Container<Table> buttonContainer = new Container<>(buttonTable);
        buttonContainer.setTransform(true);
        buttonContainer.setRotation(-4);

        PointerButton continueButton = new PointerButton("Continue");
        PointerButton newGameButton = new PointerButton("NewGame");
        PointerButton loadGameButton = new PointerButton("LoadGame");
        PointerButton settingsButton = new PointerButton("Settings");
        PointerButton creditsButton = new PointerButton("Credits");
        PointerButton licenseButton = new PointerButton("License");
        PointerButton exitButton = new PointerButton("Exit");

        PointerButton[] mainMenuButtons = new PointerButton[]{
                continueButton, newGameButton, loadGameButton, settingsButton, creditsButton, licenseButton, exitButton
        };

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.createGame();
            }
        });
        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                popup(new SaveLoadPopup());
            }
        });
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                popup(new SettingsPopup());
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        for (PointerButton mainMenuButton : mainMenuButtons) {
            new ResizableCell(buttonTable.add(mainMenuButton.getPointer())).padRight(10);
            buttonTable.add(mainMenuButton).align(Align.left).row();
        }

        new ResizableCell(mainTable.add(buttonContainer).align(Align.topLeft)).padLeft(2750).padBottom(580);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
