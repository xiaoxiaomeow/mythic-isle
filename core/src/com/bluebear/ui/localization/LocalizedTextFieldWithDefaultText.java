package com.bluebear.ui.localization;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.bluebear.ui.SkinLoader;

public class LocalizedTextFieldWithDefaultText extends TextField implements Localizable {
    private String key;
    private String style;
    private boolean changed = false;
    public LocalizedTextFieldWithDefaultText (String key) {
        this(key, "default");
    }
    public LocalizedTextFieldWithDefaultText (String defaultKey, String style) {
        super(LocalizationManager.get(defaultKey), SkinLoader.getSkin(), style);
        this.key = defaultKey;
        this.style = style;
        update();

        addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (focused && !changed) {
                    setText("");
                    changed = true;
                }
            }
        });
        LocalizationManager.register(this);
    }
    @Override
    public String getText() {
        if (changed) {
            return super.getText();
        }
        return "";
    }
    @Override
    public void update() {
        setStyle(SkinLoader.getSkin().get(style, TextFieldStyle.class));
        if (!changed) {
            setText(LocalizationManager.get(key));
        }
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
