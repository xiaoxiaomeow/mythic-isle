package com.bluebear.ui.resolution;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.bluebear.ui.settings.Settings;

public class ResizableCell implements Resizable {
    private Cell<Actor> cell;
    private float padTop, padLeft, padBottom, padRight;
    private float minWidth, minHeight, maxWidth, maxHeight, prefWidth, prefHeight;
    public ResizableCell(Cell<Actor> cell) {
        this.cell = cell;
        padTop = cell.getPadTop();
        padLeft = cell.getPadLeft();
        padBottom = cell.getPadBottom();
        padRight = cell.getPadRight();
        minWidth = cell.getMinWidth();
        minHeight = cell.getMinHeight();
        maxWidth = cell.getMaxWidth();
        maxHeight = cell.getMaxHeight();
        prefWidth = cell.getPrefWidth();
        prefHeight = cell.getPrefHeight();

        update();
        ResolutionManager.register(this);
    }
    @Override
    public void update() {
        cell.padTop(padTop * Settings.getHeightScaleFactor());
        cell.padLeft(padLeft * Settings.getWidthScaleFactor());
        cell.padBottom(padBottom * Settings.getHeightScaleFactor());
        cell.padRight(padRight * Settings.getWidthScaleFactor());
        cell.minWidth(minWidth * Settings.getWidthScaleFactor());
        cell.minHeight(minHeight * Settings.getHeightScaleFactor());
        cell.maxWidth(maxWidth * Settings.getWidthScaleFactor());
        cell.maxHeight(maxHeight * Settings.getHeightScaleFactor());
        cell.prefWidth(prefWidth * Settings.getWidthScaleFactor());
        cell.prefHeight(prefHeight * Settings.getHeightScaleFactor());
    }
}
