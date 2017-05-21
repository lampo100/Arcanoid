package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

/**
 * This is main game controller. It handles all input delegated from listeners and callbacks model methods accordingly.
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

    /**
     * Set the View to control
     */
    public void setGame(ArcanoidGame game){
        this.game = game;
    }

    /**
     * Create all managers
     */
    public void createManagers(){
        screenManager = new ScreenManager(game, model);
        settingsManager = new SettingsManager();
    }

    /**
     *
     * @return Screen manager
     */
    public ScreenManager getScreenManager() {
        return screenManager;
    }

    /**
     *
     * @return Settings manager
     */
    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public void dispose(){
        settingsManager.saveSettings();
        screenManager.dispose();
    }

}
