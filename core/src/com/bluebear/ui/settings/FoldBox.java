package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.file.Settings;
import com.bluebear.ui.SkinLoader;

public class FoldBox extends Table {
    public FoldBox (String title, Actor content) {
        align(Align.left);
        padLeft(20 * Settings.getScaleFactor());
        NinePatchDrawable backgroundDrawable = SkinLoader.getUINinePatchDrawable("UI_CharScreen_NormalBlock");
        backgroundDrawable.setPadding(0, 0, 0, 0);
        setBackground(backgroundDrawable);

        Image point = new Image(SkinLoader.getUIDrawable("UI_Settings_GeneralPoint"));
        add(point);

        ImageButton triangle = new ImageButton(SkinLoader.getSkin());
        Cell<ImageButton> cell = add(triangle).padLeft(-60 * Settings.getScaleFactor());
        triangle.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                content.setVisible(triangle.isChecked());
                if (triangle.isChecked()) {
                    cell.padLeft(-50 * Settings.getScaleFactor());
                } else {
                    cell.padLeft(-40 * Settings.getScaleFactor());
                }
            }
        });

        Label label = new Label(title, SkinLoader.getSkin(), "settingTitle");
        add(label).row();

        add(content).colspan(3);
        content.setVisible(false);
    }
}
