package com.bluebear.ui.settings;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.localization.LocalizedSelectBox;
import com.bluebear.ui.localization.LocalizedTextArea;

public class SettingSelectionItem extends SettingItem {
    public SettingSelectionItem(String setting, String key, String[] items, String[] itemKeys, String tooltip, LocalizedTextArea tooltipArea) {
        super(key, tooltip, tooltipArea);

        LocalizedSelectBox selectBox = new LocalizedSelectBox(itemKeys);
        selectBox.setAlignment(Align.center);
        selectBox.getScrollPane().getList().setAlignment(Align.center);
        selectBox.getScrollPane().setScrollingDisabled(true, true);
        selectBox.setItemKeys(itemKeys);

        String current = Settings.getString(setting);
        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (current != null && current.equals(items[i])) {
                index = i;
            }
        }
        if (index != -1) {
            selectBox.setSelectedIndex(index);
        }
        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setPreference(setting, items[selectBox.getSelectedIndex()]);
            }
        });


        container.setActor(selectBox);
    }

}
