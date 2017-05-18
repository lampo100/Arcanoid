package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * Created by Kacper on 2017-05-16.
 */
public class Settings {
    private boolean mute = false;
    private int level = 0;


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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMuteSetting(boolean mute) {
        this.mute = mute;
    }
}
