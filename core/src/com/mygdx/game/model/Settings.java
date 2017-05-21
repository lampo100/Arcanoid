package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * This class containg game settings.
 */
public class Settings {
    private boolean mute = false;
    private int level = 0;

    /**
     * Save setting to json file(default name 'settings.json')
     */
    public void saveSettings(){
        String fileName = "settings.json";
        String jsonString = convertSettingsToJson();
        saveToFile(jsonString, fileName);
    }

    private String convertSettingsToJson(){
        Json jsonParser = new Json();
        return jsonParser.toJson(this);
    }

    private void saveToFile(String jsonString, String filename){
        FileHandle handle = Gdx.files.local(filename);
        handle.writeString(jsonString, false);
    }

    /**
     *
     * @return current game level
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     * set current game level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Check if game is muted
     */
    public boolean isMute() {
        return mute;
    }

    /**
     * Change mute setting
     */
    public void setMuteSetting(boolean mute) {
        this.mute = mute;
    }
}
