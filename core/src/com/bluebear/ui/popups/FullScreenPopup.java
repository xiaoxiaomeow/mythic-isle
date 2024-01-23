package com.bluebear.ui.popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.screens.ScreenWithPopups;

public class FullScreenPopup extends PopupWindow {
    private static Texture backgroundTableTexture;
    public static Texture getBackgroundTableTexture() {
        if (backgroundTableTexture == null) {
            backgroundTableTexture = new Texture(Gdx.files.internal("ui/UI_BackgroundTable_Console_3860_2184.png"));
        }
        return backgroundTableTexture;
    }
    private static Texture backgroundPaperTexture;
    public static Texture getBackgroundPaperTexture() {
        if (backgroundPaperTexture == null) {
            backgroundPaperTexture = new Texture(Gdx.files.internal("ui/UI_BackgroundPaper_Console_3756_2000.png"));
        }
        return backgroundPaperTexture;
    }

    public FullScreenPopup (ScreenWithPopups parent) {
        super(parent);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Image backgroundColor = new Image(new Texture(pixmap));
        add(backgroundColor);

        Image backgroundBorder = new Image(getBackgroundTableTexture());
        add(backgroundBorder);

        Table outerTable = new Table();
        outerTable.align(Align.topLeft);
        outerTable.setFillParent(true);
        outerTable.setDebug(true);

        Image backgroundPaper = new Image(getBackgroundPaperTexture());
        backgroundPaper.setFillParent(true);

        outerTable.add(backgroundPaper);

        add(outerTable);
    }
}
