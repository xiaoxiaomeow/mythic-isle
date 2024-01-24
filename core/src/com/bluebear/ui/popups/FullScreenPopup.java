package com.bluebear.ui.popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.localization.LocalizedTextButton;
import com.bluebear.ui.screens.ScreenWithPopups;

import java.util.ArrayList;
import java.util.List;

public class FullScreenPopup extends PopupWindow {
    private static Texture backgroundTableTexture;
    public static Texture getBackgroundTableTexture() {
        if (backgroundTableTexture == null) {
            backgroundTableTexture = new Texture(Gdx.files.internal("ui/UI_BackgroundTable.png"));
            backgroundTableTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return backgroundTableTexture;
    }
    private static Texture backgroundPaperTexture;
    public static Texture getBackgroundPaperTexture() {
        if (backgroundPaperTexture == null) {
            backgroundPaperTexture = new Texture(Gdx.files.internal("ui/UI_BackgroundPaper.png"));
            backgroundPaperTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return backgroundPaperTexture;
    }
    private static Texture tabTexture;
    public static Texture getTabTexture() {
        if (tabTexture == null) {
            tabTexture = new Texture(Gdx.files.internal("ui/UI_BackgroundTableTopMenuSelector.png"));
            tabTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return tabTexture;
    }
    private static Texture selectorTexture;
    public static Texture getSelectorTexture() {
        if (selectorTexture == null) {
            selectorTexture = new Texture(Gdx.files.internal("ui/UI_TopMenuActive.png"));
            selectorTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return selectorTexture;
    }
    private final Table tabRow;
    private final Container<Table> contentContainer;

    public FullScreenPopup () {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Image backgroundColor = new Image(new Texture(pixmap));
        add(backgroundColor);

        Table outerTable = new Table();
        outerTable.setFillParent(true);
        outerTable.setBackground(new TextureRegionDrawable(getBackgroundTableTexture()));

        tabRow = new Table();
        tabRow.align(Align.topLeft);
        tabRow.padTop(10).padLeft(200);
        outerTable.add(tabRow).fillX();
        tabRow.setZIndex(1);

        Button closeButton = new Button(SkinLoader.getSkin(), "close");
        outerTable.add(closeButton).align(Align.right).padRight(20).padTop(20).row();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });

        Table innerTable = new Table();
        innerTable.setBackground(new TextureRegionDrawable(getBackgroundPaperTexture()));
        contentContainer = new Container<>();
        innerTable.add(contentContainer).expand().fill();

        outerTable.add(innerTable).padTop(-25).colspan(2).fill().expand();
        innerTable.setZIndex(0);
        add(outerTable);

        tabButtons = new ArrayList<>();
        tabContents = new ArrayList<>();

        selector = new Image(getSelectorTexture());
        tabRow.addActor(selector);
        selector.setZIndex(1);

        addTab("Game", new Table());
        addTab("Controls", new Table());
        addTab("Graphics", new Table());
        addTab("Sound", new Table());

        selectTab(0);
    }

    private final List<LocalizedTextButton> tabButtons;
    private final List<Table> tabContents;
    private final Image selector;
    protected void addTab (String tabNameKey, Table content) {
        int index = tabButtons.size();

        LocalizedTextButton tab = new LocalizedTextButton(tabNameKey, "tab");
        tab.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectTab(index);
            }
        });
        tabButtons.add(tab);
        tabRow.add(tab).padTop(20).prefWidth(500);
        tab.setZIndex(index + 2);

        Image tabSeparator = new Image(getTabTexture());
        tabRow.add(tabSeparator);
        tabSeparator.setZIndex(0);

        tabContents.add(content);
    }
    protected void selectTab (int index) {
        LocalizedTextButton tabButton = tabButtons.get(index);
        selector.setPosition( Math.max(tabButton.getX(), 200) - 100, -70);
        contentContainer.setActor(tabContents.get(index));
    }
}
