package com.mygdx.game.view.listeners;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

/**
 * This class handles input events in main menu.
 */
public class MainScreenListener extends InputListener {
    private Game game;
    private Actor actor;

    public MainScreenListener(Game game, Actor actor){
        super();
        this.game = game;
        this.actor = actor;
    }

    /**
     * Handle events fired by buttons
     */
    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if(actor.getName().equals("playButton")){
            System.out.println("Changing to GameScreen");
            ((ArcanoidGame)game).getController().getScreenManager().changeScreen("gameLevel");
        }else if(actor.getName().equals("optionsButton")){
            System.out.println("Changing to options");
            ((ArcanoidGame)game).getController().getScreenManager().changeScreen("options");
        }else if(actor.getName().equals("quitButton")){
            System.out.println("Leaving game");
            Gdx.app.exit();
        }
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }
}
