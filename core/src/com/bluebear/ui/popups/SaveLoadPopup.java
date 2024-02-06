package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.widgets.DecoratedScrollPane;

public class SaveLoadPopup extends FullScreenPopup {
    public SaveLoadPopup () {
        populateLoadPopup();
        // save tab

        selectTab(0);
    }

    private void populateLoadPopup () {
        // load tab
        Table outerTable = new Table();
        outerTable.pad(70);
        outerTable.setFillParent(true);
        outerTable.align(Align.topLeft);

        Table leftInner = new Table().align(Align.topLeft);
        Table leftBox = new DecoratedScrollPane(leftInner);

        outerTable.add(leftBox).expand().fillY().align(Align.left);
        addTab("LoadGame", outerTable);
    }
}
