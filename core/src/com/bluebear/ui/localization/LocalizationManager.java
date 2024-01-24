package com.bluebear.ui.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocalizationManager {
    public enum Locale {
        en, zh
    }
    public static Locale current;
    public static Locale defaultLocale = Locale.en;
    public static final Set<Localizable> uiElements = new HashSet<>();
    public static void register (Localizable localizable) {
        uiElements.add(localizable);
    }
    public static void unRegister (Localizable localizable) {
        uiElements.remove(localizable);
    }
    private static I18NBundle bundle;
    private static Map<String, Map<Integer, BitmapFont>> fonts;
    public static void changeTo (Locale locale) {
        current = locale;
        fonts = new HashMap<>();

        FileHandle base = Gdx.files.internal("i18n/ui");
        bundle = I18NBundle.createBundle(base, new java.util.Locale(current.toString()), "UTF-8");

        for (Localizable uiElement : uiElements) {
            uiElement.update();
        }
    }
    public static String allCharactersIn (I18NBundle bundle) {
        Set<Character> chars = new HashSet<>();
        for (String key : bundle.keys()) {
            String value = bundle.get(key);
            for (char c : value.toCharArray()) {
                chars.add(c);
            }
        }
        StringBuilder allChars = new StringBuilder();
        for (Character c : chars) {
            allChars.append(c);
        }
        return allChars.toString();
    }
    public static String get (String key, Object... args) {
        if (current == null) {
            changeTo(defaultLocale);
        }
        return bundle.format(key, args);
    }
    public static BitmapFont getFont (int size) {
        return getFont("default", size);
    }
    public static BitmapFont getFont (String key, int size) {
        if (current == null) {
            changeTo(defaultLocale);
        }
        if (!fonts.containsKey(key)) {
            fonts.put(key, new HashMap<>());
        }
        Map<Integer, BitmapFont> keyFonts = fonts.get(key);
        if (!keyFonts.containsKey(size)) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/" + current + "_" + key + ".ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + allCharactersIn(bundle);

            parameter.size = size;
            BitmapFont font = generator.generateFont(parameter);
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            keyFonts.put(size, font);

            generator.dispose();
        }
        return keyFonts.get(size);
    }
}
