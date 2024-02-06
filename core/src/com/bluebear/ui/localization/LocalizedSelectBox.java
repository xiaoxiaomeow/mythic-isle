package com.bluebear.ui.localization;

import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.utils.Array;
import com.bluebear.ui.resolution.SkinLoader;

public class LocalizedSelectBox extends SelectBox<String> implements Localizable {

    private String[] itemKeys;
    private final String style;
    public LocalizedSelectBox(String[] itemKeys) {
        this(itemKeys, "default");
    }

    public LocalizedSelectBox(String[] itemKeys, String style) {
        super(SkinLoader.getSkin(), style);
        this.itemKeys = itemKeys;
        this.style = style;
        update();
        LocalizationManager.register(this);
    }
    @Override
    public void update() {
        Array<String> list = new Array<>();
        for (String key : itemKeys) {
            list.add(LocalizationManager.get(key));
        }
        setItems(list);
        setStyle(SkinLoader.getSkin().get(style, SelectBoxStyle.class));
    }
    public void setItemKeys(String[] keys) {
        this.itemKeys = keys;
        update();
    }
}
