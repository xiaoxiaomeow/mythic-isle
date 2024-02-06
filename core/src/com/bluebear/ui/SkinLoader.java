package com.bluebear.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bluebear.ui.localization.LocalizationManager;
import com.bluebear.ui.settings.Settings;

import java.util.HashMap;
import java.util.Map;

public class SkinLoader {
    private static Skin skin;
    public static Skin getSkin () {
        if (skin == null) {
            loadSkin();
        }
        return skin;
    }
    public static BitmapFont getFont (String key, int size) {
        if (LocalizationManager.current == null) {
            return null;
        }
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/" + LocalizationManager.current + "_" + key + ".ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + LocalizationManager.allCharacters();
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.MipMap;
        parameter.size = (int) (size * Settings.getScaleFactor());

        BitmapFont font = generator.generateFont(parameter);
        font.getData().setScale(1.0f / Settings.getScaleFactor());
        generator.dispose();

        return font;
    }
    private static final Map<String, Texture> UITextures = new HashMap<>();
    public static void loadUITextures () {
        FileHandle base = Gdx.files.internal("ui");
        for (FileHandle file : base.list("png")) {
            Texture texture = new Texture(file);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            UITextures.put(file.nameWithoutExtension(), texture);
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
    public static void resetSkin () {
        skin = null;
    }
    private static void loadSkin () {
        skin = new Skin();

        // font
        BitmapFont mediumArt = getFont("art", 60);

        BitmapFont smallText = getFont("text", 40);
        BitmapFont mediumText = getFont("text", 48);

        // single color
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // label
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = mediumArt;
        skin.add("default", labelStyle);

        LabelStyle settingTitle = new LabelStyle();
        settingTitle.font = mediumText;
        settingTitle.fontColor = Color.BLACK;
        skin.add("settingTitle", settingTitle);

        LabelStyle switchStyle = new LabelStyle();
        switchStyle.fontColor = Color.WHITE;
        switchStyle.font = smallText;
        skin.add("switch", switchStyle);

        // text button
        TextButtonStyle mainMenuButtonStyle = new TextButtonStyle();
        mainMenuButtonStyle.fontColor = new Color(0x2E014EFF);
        mainMenuButtonStyle.font = getFont("menu", 144);
        skin.add("pointer", mainMenuButtonStyle);

        TextButtonStyle tabButtonStyle = new TextButtonStyle();
        tabButtonStyle.fontColor = new Color(0x9A7C68FF);
        tabButtonStyle.overFontColor = new Color(0xD4BEB1FF);
        tabButtonStyle.font = mediumArt;
        skin.add("tab", tabButtonStyle);

        // button
        ButtonStyle closeButton = new ButtonStyle();
        closeButton.up = new TextureRegionDrawable(getUITexture("UI_Inventory_CloseButton_Default"));
        closeButton.over = new TextureRegionDrawable(getUITexture("UI_Inventory_CloseButton_Hover"));
        closeButton.down = new TextureRegionDrawable(getUITexture("UI_Inventory_CloseButton_Click"));
        skin.add("close", closeButton);

        // text area
        TextFieldStyle tooltipBoxStyle = new TextFieldStyle();
        tooltipBoxStyle.fontColor = new Color(0x2E014EFF);
        tooltipBoxStyle.font = smallText;
        skin.add("tooltip_box", tooltipBoxStyle);

        // scroll pane
        ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
        scrollPaneStyle.vScroll = new NinePatchDrawable(getUINinePatch("UI_ScrollVertical_bg"));
        scrollPaneStyle.vScrollKnob = new NinePatchDrawable(getUINinePatch("UI_ScrollVertical_Handle_Default"));
        skin.add("default", scrollPaneStyle);

        // input
        TextFieldStyle textFieldStyle = new TextFieldStyle();
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = skin.newDrawable("white", new Color(0.1f, 0.1f, 0.1f, 0.1f));
        textFieldStyle.cursor = skin.newDrawable("white", Color.BLACK);
        textFieldStyle.selection = skin.newDrawable("white", new Color(0.1f, 0.1f, 0.1f, 0.5f));
        textFieldStyle.font = mediumText;
        skin.add("default", textFieldStyle);

        // select box
        SelectBoxStyle selectBoxStyle = new SelectBoxStyle();
        selectBoxStyle.font = smallText;
        selectBoxStyle.fontColor = Color.WHITE;
        selectBoxStyle.background = new TextureRegionDrawable(getUITexture("UI_TypeButton"));
        selectBoxStyle.backgroundOver = new TextureRegionDrawable(getUITexture("UI_TypeButton_Hover"));
        selectBoxStyle.backgroundOpen = new TextureRegionDrawable(getUITexture("UI_TypeButton_Active"));

        ListStyle selectionBoxListStyle = new ListStyle();
        selectionBoxListStyle.font = smallText;
        selectionBoxListStyle.fontColorUnselected = Color.BLACK;
        selectionBoxListStyle.fontColorSelected = Color.BLACK;
        selectionBoxListStyle.background = new TextureRegionDrawable(getUITexture("UI_BackgroundTooltipPaper"));
        selectionBoxListStyle.background.setLeftWidth(20);
        selectionBoxListStyle.background.setRightWidth(20);
        selectionBoxListStyle.background.setTopHeight(20);
        selectionBoxListStyle.background.setBottomHeight(20);
        selectionBoxListStyle.selection = skin.newDrawable("white", new Color(0x8C6F8480));
        selectionBoxListStyle.over = skin.newDrawable("white", new Color(0xB19FA480));
        selectBoxStyle.listStyle = selectionBoxListStyle;

        selectBoxStyle.scrollStyle = new ScrollPaneStyle();
        skin.add("default", selectBoxStyle);

        // slider
        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.background = new TextureRegionDrawable(getUITexture("UI_WightLine"));
        sliderStyle.knob = new TextureRegionDrawable(getUITexture("UI_Settings_ValueScroll"));
        skin.add("default-horizontal", sliderStyle);
    }
}
