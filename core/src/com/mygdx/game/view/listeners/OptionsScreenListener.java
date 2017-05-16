package com.mygdx.game.view.listeners;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

/**
 * Created by Kacper on 2017-05-15.
 */
public class OptionsScreenListener extends InputListener{
    Game game;
    Actor actor;

    public OptionsScreenListener(Game game, Actor actor){
        super();
        this.game = game;
        this.actor = actor;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if(actor.getName().equals("mainMenuButton")){
            System.out.println("Changing to MainMenu");
            ((ArcanoidGame)game).getController().getScreenManager().changeScreen("mainMenu");
        }else if(actor.getName().equals("muteButton")){
            ((ArcanoidGame)game).getController().getSettingsManager().muteGame();
        }
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }
}
