package com.bluebear.ui.resolution;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bluebear.file.Settings;

public class ResizableTable extends Table implements Resizable {
    private float padTop, padLeft, padBottom, padRight;

    public ResizableTable() {
        ResolutionManager.register(this);
    }

    @Override
    public void update() {
        super.padTop(padTop * Settings.getHeightScaleFactor());
        super.padLeft(padLeft * Settings.getWidthScaleFactor());
        super.padBottom(padBottom * Settings.getHeightScaleFactor());
        super.padRight(padRight * Settings.getWidthScaleFactor());
    }
    @Override
    public Table pad(float pad) {
        padTop = padLeft = padBottom = padRight = pad;
        update();
        return this;
    }
    @Override
    public Table padTop(float padTop) {
        this.padTop = padTop;
        update();
        return this;
    }
    @Override
    public Table padLeft(float padLeft) {
        this.padLeft = padLeft;
        update();
        return this;
    }
    @Override
    public Table padBottom(float padBottom) {
        this.padBottom = padBottom;
        update();
        return this;
    }
    @Override
    public Table padRight(float padRight) {
        this.padRight = padRight;
        update();
        return this;
    }
}
