package com.bluebear.ui.localization;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class LocalizedSkin extends Skin implements Localizable {
    public LocalizedSkin () {
        LocalizationManager.register(this);
    }
    @Override
    public void update() {
        for (Entry<String, TextButtonStyle> entry : new ObjectMap<>(getAll(TextButtonStyle.class))) {
            String name = entry.key;
            TextButtonStyle style = entry.value;
            style.font = LocalizationManager.getFont(name);
        }
        for (Entry<String, TextFieldStyle> entry : new ObjectMap<>(getAll(TextFieldStyle.class))) {
            String name = entry.key;
            TextFieldStyle style = entry.value;
            style.font = LocalizationManager.getFont(name);
        }
        for (Entry<String, LabelStyle> entry : new ObjectMap<>(getAll(LabelStyle.class))) {
            String name = entry.key;
            LabelStyle style = entry.value;
            style.font = LocalizationManager.getFont(name);
        }
    }
}
