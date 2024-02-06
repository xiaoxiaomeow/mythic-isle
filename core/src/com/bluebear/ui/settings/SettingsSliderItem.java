package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.bluebear.file.Settings;
import com.bluebear.ui.localization.LocalizedLabel;
import com.bluebear.ui.resolution.ResolutionManager;
import com.bluebear.ui.resolution.SkinLoader;
import com.bluebear.ui.localization.LocalizedTextArea;
import com.bluebear.ui.widgets.AutoSizeSlider;

import java.text.DecimalFormat;

public class SettingsSliderItem extends SettingItem {
    private AutoSizeSlider slider;
    private final String setting;
    private final LocalizedLabel label;
    private final float min;
    private final float max;
    private final float step;
    private Container<Actor> sliderContainer;
    public SettingsSliderItem(String setting, String key, float min, float max, float step, String tooltipKey, LocalizedTextArea tooltipArea) {
        super(key, tooltipKey, tooltipArea);

        this.setting = setting;
        this.min = min;
        this.max = max;
        this.step = step;

        float value = Settings.getFloat(setting);
        String valueText = new DecimalFormat("#").format(value);

        Table table = new Table();
        sliderContainer = new Container<>();
        label = new LocalizedLabel(valueText, "switch", true);
        label.setTouchable(Touchable.disabled);

        table.add(sliderContainer);
        table.addActor(label);

        container.setActor(table);

        update();

        ResolutionManager.register(this);
    }

    @Override
    public void layout() {
        super.layout();
        float value = slider.getValue();
        String valueText = new DecimalFormat("#").format(value);
        label.setText(valueText);
        float percent = (value - min) / (max - min);
        float leftmost = slider.getLeftMost();
        float rightmost = slider.getRightMost();
        float x = leftmost + (rightmost - leftmost) * percent - label.getPrefWidth() / 2;
        label.setPosition(x, label.getPrefHeight() / 2 + 3 * Settings.getHeightScaleFactor(), Align.left);
    }

    @Override
    public void update() {
        super.update();

        if (setting == null) {
            return;
        }

        slider = new AutoSizeSlider(min, max, step, false, SkinLoader.getSkin());
        slider.setValue(Settings.getFloat(setting));
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = slider.getValue();
                Settings.setPreference(setting, value);
                layout();
            }
        });
        sliderContainer.setActor(slider);

        invalidateHierarchy();
    }
}
