package com.bluebear.ui.popups;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.widgets.AutoResizeTextArea;

public class SettingsPopup extends FullScreenPopup {
    public SettingsPopup () {
        String[] tabNames = new String[]{"Game", "Controls", "Graphics", "Sound"};

        for (String tabName : tabNames) {
            Table outerTable = new Table();
            outerTable.pad(70);
            outerTable.align(Align.topLeft);

            Table leftBox = new Table();
            leftBox.align(Align.topLeft);
            NinePatch topLinePatch = SkinLoader.getUINinePatch("UI_Journal_BigMenu_Border");
            Image topLine = new Image(topLinePatch);
            leftBox.add(topLine).expandX().fillX();

            Table rightBox = new Table();
            rightBox.align(Align.topLeft);

            Image boxTop = new Image(SkinLoader.getUITexture("UI_Settings_ArtTop"));
            rightBox.add(boxTop).row();

            Table tooltipTable = new Table();
            TextArea tooltipArea = new AutoResizeTextArea("Tooltip Box, Tooltip Box, Tooltip Box, Tooltip Box, Tooltip Box, Tooltip Box", SkinLoader.getSkin(), "tooltip_box");
            tooltipArea.setDisabled(true);
            tooltipTable.add(tooltipArea).fillX().expandX();
            tooltipTable.debug();
            rightBox.add(tooltipTable).fillX().prefHeight(1000).row();

            Image boxBottom = new Image(SkinLoader.getUITexture("UI_Settings_ArtBottom"));
            rightBox.add(boxBottom).row();

            outerTable.add(leftBox).expand().fill();
            outerTable.add(rightBox).expand().fill();
            addTab(tabName, outerTable);
        }

        selectTab(0);
    }
}
