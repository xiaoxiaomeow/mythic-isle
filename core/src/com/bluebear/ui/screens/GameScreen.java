package com.bluebear.ui.screens;

import com.bluebear.Game;
import com.bluebear.ui.popups.GameMenu;

public class GameScreen extends ScreenWithPopups {
    public GameScreen (Game game) {
        super(game);
    }

    @Override
    public boolean escOnEmptyStack() {
        popup(new GameMenu(this));
        return true;
    }
}
