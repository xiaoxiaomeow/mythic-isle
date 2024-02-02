package com.bluebear.ui.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {
    private static Preferences pref = Gdx.app.getPreferences("settings");
    private static Map<String, List<SettingChangedListener>> listeners;
    public static void initialize () {
        if (pref.contains("initialized")) {
            for (Map.Entry<String, ?> obj : pref.get().entrySet()) {
                String key = obj.getKey();
                Object value = obj.getValue();
                if (listeners.containsKey(key)) {
                    for (SettingChangedListener listener : listeners.get(key)) {
                        listener.settingChanged(key, value);
                    }
                }
            }
        } else {
            loadDefaultPreferences();
        }
    }
    private static void loadDefaultPreferences () {

    }
    public void setPreference (String key, Object value) {
        pref.put(new HashMap<>(){{put(key,value);}});
        if (listeners.containsKey(key)) {
            for (SettingChangedListener listener : listeners.get(key)) {
                listener.settingChanged(key, value);
            }
        }
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
