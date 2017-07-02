package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.Settings;

import java.io.IOException;

public class SettingsManager {
    private Settings settings;

    SettingsManager(){
        loadSettings();
    }

    void saveSettings(){
        settings.saveSettings();
    }

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

    public void changeMuteSetting(){
        settings.setMuteSetting(!settings.isMute());
    }

    public boolean isGameMuted(){
        return settings.isMute();
    }

}
