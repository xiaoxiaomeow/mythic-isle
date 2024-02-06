package com.bluebear.ui.widgets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.SkinLoader;

public class DecoratedScrollPane extends Table {
    public DecoratedScrollPane (Actor content) {
        align(Align.topLeft).pad(10);
        NinePatch topLinePatch = SkinLoader.getUINinePatch("UI_Journal_BigMenu_Border");
        Image topLine = new Image(topLinePatch);
        add(topLine).expandX().fillX().minHeight(topLine.getHeight()).row();

        ScrollPane pane = new ScrollPane(content, SkinLoader.getSkin());
        pane.setScrollingDisabled(true, false);
        pane.setFadeScrollBars(false);
        pane.addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Stage stage = getStage();
                if (stage != null) {
                    stage.setScrollFocus(pane);
                }
            }
        });
        add(pane).expand().fill().row();

        NinePatch bottomLineNinePatch = SkinLoader.getUINinePatch("UI_Journal_BigMenu_Border_Bottom");
        Image bottomLine = new Image(bottomLineNinePatch);
        add(bottomLine).expandX().fillX().minHeight(bottomLine.getHeight());
    }
}
