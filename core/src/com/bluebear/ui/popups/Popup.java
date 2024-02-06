package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

public class Popup extends Stack {
    public Popup() {
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
