package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.localization.LocalizedTextArea;
import com.bluebear.ui.widgets.AutoSizeSlider;

import java.text.DecimalFormat;

public class SettingsSliderItem extends SettingItem {
    private final AutoSizeSlider slider;
    private final Label label;
    private final float min;
    private final float max;
    public SettingsSliderItem(String setting, String key, float min, float max, float step, String tooltipKey, LocalizedTextArea tooltipArea) {
        super(key, tooltipKey, tooltipArea);

        this.min = min;
        this.max = max;

        float value = Settings.getFloat(setting);
        String valueText = new DecimalFormat("#").format(value);

        Table table = new Table();
        slider = new AutoSizeSlider(min, max, step, false, SkinLoader.getSkin());
        label = new Label(valueText, SkinLoader.getSkin(), "switch");
        label.setTouchable(Touchable.disabled);

        slider.setValue(Settings.getFloat(setting));
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();
                Settings.setPreference(setting, value);
                sync();
            }
        });

        table.add(slider);
        table.addActor(label);

        sync();

        container.setActor(table);
    }
    private void sync () {
        float value = slider.getValue();
        String valueText = new DecimalFormat("#").format(value);
        label.setText(valueText);
        float percent = (value - min) / (max - min);
        float leftmost = slider.getLeftMost();
        float rightmost = slider.getRightMost();
        float x = leftmost + (rightmost - leftmost) * percent - label.getPrefWidth() / 2;
        label.setPosition(x, label.getPrefHeight() / 2 + 3, Align.left);
    }
}
