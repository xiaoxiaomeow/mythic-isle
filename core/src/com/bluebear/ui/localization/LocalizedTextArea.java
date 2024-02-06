package com.bluebear.ui.localization;

import com.bluebear.ui.resolution.SkinLoader;
import com.bluebear.ui.widgets.AutoResizeTextArea;

public class LocalizedTextArea extends AutoResizeTextArea implements Localizable {
    private String key;
    private final String style;
    public LocalizedTextArea (String key, String style) {
        super(LocalizationManager.get(key), SkinLoader.getSkin(), style);
        this.key = key;
        this.style = style;
        update();
        LocalizationManager.register(this);
    }
    @Override
    public void update() {
        setText(LocalizationManager.get(key));
        setStyle(SkinLoader.getSkin().get(style, TextFieldStyle.class));
    }
    public void setKey(String key) {
        this.key = key;
        update();
    }
}
