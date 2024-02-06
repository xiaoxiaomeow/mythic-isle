package com.bluebear.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Preferences;
import com.bluebear.ui.localization.LocalizationManager;
import com.bluebear.ui.resolution.SkinLoader;

import java.util.*;

public class Settings {
    private static final Preferences pref = Gdx.app.getPreferences("mystic isle.preferences");
    private static final Map<String, List<SettingChangedListener>> listeners = new HashMap<>();
    private static boolean initialized = false;
    public static void initialize () {
        if (initialized) return;
        loadListeners();
        if (!pref.contains("initialized")) {
            loadDefaultPreferences();
            pref.flush();
        }
        for (Map.Entry<String, ?> obj : pref.get().entrySet()) {
            String key = obj.getKey();
            Object value = obj.getValue();
            if (listeners.containsKey(key)) {
                for (SettingChangedListener listener : listeners.get(key)) {
                    listener.settingChanged(key, value);
                }
            }
        }
        initialized = true;
    }
    private static void loadDefaultPreferences () {
        pref.putString("language", "en");
        pref.putString("displayMode", "fullscreen");

        int width = Gdx.graphics.getWidth();
        if (width <= 1280) {
            pref.putString("resolution", "1280x720");
        } else if (width <= 1920) {
            pref.putString("resolution", "1920x1080");
        } else if (width <= 2160) {
            pref.putString("resolution", "2560x1440");
        } else if (width <= 3840) {
            pref.putString("resolution", "3840x2160");
        } else {
            pref.putString("resolution", "3840x2160");
        }

        pref.putBoolean("allowSendingData", true);
        pref.putFloat("autoSaveAmount", 10);

        pref.putBoolean("initialized", true);
    }
    public static int getWidth () {
        if (getString("displayMode").equals("fullscreen")) {
            return Gdx.graphics.getWidth();
        } else {
            String[] size = getString("resolution").split("x");
            return Integer.parseInt(size[0]);
        }

    }
    public static int getHeight () {
        if (getString("displayMode").equals("fullscreen")) {
            return Gdx.graphics.getHeight();
        } else {
            String[] size = getString("resolution").split("x");
            return Integer.parseInt(size[1]);
        }
    }
    public static float getWidthScaleFactor () {
        return 1.0f * getWidth() / 3840;
    }
    public static float getHeightScaleFactor () {
        return 1.0f * getHeight() / 2160;
    }
    public static float getScaleFactor () {
        return Math.min(getWidthScaleFactor(), getHeightScaleFactor());
    }
    private static void loadListeners () {
        addSettingsChangedListener("language", (key, value) -> LocalizationManager.changeTo(LocalizationManager.Locale.valueOf(value.toString())));

        SettingChangedListener displayListener = (key, value) -> {
            DisplayMode mode = Gdx.graphics.getDisplayMode();
            String displayMode = getString("displayMode");
            int width = getWidth();
            int height = getHeight();
            switch (displayMode) {
                case "fullscreen":
                    Gdx.graphics.setFullscreenMode(mode);
                    break;
                case "borderlessWindow":
                    Gdx.graphics.setUndecorated(true);
                    Gdx.graphics.setWindowedMode(width, height);
                    break;
                case "window":
                    Gdx.graphics.setUndecorated(false);
                    Gdx.graphics.setWindowedMode(width, height);
                    break;
            }
            SkinLoader.resetSkin();
            LocalizationManager.updateUIElements();
        };
        addSettingsChangedListener("displayMode", displayListener);
        addSettingsChangedListener("resolution", displayListener);
    }
    public static void setPreference (String key, Object value) {
        if (pref.contains(key) && Objects.equals(pref.get().get(key), value)) {
            return;
        }
        pref.put(new HashMap<>(){{put(key,value);}});
        if (listeners.containsKey(key)) {
            for (SettingChangedListener listener : listeners.get(key)) {
                listener.settingChanged(key, value);
            }
        }
        pref.flush();
    }
    public static String getString (String key) {
        return pref.getString(key, null);
    }
    public static boolean getBoolean (String key) {
        return pref.getBoolean(key, false);
    }
    public static float getFloat (String key) {
        return pref.getFloat(key, 1);
    }
    public static void addSettingsChangedListener (String key, SettingChangedListener l) {
        if (!listeners.containsKey(key)) {
            listeners.put(key, new ArrayList<>());
        }
        listeners.get(key).add(l);
    }
    public interface SettingChangedListener {
        void settingChanged (String key, Object value);
    }
}
