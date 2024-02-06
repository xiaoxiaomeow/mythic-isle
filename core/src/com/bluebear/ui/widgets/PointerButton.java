package com.bluebear.ui.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bluebear.ui.localization.LocalizedTextButton;

public class PointerButton extends LocalizedTextButton {
    private static Texture pointerTexture;
    private static Texture getPointerTexture () {
        if (pointerTexture == null) {
            pointerTexture = new Texture(Gdx.files.internal("ui/UI_Dialogue_stick.png"));
        }
        return pointerTexture;
    }
    private final Image pointer;
    public PointerButton (String key) {
        this(key, "pointer");
    }
    public PointerButton (String key, String style) {
        super(key, style);
        pointer = new Image(getPointerTexture());
        pointer.setVisible(false);
        pointer.setColor(new Color(0x3D2F60FF));
        addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pt, Actor fromActor) {
                if (pt == -1) {
                    pointer.setVisible(true);
                }
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pt, Actor toActor) {
                if (pt == -1) {
                    pointer.setVisible(false);
                }
            }
        });
    }
    public Image getPointer() {
        return pointer;
    }
}
