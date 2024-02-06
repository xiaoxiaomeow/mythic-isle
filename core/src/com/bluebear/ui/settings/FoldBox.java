package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.resolution.ResizableCell;
import com.bluebear.ui.resolution.ResizableTable;
import com.bluebear.ui.resolution.SkinLoader;

public class FoldBox extends ResizableTable {
    public FoldBox (String title, Actor content) {
        align(Align.left);
        padLeft(20);
        NinePatchDrawable backgroundDrawable = SkinLoader.getUINinePatchDrawable("UI_CharScreen_NormalBlock");
        backgroundDrawable.setPadding(0, 0, 0, 0);
        setBackground(backgroundDrawable);

        Image point = new Image(SkinLoader.getUIDrawable("UI_Settings_GeneralPoint"));
        add(point);

        ImageButton triangle = new ImageButton(SkinLoader.getSkin());
        ResizableCell cell = new ResizableCell(add(triangle)).padLeft(-50);
        triangle.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                content.setVisible(triangle.isChecked());
                if (triangle.isChecked()) {
                    cell.padLeft(-50);
                } else {
                    cell.padLeft(-40);
                }
            }
        });

        Label label = new Label(title, SkinLoader.getSkin(), "settingTitle");
        add(label).row();

        add(content).colspan(3);
        content.setVisible(false);
    }
}
