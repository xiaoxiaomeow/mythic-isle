package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.bluebear.ui.screens.ScreenWithPopups;

public class PopupWindow extends Stack {
    public PopupWindow () {
    }
    public void requestClose () {
        close();
    }
    public void close () {
        addAction(Actions.sequence(
                Actions.fadeOut(0.1f),
                Actions.removeActor()));
    }
}
