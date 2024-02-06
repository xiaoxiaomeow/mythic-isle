package com.bluebear.ui.popups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.bluebear.file.Settings;
import com.bluebear.ui.resolution.*;
import com.bluebear.ui.localization.LocalizedTextButton;

import java.util.ArrayList;
import java.util.List;

public class FullScreenPopup extends Popup implements Resizable {
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
        outerTable.setBackground(SkinLoader.getUIDrawable("UI_BackgroundTable"));

        tabRow = new ResizableTable();
        tabRow.align(Align.topLeft);
        tabRow.padTop(10).padLeft(200);
        outerTable.add(tabRow).fillX();
        tabRow.setZIndex(1);

        Button closeButton = new Button(SkinLoader.getSkin(), "close");
        new ResizableCell(outerTable.add(closeButton).align(Align.right)).padRight(20).padTop(20).row();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });

        Table innerTable = new Table();
        innerTable.setBackground(SkinLoader.getUIDrawable("UI_BackgroundPaper"));
        contentContainer = new Container<>();
        contentContainer.align(Align.bottomLeft);
        innerTable.add(contentContainer).expand().fill();

        new ResizableCell(outerTable.add(innerTable).colspan(2).fill().expand()).padTop(-25);
        innerTable.setZIndex(0);
        add(outerTable);

        tabButtons = new ArrayList<>();
        tabContents = new ArrayList<>();

        ResolutionManager.register(this);
    }

    private final List<LocalizedTextButton> tabButtons;
    private final List<Table> tabContents;
    private Image selector;
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
        new ResizableCell(tabRow.add(tab)).padTop(20).prefWidth(500);
        tab.setZIndex(index + 2);

        Image tabSeparator = new Image(SkinLoader.getUIDrawable("UI_BackgroundTableTopMenuSelector"));
        tabRow.add(tabSeparator);
        tabSeparator.setZIndex(0);

        tabContents.add(content);
    }
    private int selected;
    protected void selectTab (int index) {
        selected = index;
        update();
        contentContainer.setActor(tabContents.get(index));
    }

    @Override
    public void layout() {
        super.layout();

        LocalizedTextButton tabButton = tabButtons.get(selected);
        float x = Math.max(tabButton.getX(), 200 * Settings.getScaleFactor()) - 100 * Settings.getScaleFactor();
        float y = -70 * Settings.getScaleFactor();
        selector.setPosition(x, y);
    }

    @Override
    public void update () {
        if (selector != null) {
            tabRow.removeActor(selector);
        }
        selector = new Image(SkinLoader.getUIDrawable("UI_TopMenuActive"));
        tabRow.addActor(selector);
        selector.setZIndex(1);
    }
}
