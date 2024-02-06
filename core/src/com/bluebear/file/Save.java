package com.bluebear.file;

import java.util.UUID;

public class Save {
    public SaveMetadata metadata;
    public GameData gameData;
    public static class SaveMetadata {
        public UUID saveUUID;
        public String saveName;
        public UUID gameUUID;
        public String gameName;
        public long saveTime;
        public long playTime;
        public boolean isQuickSave = false;
        public boolean isAutoSave = false;
    }

}