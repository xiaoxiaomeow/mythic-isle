package com.bluebear.ui.resolution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.bluebear.ui.localization.LocalizationManager;
import com.bluebear.file.Settings;

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
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = (int) (size * Settings.getScaleFactor());
        // parameter.gamma = 1.5f;

        BitmapFont font = generator.generateFont(parameter);

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
    public static NinePatchDrawable getUIDrawable(String key) {
        if (key == null) {
            return null;
        }
        Texture texture = UITextures.get(key);
        NinePatch ninePatch = new NinePatch(texture, 0, 0, 0, 0);
        return new ResizableNinePatchDrawable(ninePatch);
    }
    public static NinePatchDrawable getUINinePatchDrawable (String key) {
        if (key == null) {
            return null;
        }
        Texture texture = UITextures.get(key);
        NinePatch ninePatch;
        int w = texture.getWidth();
        int h = texture.getHeight();
        if (w < h) {
            int r = (h - 1) / 2;
            ninePatch = new NinePatch(texture, 0, 0, r, r);
        } else {
            int r = (w - 1) / 2;
            ninePatch = new NinePatch(texture, r, r, 0, 0);
        }
        return new ResizableNinePatchDrawable(ninePatch);
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
        closeButton.up = getUIDrawable("UI_Inventory_CloseButton_Default");
        closeButton.over = getUIDrawable("UI_Inventory_CloseButton_Hover");
        closeButton.down = getUIDrawable("UI_Inventory_CloseButton_Click");
        skin.add("close", closeButton);

        // Image button
        ImageButtonStyle triangleStyle = new ImageButtonStyle();
        triangleStyle.up = triangleStyle.down = getUIDrawable("UI_Journal_BigMenuArrow_Right");
        triangleStyle.checked = triangleStyle.checkedDown = getUIDrawable("UI_Journal_BigMenuArrow_Down");
        triangleStyle.unpressedOffsetX = triangleStyle.checkedOffsetX = 5;
        triangleStyle.unpressedOffsetY = triangleStyle.checkedOffsetY = -5;
        skin.add("default", triangleStyle);

        // text area
        TextFieldStyle tooltipBoxStyle = new TextFieldStyle();
        tooltipBoxStyle.fontColor = new Color(0x2E014EFF);
        tooltipBoxStyle.font = smallText;
        skin.add("tooltip_box", tooltipBoxStyle);

        // scroll pane
        ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
        scrollPaneStyle.vScroll = getUINinePatchDrawable("UI_ScrollVertical_bg");
        scrollPaneStyle.vScrollKnob = getUINinePatchDrawable("UI_ScrollVertical_Handle_Default");
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
        selectBoxStyle.background = getUIDrawable("UI_TypeButton");
        selectBoxStyle.backgroundOver = getUIDrawable("UI_TypeButton_Hover");
        selectBoxStyle.backgroundOpen = getUIDrawable("UI_TypeButton_Active");

        ListStyle selectionBoxListStyle = new ListStyle();
        selectionBoxListStyle.font = smallText;
        selectionBoxListStyle.fontColorUnselected = Color.BLACK;
        selectionBoxListStyle.fontColorSelected = Color.BLACK;
        selectionBoxListStyle.background = getUIDrawable("UI_BackgroundTooltipPaper");
        selectionBoxListStyle.background.setLeftWidth(20 * Settings.getScaleFactor());
        selectionBoxListStyle.background.setRightWidth(20 * Settings.getScaleFactor());
        selectionBoxListStyle.background.setTopHeight(20 * Settings.getScaleFactor());
        selectionBoxListStyle.background.setBottomHeight(20 * Settings.getScaleFactor());
        selectionBoxListStyle.selection = skin.newDrawable("white", new Color(0x8C6F8480));
        selectionBoxListStyle.over = skin.newDrawable("white", new Color(0xB19FA480));
        selectBoxStyle.listStyle = selectionBoxListStyle;

        selectBoxStyle.scrollStyle = new ScrollPaneStyle();
        skin.add("default", selectBoxStyle);

        // slider
        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.background = getUIDrawable("UI_WightLine");
        sliderStyle.knob = getUIDrawable("UI_Settings_ValueScroll");
        skin.add("default-horizontal", sliderStyle);
    }
}
