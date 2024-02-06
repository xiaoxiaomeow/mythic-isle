package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.file.Settings;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.localization.LocalizedLabel;

public class SettingGeneralTerm extends Table {
    public SettingGeneralTerm (String key) {
        align(Align.left);
        padLeft(20 * Settings.getScaleFactor());
        NinePatchDrawable backgroundDrawable = SkinLoader.getUINinePatchDrawable("UI_CharScreen_NormalBlock");
        backgroundDrawable.setPadding(0, 0, 0, 0);
        setBackground(backgroundDrawable);

        Image point = new Image(SkinLoader.getUIDrawable("UI_Settings_GeneralPoint"));
        add(point);

        LocalizedLabel label = new LocalizedLabel(key, "settingTitle");
        add(label);
    }
}
