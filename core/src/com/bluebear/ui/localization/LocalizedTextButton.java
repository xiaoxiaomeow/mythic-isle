package com.bluebear.ui.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bluebear.ui.SkinLoader;

public class LocalizedTextButton extends TextButton implements Localizable {
    private String key;
    private String style;
    public LocalizedTextButton(String key) {
        this(key, "default");
    }
    public LocalizedTextButton(String key, String style) {
        super(LocalizationManager.get(key), SkinLoader.getSkin(), style);
        this.key = key;
        this.style = style;
        update();
        LocalizationManager.register(this);
    }
    @Override
    public void update() {
        setText(LocalizationManager.get(key));
        setStyle(SkinLoader.getSkin().get(style, TextButtonStyle.class));
    }
    public void setKey(String key) {
        this.key = key;
        update();
    }
    public void setStyle(String style) {
        this.style = style;
        update();
    }
}
