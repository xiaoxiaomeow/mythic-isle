package com.bluebear.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
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
    private static final Map<String, Texture> UITextures = new HashMap<>();
    public static void loadUITextures () {
        FileHandle base = Gdx.files.internal("ui");
        for (FileHandle file : base.list("png")) {
            Texture texture = new Texture(file);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            UITextures.put(file.nameWithoutExtension(), texture);
            System.out.println(file.nameWithoutExtension());
        }
    }
    public static Texture getUITexture (String key) {
        return UITextures.get(key);
    }
    public static NinePatch getUINinePatch (String key) {
        Texture texture = UITextures.get(key);
        int w = texture.getWidth();
        int h = texture.getHeight();
        if (w < h) {
            int r = (h - 1) / 2;
            return new NinePatch(texture, 0, 0, r, r);
        } else {
            int r = (w - 1) / 2;
            return new NinePatch(texture, r, r, 0, 0);
        }
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
        tabButtonStyle.fontColor = new Color(0x9A7C68FF);
        tabButtonStyle.overFontColor = new Color(0xD4BEB1FF);
        tabButtonStyle.font = medium;
        skin.add("tab", tabButtonStyle);

        ButtonStyle closeButton = new ButtonStyle();
        closeButton.up = new TextureRegionDrawable(getUITexture("UI_Inventory_CloseButton_Default"));
        closeButton.over = new TextureRegionDrawable(getUITexture("UI_Inventory_CloseButton_Hover"));
        closeButton.down = new TextureRegionDrawable(getUITexture("UI_Inventory_CloseButton_Click"));
        skin.add("close", closeButton);

        // text area

        TextFieldStyle tooltipBoxStyle = new TextFieldStyle();
        tooltipBoxStyle.fontColor = new Color(0x2E014EFF);
        tooltipBoxStyle.font = medium;
        skin.add("tooltip_box", tooltipBoxStyle);

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
