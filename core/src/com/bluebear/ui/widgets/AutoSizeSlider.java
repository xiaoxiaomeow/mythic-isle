package com.bluebear.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class AutoSizeSlider extends Slider {

    public AutoSizeSlider(float min, float max, float stepSize, boolean vertical, Skin skin) {
        super(min, max, stepSize, vertical, skin);
    }

    @Override
    public float getPrefWidth() {
        return getBackgroundDrawable().getMinWidth();
    }

    public float getLeftMost() {
        return getKnobDrawable().getMinWidth() / 2;
    }
    public float getRightMost() {
        return getBackgroundDrawable().getMinWidth() - getKnobDrawable().getMinWidth() / 2;
    }
}
