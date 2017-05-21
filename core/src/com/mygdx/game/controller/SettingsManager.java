package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.Settings;

import java.io.IOException;

/**
 * This class handles Settings
 */
public class SettingsManager {
    private Settings settings;

    SettingsManager(){
        loadSettings();
    }

    /**
     * Save current settings to json file
     */
    void saveSettings(){
        settings.saveSettings();
    }

    /**
     * Load settings from json file if it exists
     */
    private void loadSettings(){
            Json jsonParser = new Json();
            String jsonString = readJsonFromFile();
            if(jsonString != null)
                settings = jsonParser.fromJson(settings.getClass(), jsonString);
    }

    private String readJsonFromFile(){
        try{
            String fileName = "settings.json";
            FileHandle handle = Gdx.files.internal( fileName);
            return handle.readString();
        }catch(NullPointerException e){
            createFreshSettings();
            return null;
        }
    }

    private void createFreshSettings(){
        settings = new Settings();
        settings.setLevel(1);
        settings.setMuteSetting(false);
    }

    /**
     * Reverse mute setting
     */
    public void changeMuteSetting(){
        settings.setMuteSetting(!settings.isMute());
    }

}
