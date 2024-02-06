package com.bluebear.ui.resolution;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class ResolutionManager {
    public static final Set<WeakReference<Resizable>> uiElements = new HashSet<>();
    public static void register (Resizable localizable) {
        uiElements.add(new WeakReference<>(localizable));
    }
    public static void updateUIElements () {
        SkinLoader.resetSkin();

        Set<WeakReference<Resizable>> outdated = new HashSet<>();
        for (WeakReference<Resizable> reference : new HashSet<>(uiElements)) {
            Resizable uiElement = reference.get();
            if (uiElement != null) {
                uiElement.update();
            } else {
                outdated.add(reference);
            }
        }

        uiElements.removeAll(outdated);
    }
}
