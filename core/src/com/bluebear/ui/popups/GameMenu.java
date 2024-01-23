package com.bluebear.ui.popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebear.ui.screens.ScreenWithPopups;
import com.bluebear.ui.localization.LocalizedTextButton;

public class GameMenu extends Popup {
    public GameMenu (ScreenWithPopups parent) {
        super(parent, "GameMenuTitle", Size.Medium);

        TextButton save = new LocalizedTextButton("GameMenuSave");
        TextButton load = new LocalizedTextButton("GameMenuLoad");
        TextButton mainMenu = new LocalizedTextButton("GameMenuMainMenu");
        TextButton exit = new LocalizedTextButton("GameMenuExit");

        save.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.close(GameMenu.this);
                parent.popup(new SavePopup(parent));
            }
        });
        mainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.game.backToMainMenu();
            }
        });
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        mainTable.add(save).padBottom(20).row();
        mainTable.add(load).padBottom(20).row();
        mainTable.add(mainMenu).padBottom(20).row();
        mainTable.add(exit);
    }
}
