package com.bluebear.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.*;

public class SaveLoadManager {
    public static List<Saves> getSavesForTesting () {
        FileHandle saveFolder = Gdx.files.local("saves");
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        Json json = new Json();
        List<Saves> saves = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UUID gameUUID = UUID.randomUUID();
            for (int j = 0; j < 10; j++) {
                Save save = new Save();
                save.metadata = new Save.SaveMetadata();
                save.gameData = new GameData();
                save.metadata.saveTime = System.nanoTime() - new Random().nextLong(0, 1000000);
                save.metadata.gameUUID = gameUUID;
                save.metadata.gameName = gameUUID.toString();

                boolean found = false;
                for (Saves gameSaves : saves) {
                    if (gameSaves.gameUUID == gameUUID) {
                        gameSaves.add(save);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Saves gameSaves = new Saves(gameUUID);
                    gameSaves.add(save);
                    saves.add(gameSaves);
                }
            }
        }
        for (Saves gameSaves : saves) {
            gameSaves.sort(Comparator.comparingLong(s -> s.metadata.saveTime));
        }
        saves.sort(Comparator.comparingLong(s -> s.get(0).metadata.saveTime));
        return saves;
    }
    public static List<Saves> getSaves () {
        FileHandle saveFolder = Gdx.files.local("saves");
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        Json json = new Json();
        List<Saves> saves = new ArrayList<>();
        for (FileHandle saveFile : saveFolder.list()) {
            String serializedData = saveFile.readString();
            Save save = json.fromJson(Save.class, serializedData);
            UUID gameUUID = save.metadata.gameUUID;
            boolean found = false;
            for (Saves gameSaves : saves) {
                if (gameSaves.gameUUID == gameUUID) {
                    gameSaves.add(save);
                    found = true;
                    break;
                }
            }
            if (!found) {
                Saves gameSaves = new Saves(gameUUID);
                gameSaves.add(save);
                saves.add(gameSaves);
            }
        }
        for (Saves gameSaves : saves) {
            gameSaves.sort(Comparator.comparingLong(s -> s.metadata.saveTime));
        }
        saves.sort(Comparator.comparingLong(s -> s.get(0).metadata.saveTime));
        return saves;
    }
    public static class Saves extends ArrayList<Save> {
        public UUID gameUUID;
        public Saves (UUID gameUUID) {
            this.gameUUID = gameUUID;
        }
    }
}