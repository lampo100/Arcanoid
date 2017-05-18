package com.mygdx.game.view.listeners;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

/**
 * Created by Kacper on 2017-05-15.
 */
public class GameLevelListener extends InputListener{
    private ArcanoidGame game;

    public GameLevelListener(ArcanoidGame game){
            super();
            this.game = game;
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        game.getModel().getWorldManager().movePadle(x);
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        if(keycode == Input.Keys.ESCAPE)
            game.getController().getScreenManager().changeScreen("mainMenu");
        return true;
    }
}

