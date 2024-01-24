package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.screens.ScreenWithPopups;
import com.bluebear.ui.localization.LocalizedTextButton;
import com.bluebear.ui.localization.LocalizedTextFieldWithDefaultText;

public class SavePopup extends Popup {
    public SavePopup (ScreenWithPopups parent) {
        super("SavePopupTitle", Size.Large);

        TextField input = new LocalizedTextFieldWithDefaultText("SavePopupEnterSaveName");
        TextButton save = new LocalizedTextButton("SavePopupSave");

        mainTable.add(input).align(Align.center).width(1000).padRight(20);
        mainTable.add(save).align(Align.right).padBottom(20).row();
    }
}
