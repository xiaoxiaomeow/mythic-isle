package com.bluebear.ui.resolution;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.bluebear.file.Settings;

public class ResizableNinePatchDrawable extends NinePatchDrawable implements Resizable {
    private final NinePatch patch;
    public ResizableNinePatchDrawable(NinePatch patch) {
        super(patch);
        this.patch = patch;
        update();

        ResolutionManager.register(this);
    }

    @Override
    public void update() {
        NinePatch cur = new NinePatch(patch);
        cur.scale(Settings.getWidthScaleFactor(), Settings.getHeightScaleFactor());
        setPatch(cur);
    }
}
