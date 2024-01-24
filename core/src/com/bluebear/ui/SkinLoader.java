package com.bluebear.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bluebear.ui.localization.LocalizationManager;
import com.bluebear.ui.localization.LocalizationManager.Locale;

import java.util.HashMap;
import java.util.Map;

public class SkinLoader {
    private static final Map<Locale, Skin> skins = new HashMap<>();
    public static Skin getSkin () {
        return getSkin(LocalizationManager.current);
    }
    public static Skin getSkin (Locale locale) {
        if (!skins.containsKey(locale)) {
            loadSkin(locale);
        }
        return skins.get(locale);
    }
    private static void loadSkin (Locale locale) {
        Skin skin = new Skin();

        // font
        BitmapFont medium = LocalizationManager.getFont("art", 60);
        BitmapFont big = LocalizationManager.getFont("art", 144);

        // single color
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        TextButtonStyle mainMenuButtonStyle = new TextButtonStyle();
        mainMenuButtonStyle.fontColor = new Color(0x2E014EFF);
        mainMenuButtonStyle.font = big;
        skin.add("pointer", mainMenuButtonStyle);

        TextButtonStyle tabButtonStyle = new TextButtonStyle();
        // tabButtonStyle.over = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/UI_Settings_BackValue.png")));
        tabButtonStyle.fontColor = new Color(0x9A7C68FF);
        tabButtonStyle.overFontColor = new Color(0xD4BEB1FF);
        tabButtonStyle.font = medium;
        skin.add("tab", tabButtonStyle);

        ButtonStyle closeButton = new ButtonStyle();
        closeButton.up = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/UI_Inventory_CloseButton_Default.png")));
        closeButton.over = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/UI_Inventory_CloseButton_Hover.png")));
        closeButton.down = new TextureRegionDrawable(new Texture(Gdx.files.internal("ui/UI_Inventory_CloseButton_Click.png")));
        skin.add("close", closeButton);

        // input
        TextFieldStyle textFieldStyle = new TextFieldStyle();
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = skin.newDrawable("white", new Color(0.1f, 0.1f, 0.1f, 0.1f));
        textFieldStyle.cursor = skin.newDrawable("white", Color.BLACK);
        textFieldStyle.selection = skin.newDrawable("white", new Color(0.1f, 0.1f, 0.1f, 0.5f));
        textFieldStyle.font = medium;
        skin.add("default", textFieldStyle);

        skins.put(locale, skin);
    }
}
