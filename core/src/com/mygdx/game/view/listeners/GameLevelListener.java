package com.mygdx.game.view.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.view.ArcanoidGame;

public class GameLevelListener extends InputListener{
    private ArcanoidGame game;

    public GameLevelListener(ArcanoidGame game){
            super();
            this.game = game;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(!game.getModel().getWorldManager().isBallInMotion()){
            game.getModel().getWorldManager().setBallInMotion();
        }
        return false;
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        game.getModel().getWorldManager().movePaddle(x);
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        if(keycode == Input.Keys.ESCAPE)
            game.getController().getScreenManager().changeScreen("mainMenu");
        if(keycode == Input.Keys.Q)
            game.getModel().getGameLevelModel().getPaddle().setColor(0.78f, 0.3f, 0.1f, 1);
        if(keycode == Input.Keys.Z)
            game.getModel().getGameLevelModel().getPaddle().setColor(0.99f, 0.37f, 0.67f, 1);
        if(keycode == Input.Keys.R){
            game.getController().getScreenManager().pauseGameScreen();
            game.getModel().resetGameLevel();
            game.getController().getScreenManager().resumeGameScreen();
            game.getController().getScreenManager().resetGameLevel();
        }
        return true;
    }
}

