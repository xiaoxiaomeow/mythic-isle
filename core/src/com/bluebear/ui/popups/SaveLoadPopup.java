package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bluebear.file.SaveLoadManager;
import com.bluebear.file.SaveLoadManager.Saves;
import com.bluebear.file.Settings;
import com.bluebear.ui.resolution.ResizableTable;
import com.bluebear.ui.settings.FoldBox;
import com.bluebear.ui.widgets.DecoratedScrollPane;

public class SaveLoadPopup extends FullScreenPopup {
    public SaveLoadPopup () {
        populateLoadPopup();
        // save tab

        selectTab(0);
    }

    private void populateLoadPopup () {
        // load tab
        Table outerTable = new ResizableTable().pad(70);
        outerTable.setFillParent(true);
        outerTable.align(Align.topLeft);

        Table leftInner = new Table().align(Align.topLeft);
        Table leftBox = new DecoratedScrollPane(leftInner);

        for (Saves saves : SaveLoadManager.getSavesForTesting()) {
            Table inner = new Table();
            FoldBox box = new FoldBox(saves.get(0).metadata.gameName, inner);
            leftInner.add(box).row();
        }

        outerTable.add(leftBox).expand().fillY().align(Align.left);
        addTab("LoadGame", outerTable);
    }
}
