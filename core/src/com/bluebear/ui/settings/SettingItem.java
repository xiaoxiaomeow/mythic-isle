package com.bluebear.ui.settings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.resolution.ResizableCell;
import com.bluebear.ui.resolution.ResizableTable;
import com.bluebear.ui.resolution.SkinLoader;
import com.bluebear.ui.localization.LocalizedLabel;
import com.bluebear.ui.localization.LocalizedTextArea;

public class SettingItem extends ResizableTable {
    protected Container<Actor> container;
    public SettingItem (String key, String tooltipKey, LocalizedTextArea tooltipArea) {
        align(Align.left).padTop(3).padLeft(20).padBottom(3).setTouchable(Touchable.enabled);

        Image point = new Image(SkinLoader.getUIDrawable("UI_CharScreen_Class_Point"));
        new ResizableCell(add(point).align(Align.left)).padRight(10);

        LocalizedLabel label = new LocalizedLabel(key, "settingTitle");
        add(label).align(Align.left);

        Container<Actor> empty = new Container<>();
        add(empty).align(Align.left).expandX().fillX();

        container = new Container<>().align(Align.center);
        new ResizableCell(add(container).align(Align.center)).minWidth(1000).row();

        Image line = new Image(SkinLoader.getUIDrawable("UI_Journal_Line"));
        add(line).colspan(4).expandX().fillX();

        addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                setBackground(SkinLoader.getSkin().newDrawable("white", new Color(0x80808040)));
                tooltipArea.setKey(tooltipKey);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (pointer == -1) {
                    setBackground((Drawable) null);
                    tooltipArea.setKey(null);
                }
            }
        });
    }

}
