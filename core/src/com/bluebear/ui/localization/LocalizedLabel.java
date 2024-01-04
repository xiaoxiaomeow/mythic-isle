package com.bluebear.ui.localization;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bluebear.ui.SkinLoader;

public class LocalizedLabel extends Label implements Localizable {
    private String key;
    private String style;
    public LocalizedLabel (String key) {
        this(key, "default");
    }
    public LocalizedLabel (String key, String style) {
        super(LocalizationManager.get(key), SkinLoader.getSkin(), style);
        this.key = key;
        this.style = style;
        update();
        LocalizationManager.register(this);
    }
    @Override
    public void update() {
        setText(LocalizationManager.get(key));
        setStyle(SkinLoader.getSkin().get(style, LabelStyle.class));
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
