package com.bluebear.ui.settings;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.localization.LocalizedLabel;

public class SettingGeneralTerm extends Table {
    public SettingGeneralTerm (String key) {
        align(Align.left);
        padLeft(20);
        NinePatch backgroundNinePatch = SkinLoader.getUINinePatch("UI_CharScreen_NormalBlock");
        NinePatchDrawable backgroundDrawable = new NinePatchDrawable(backgroundNinePatch);
        backgroundDrawable.setPadding(0, 0, 0, 0);
        setBackground(backgroundDrawable);

        Image point = new Image(SkinLoader.getUITexture("UI_Settings_GeneralPoint"));
        add(point);

        LocalizedLabel label = new LocalizedLabel(key, "settingTitle");
        add(label);
    }
}
