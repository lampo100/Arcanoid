package com.mygdx.game.controller;

import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.screens.GameLevelScreen;
import com.mygdx.game.view.screens.MainMenuScreen;
import com.mygdx.game.view.screens.OptionsScreen;
import com.mygdx.game.view.screens.YouWinScreen;

/**
 * This is screens(from View) manager. It creates and disposes of all screens in game. It handles changing of the current screen in game.
 */
public class ScreenManager {
    private MainMenuScreen mainMenuScreen;
    private GameLevelScreen gameLevelScreen;
    private OptionsScreen optionsScreen;
    private YouWinScreen youWinScreen;
    private ArcanoidGame game;
    private ModelManager model;

    ScreenManager(ArcanoidGame game, ModelManager model){
        this.game = game;
        this.model = model;
    }

    /**
     * Change current game screen.
     * @param which acceptable strings: "mainMenu", "options", "gameLevel", "youWin"
     */
    public void changeScreen(String which){
        if(which.equals("mainMenu")){
            game.setScreen(getMainMenuScreen());
        }else if(which.equals("options")){
            game.setScreen(getOptionsScreen());
        }else if(which.equals("gameLevel")){
            game.setScreen(getGameLevelScreen());
        }else if(which.equals("youWin")){
            game.setScreen(getYouWinScreen());
        }
    }

    /**
     * Pause game level screen.
     */
    public void pauseGameScreen(){
        gameLevelScreen.pause();
    }

    /**
     * Resume game level screen
     */
    public void resumeGameScreen(){
        gameLevelScreen.resume();
    }

    /**
     * Reset game screen to its initial state
     */
    public void resetGameLevel(){
        gameLevelScreen.resetScreen();
    }

    private MainMenuScreen getMainMenuScreen() {
        if(mainMenuScreen == null)
            mainMenuScreen = new MainMenuScreen(game, model);
        return mainMenuScreen;
    }

    private YouWinScreen getYouWinScreen() {
        if(youWinScreen == null)
            youWinScreen = new YouWinScreen(game, model);
        return youWinScreen;
    }

    public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
        if(this.mainMenuScreen == null)
            this.mainMenuScreen = mainMenuScreen;
    }

    private GameLevelScreen getGameLevelScreen() {
        if(gameLevelScreen == null)
            gameLevelScreen = new GameLevelScreen(game, model);
        return gameLevelScreen;
    }

    public void setGameLevelScreen(GameLevelScreen gameLevelScreen) {
        if(this.gameLevelScreen == null)
            this.gameLevelScreen = gameLevelScreen;
    }

    private OptionsScreen getOptionsScreen() {
        if(optionsScreen == null)
            optionsScreen = new OptionsScreen(game, model);
        return optionsScreen;
    }

    public void setOptionsScreen(OptionsScreen optionsScreen) {
        if(this.optionsScreen == null)
            this.optionsScreen = optionsScreen;
    }

    void dispose(){
        if(mainMenuScreen != null)
            mainMenuScreen.dispose();
        if(gameLevelScreen != null)
            gameLevelScreen.dispose();
        if(optionsScreen != null)
            optionsScreen.dispose();
        if(youWinScreen != null)
            youWinScreen.dispose();
    }
}
