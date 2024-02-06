package com.bluebear.ui.resolution;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.bluebear.file.Settings;

public class ResizableCell implements Resizable {
    private Cell<?> cell;
    private float padTop, padLeft, padBottom, padRight;
    private boolean hasMinWidth, hasMinHeight, hasMaxWidth, hasMaxHeight, hasPrefWidth, hasPrefHeight;
    private float minWidth, minHeight, maxWidth, maxHeight, prefWidth, prefHeight;
    public ResizableCell(Cell<?> cell) {
        this.cell = cell;

        update();

        ResolutionManager.register(this);
    }
    @Override
    public void update() {
        cell.padTop(padTop * Settings.getHeightScaleFactor());
        cell.padLeft(padLeft * Settings.getWidthScaleFactor());
        cell.padBottom(padBottom * Settings.getHeightScaleFactor());
        cell.padRight(padRight * Settings.getWidthScaleFactor());
        if (hasMinWidth) cell.minWidth(minWidth * Settings.getWidthScaleFactor());
        if (hasMinHeight) cell.minHeight(minHeight * Settings.getHeightScaleFactor());
        if (hasMaxWidth) cell.maxWidth(maxWidth * Settings.getWidthScaleFactor());
        if (hasMaxHeight) cell.maxHeight(maxHeight * Settings.getHeightScaleFactor());
        if (hasPrefWidth) cell.prefWidth(prefWidth * Settings.getWidthScaleFactor());
        if (hasPrefHeight) cell.prefHeight(prefHeight * Settings.getHeightScaleFactor());
    }

    public ResizableCell padTop(float padTop) {
        this.padTop = padTop;
        update();
        return this;
    }

    public ResizableCell padLeft(float padLeft) {
        this.padLeft = padLeft;
        update();
        return this;
    }

    public ResizableCell padBottom(float padBottom) {
        this.padBottom = padBottom;
        update();
        return this;
    }

    public ResizableCell padRight(float padRight) {
        this.padRight = padRight;
        update();
        return this;
    }

    public ResizableCell minWidth(float minWidth) {
        hasMinWidth = true;
        this.minWidth = minWidth;
        update();
        return this;
    }

    public ResizableCell minHeight(float minHeight) {
        hasMinHeight = true;
        this.minHeight = minHeight;
        update();
        return this;
    }

    public ResizableCell maxWidth(float maxWidth) {
        hasMaxWidth = true;
        this.maxWidth = maxWidth;
        update();
        return this;
    }

    public ResizableCell maxHeight(float maxHeight) {
        hasMaxHeight = true;
        this.maxHeight = maxHeight;
        update();
        return this;
    }

    public ResizableCell prefWidth(float prefWidth) {
        hasPrefWidth = true;
        this.prefWidth = prefWidth;
        update();
        return this;
    }

    public ResizableCell prefHeight(float prefHeight) {
        hasPrefHeight = true;
        this.prefHeight = prefHeight;
        update();
        return this;
    }

    public void row() {
        cell.row();
    }
}
