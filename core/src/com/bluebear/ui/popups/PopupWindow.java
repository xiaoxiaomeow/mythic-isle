package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.bluebear.ui.screens.ScreenWithPopups;

public class PopupWindow extends Stack {
    private final ScreenWithPopups parent;
    public PopupWindow (ScreenWithPopups parent) {
        this.parent = parent;
    }
}
