package com.bluebear.ui.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import com.bluebear.ui.SkinLoader;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class LocalizationManager {
    @SuppressWarnings("unused")
    public enum Locale {
        en, zh
    }
    public static Locale current;
    public static final Set<WeakReference<Localizable>> uiElements = new HashSet<>();
    public static void register (Localizable localizable) {
        uiElements.add(new WeakReference<>(localizable));
    }
    private static I18NBundle bundle;
    public static void changeTo (Locale locale) {
        current = locale;

        FileHandle base = Gdx.files.internal("i18n/ui");
        bundle = I18NBundle.createBundle(base, new java.util.Locale(current.toString()), "UTF-8");

        updateUIElements();
    }
    public static void updateUIElements () {
        SkinLoader.resetSkin();

        Set<WeakReference<Localizable>> outdated = new HashSet<>();
        for (WeakReference<Localizable> reference : uiElements) {
            Localizable uiElement = reference.get();
            if (uiElement != null) {
                uiElement.update();
            } else {
                outdated.add(reference);
            }
        }

        uiElements.removeAll(outdated);
    }
    public static String allCharacters () {
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
        if (key == null || current == null) {
            return "";
        }
        return bundle.format(key, args);
    }
}
