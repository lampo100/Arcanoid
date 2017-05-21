package com.mygdx.game.view.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.view.ArcanoidGame;

/**
 * This class handles input events in "You win" screen.
 */
public class YouWinListener extends InputListener{
    private ArcanoidGame game;

    public YouWinListener(ArcanoidGame game){
        super();
        this.game = game;
    }

    /**
     * Press r to restart the game, escape to go to the main menu.
     */
    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        if(keycode == Input.Keys.ESCAPE){
            game.getController().getScreenManager().pauseGameScreen();
            game.getModel().resetGameLevel();
            game.getController().getScreenManager().resumeGameScreen();
            game.getController().getScreenManager().resetGameLevel();
            game.getController().getScreenManager().changeScreen("mainMenu");
        }
        if(keycode == Input.Keys.R){
            game.getController().getScreenManager().pauseGameScreen();
            game.getModel().resetGameLevel();
            game.getController().getScreenManager().resumeGameScreen();
            game.getController().getScreenManager().resetGameLevel();
            game.getController().getScreenManager().changeScreen("gameLevel");
        }
        return true;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }
}
