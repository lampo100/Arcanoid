package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.screens.GameLevelScreen;
import com.mygdx.game.view.screens.MainMenuScreen;
import com.mygdx.game.view.screens.OptionsScreen;
import com.mygdx.game.view.screens.YouWinScreen;

/**
 * Created by Kacper on 2017-05-15.
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

    public void pauseGameScreen(){
        gameLevelScreen.pause();
    }

    public void resumeGameScreen(){
        gameLevelScreen.resume();
    }

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

    public void dispose(){
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
