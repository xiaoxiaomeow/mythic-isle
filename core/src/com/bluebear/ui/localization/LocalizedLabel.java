package com.bluebear.ui.localization;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bluebear.ui.resolution.SkinLoader;

public class LocalizedLabel extends Label implements Localizable {
    private String key;
    private final String style;
    private final boolean doNotLookup;
    public LocalizedLabel (String key, String style) {
        this(key, style, false);
    }
    public LocalizedLabel (String key, String style, boolean doNotLookup) {
        super(doNotLookup ? key : LocalizationManager.get(key), SkinLoader.getSkin(), style);
        this.key = key;
        this.style = style;
        this.doNotLookup = doNotLookup;
        update();
        LocalizationManager.register(this);
    }
    @Override
    public void update() {
        if (doNotLookup) {
            setText(key);
        } else {
            setText(LocalizationManager.get(key));
        }
        setStyle(SkinLoader.getSkin().get(style, LabelStyle.class));
    }
    public void setKey(String key) {
        this.key = key;
        update();
    }
}
