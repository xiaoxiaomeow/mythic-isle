package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.file.Settings;
import com.bluebear.ui.resolution.ResizableCell;
import com.bluebear.ui.resolution.ResizableTable;
import com.bluebear.ui.resolution.SkinLoader;
import com.bluebear.ui.localization.LocalizedLabel;
import com.bluebear.ui.localization.LocalizedTextArea;

public class SettingSwitchItem extends SettingItem {
    private static final Drawable up = SkinLoader.getUIDrawable("UI_Settings_BackValueBlue");
    private static final Drawable down = SkinLoader.getUIDrawable("UI_Settings_BackValue");
    private final String setting;
    private final Table table;
    private final LocalizedLabel label;
    public SettingSwitchItem(String setting, String key, String tooltipKey, LocalizedTextArea tooltipArea) {
        super(key, tooltipKey, tooltipArea);
        this.setting = setting;

        table = new ResizableTable().padLeft(50).padRight(50);
        table.setTouchable(Touchable.enabled);
        table.add(new Image(SkinLoader.getUIDrawable("UI_Journal_BigMenuArrow")));

        label = new LocalizedLabel(null, "switch");
        label.setAlignment(Align.center);
        sync();

        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.setPreference(setting, !Settings.getBoolean(setting));
                sync();
            }
        });
        new ResizableCell(table.add(label)).prefWidth(300);

        table.add(new Image(SkinLoader.getUIDrawable("UI_Journal_BigMenuArrow_Right")));

        container.setActor(table);
    }

    private void sync () {
        if (Settings.getBoolean(setting)) {
            table.setBackground(down);
            label.setKey("Settings_On");
        } else {
            table.setBackground(up);
            label.setKey("Settings_Off");
        }
    }
}
