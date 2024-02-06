package com.bluebear.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class AutoResizeTextArea extends TextArea {
    public AutoResizeTextArea(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }
    @Override
    protected void calculateOffsets() {
        super.calculateOffsets();

        setPrefRows(getLines());
        invalidateHierarchy();
    }
}
