package com.bluebear.ui.popups;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.bluebear.ui.SkinLoader;
import com.bluebear.ui.localization.LocalizedTextArea;
import com.bluebear.ui.settings.SettingGeneralTerm;
import com.bluebear.ui.settings.SettingSelectionItem;
import com.bluebear.ui.settings.SettingSwitchItem;
import com.bluebear.ui.settings.SettingsSliderItem;
import com.bluebear.ui.widgets.DecoratedScrollPane;

public class SettingsPopup extends FullScreenPopup {
    public SettingsPopup () {
        String[] tabNames = new String[]{"Game", "Controls", "Graphics", "Sound"};

        for (String tabName : tabNames) {
            Table outerTable = new Table();
            outerTable.pad(70);
            outerTable.setFillParent(true);
            outerTable.align(Align.topLeft);

            Table leftInner = new Table().align(Align.topLeft);
            Table leftBox = new DecoratedScrollPane(leftInner);

            Table rightBox = new Table().align(Align.topLeft).pad(10);
            Image boxTop = new Image(SkinLoader.getUITexture("UI_Settings_ArtTop"));
            rightBox.add(boxTop).row();

            Table tooltipTable = new Table();
            LocalizedTextArea tooltipArea = new LocalizedTextArea(null, "tooltip_box");
            tooltipArea.setDisabled(true);
            tooltipTable.add(tooltipArea).fillX().expandX();
            rightBox.add(tooltipTable).fillX().prefHeight(1000).row();

            Image boxBottom = new Image(SkinLoader.getUITexture("UI_Settings_ArtBottom"));
            rightBox.add(boxBottom).row();

            switch (tabName) {
                case "Game": populateGameSettings(leftInner, tooltipArea); break;
                case "Controls": populateControlsSettings(leftInner, tooltipArea); break;
                case "Graphics": populateGraphicsSettings(leftInner, tooltipArea); break;
                case "Sound": populateSoundSettings(leftInner, tooltipArea); break;
            }

            outerTable.add(leftBox).expand().fill();
            outerTable.add(rightBox).expandY().fillY();
            addTab(tabName, outerTable);
        }

        selectTab(0);
    }

    public Table packSettingBox (String title, Table contents) {
        Table outer = new Table();

        outer.add(new SettingGeneralTerm(title)).fillX().expandX().colspan(3).padBottom(2).row();
        outer.add(new Image(SkinLoader.getUINinePatch("UI_Journal_BigMenuLine"))).prefHeight(0).padLeft(28).fillY();
        outer.add(contents).expandX().fillX();
        outer.add(new Image(SkinLoader.getUINinePatch("UI_Journal_BigMenuLine_Right"))).prefHeight(0).padRight(28).fillY();

        return outer;
    }

    public void populateGameSettings (Table table, LocalizedTextArea tooltip) {
        // game settings
        Table gameSettings = new Table().align(Align.left);

        gameSettings.add(new SettingSelectionItem(
                "language",
                "Settings_Language",
                new String[]{"en", "zh"},
                new String[]{"Settings_en", "Settings_zh"},
                "Settings_Language_Tooltip",
                tooltip)).expandX().fillX().row();

        gameSettings.add(new SettingSwitchItem(
                "allowSendingData",
                "Settings_AllowSendingData",
                "Settings_AllowSendingData_Tooltip",
                tooltip)).expandX().fillX().row();


        gameSettings.add(new SettingsSliderItem(
                "autoSaveAmount",
                "Settings_AutoSaveAmount",
                0,
                25,
                1,
                "Settings_AutoSaveAmount_Tooltip",
                tooltip)).expandX().fillX().row();

        table.add(packSettingBox("Settings_GameSettings", gameSettings)).expandX().fillX();

    }

    public void populateControlsSettings (Table table, LocalizedTextArea tooltip) {

    }

    public void populateGraphicsSettings (Table table, LocalizedTextArea tooltip) {
        // game settings
        Table videoSettings = new Table().align(Align.left);

        videoSettings.add(new SettingSelectionItem(
                "displayMode",
                "Settings_DisplayMode",
                new String[]{"fullscreen", "borderlessWindow", "window"},
                new String[]{"Settings_Fullscreen", "Settings_BorderlessWindow", "Settings_Window"},
                "Settings_DisplayMode_Tooltip",
                tooltip)).expandX().fillX().row();

        videoSettings.add(new SettingSelectionItem(
                "resolution",
                "Settings_Resolution",
                new String[]{"1280x720", "1920x1080", "2560x1440", "3840x2160"},
                new String[]{"Settings_720P", "Settings_1080P", "Settings_2K", "Settings_4K"},
                "Settings_Resolution_Tooltip",
                tooltip)).expandX().fillX().row();

        table.add(packSettingBox("Settings_VideoSettings", videoSettings)).expandX().fillX();

    }

    public void populateSoundSettings (Table table, LocalizedTextArea tooltip) {

    }
}
