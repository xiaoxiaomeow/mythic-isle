package com.bluebear.ui.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.HashSet;
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
    private static BitmapFont font;
    public static void changeTo (Locale locale) {
        current = locale;

        FileHandle base = Gdx.files.internal("i18n/ui");
        bundle = I18NBundle.createBundle(base, new java.util.Locale(current.toString()), "UTF-8");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/" + current + ".ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + allCharactersIn(bundle);
        font = generator.generateFont(parameter);

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
    public static BitmapFont getFont (String key) {
        if (current == null) {
            changeTo(defaultLocale);
        }
        return font;
    }
}
