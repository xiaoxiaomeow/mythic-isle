package com.bluebear.ui.popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.screens.ScreenWithPopups;
import com.bluebear.ui.localization.LocalizedLabel;

import java.util.HashMap;
import java.util.Map;

public class Popup extends PopupWindow {
    public enum Size {
        Large, Medium, Small
    }
    private static final Map<Size, Texture> stoneTextures = new HashMap<>();
    private static Texture getStoneTexture (Size size) {
        if (!stoneTextures.containsKey(size)) {
            stoneTextures.put(size, new Texture(Gdx.files.internal("ui/UI board " + size + " stone.png")));
        }
        return stoneTextures.get(size);
    }
    private static final Map<Size, Texture> parchmentTextures = new HashMap<>();
    private static Texture getParchmentTexture (Size size) {
        if (!parchmentTextures.containsKey(size)) {
            parchmentTextures.put(size, new Texture(Gdx.files.internal("ui/UI board " + size + " parchment.png")));
        }
        return parchmentTextures.get(size);
    }
    private static Texture closeButton;
    private static Texture getCloseButton () {
        if (closeButton == null) {
            closeButton = new Texture(Gdx.files.internal("ui/Close Button.png"));
        }
        return closeButton;
    }

    protected final Table mainTable;
    public Popup (String title, Size size) {
        Table outerTable = new Table();
        add(outerTable);

        Stack mainStack = new Stack();
        mainTable = new Table();

        Image stone = new Image(getStoneTexture(size));
        Image parchment = new Image(getParchmentTexture(size));
        Image close = new Image(getCloseButton());
        Label label = new LocalizedLabel(title, "title");
        label.setAlignment(Align.center);
        Container<Label> labelContainer = new Container<>(label).align(Align.top).pad(-15);

        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });
        label.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                float deltaX = x - getDragStartX();
                float deltaY = y - getDragStartY();

                moveBy(deltaX, deltaY);
            }
        });

        mainStack.add(stone);
        mainStack.add(parchment);
        mainStack.add(mainTable);
        mainStack.add(labelContainer);

        mainTable.setFillParent(true);
        mainTable.pad(100, 20, 60, 20);

        outerTable.add(mainStack);
        outerTable.add(close).align(Align.top).padLeft(-20).padTop(65);
    }
    public Popup (String title) {
        this(title, Size.Large);
    }
}
