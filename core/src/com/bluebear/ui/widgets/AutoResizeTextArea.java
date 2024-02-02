package com.bluebear.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class AutoResizeTextArea extends TextArea {
    public AutoResizeTextArea(String text, Skin skin) {
        super(text, skin);
        updateHeight();
    }
    public AutoResizeTextArea(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        updateHeight();
    }
    public AutoResizeTextArea(String text, TextFieldStyle style) {
        super(text, style);
        updateHeight();
    }

    @Override
    public void setText(String str) {
        super.setText(str);
        updateHeight();
    }
    private void updateHeight () {
        System.out.println(getLines());
        System.out.println(getLinesShowing());
        System.out.println(getFirstLineShowing());
        System.out.println("----");
        setPrefRows(getLines());
        invalidateHierarchy();
    }
}
