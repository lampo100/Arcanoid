package com.mygdx.game.view.listeners;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;

public class OptionsScreenListener extends InputListener{
    private ArcanoidGame game;
    private Actor actor;

    public OptionsScreenListener(ArcanoidGame game, Actor actor){
        super();
        this.game = game;
        this.actor = actor;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if(actor.getName().equals("mainMenuButton")){
            System.out.println("Changing to MainMenu");
            game.getController().getScreenManager().changeScreen("mainMenu");
        }else if(actor.getName().equals("muteButton")){
            reverseMuteSetting();
        }
    }

    private void reverseMuteSetting(){
        if(game.getController().getSettingsManager().isGameMuted()){
            game.getModel().playMusic();
        }else{
            game.getModel().muteMusic();
        }
        game.getController().getSettingsManager().changeMuteSetting();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }
}
