package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

/**
 * <h1>Controller</h1>
 * This is main game controller. It handles all input delegated from listeners and callbacks model methods accordingly.
 * @author Kacper Kamiński
 *
 */
public class Controller {
    private ModelManager model;
    private ArcanoidGame game;
    private SettingsManager settingsManager;
    private ScreenManager screenManager;

    public Controller(ModelManager model){
        this.model = model;
    }

    public void setGame(ArcanoidGame game){
        this.game = game;
    }

    public void createManagers(){
        screenManager = new ScreenManager(game, model);
        settingsManager = new SettingsManager();
        System.out.println("done");
    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public void dispose(){
        settingsManager.saveSettings();
    }

}
