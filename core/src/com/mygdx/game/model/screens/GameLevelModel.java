package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class GameLevelModel {
    private Label score;
    Skin screenSkin;

    public GameLevelModel(Skin skin){
        screenSkin = skin;
        createAndPrepareScore();
    }

    public void getActors(List<Actor> actors){
        actors.clear();
        actors.add(score);
    }

    private void createAndPrepareScore(){
        score = new Label("Score: 0", screenSkin, "default");
        score.setPosition(40f, Gdx.graphics.getHeight() - 100f);
        score.setWidth(100f);
        score.setHeight(40f);
        score.setName("scoreLabel");
      //  score.setTouchable(Touchable.disabled);
    }

}
