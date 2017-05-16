package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class OptionsModel {
    private Skin screenSkin;
    private Label title;
    private TextButton mainMenuButton;
    private TextButton muteButton;
    private Image background;


    public OptionsModel(Skin skin){
        screenSkin = skin;
        createAndPrepareTitle();
        createAndPrepareMainMenuButton();
        createAndPrepareMuteButton();
        createAndPrepareBackground();
    }

    public void getActors(List<Actor> actors){
        actors.clear();
        actors.add(background);
        actors.add(title);
        actors.add(mainMenuButton);
        actors.add(muteButton);
    }

    private void createAndPrepareTitle(){
        title = new Label("Options", screenSkin);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        title.setName("optionsLabel");
    }

    private void createAndPrepareMainMenuButton(){
        mainMenuButton = new TextButton("Main Menu", screenSkin, "default");
        mainMenuButton.setWidth(Gdx.graphics.getWidth()/2);
        mainMenuButton.setPosition(Gdx.graphics.getWidth()/2-mainMenuButton.getWidth()/2,Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/5 - mainMenuButton.getHeight()/2);
        mainMenuButton.setName("mainMenuButton");
    }

    private void createAndPrepareMuteButton(){
        muteButton = new TextButton("Mute", screenSkin, "toggle");
        muteButton.setWidth(Gdx.graphics.getWidth()/2);
        muteButton.setName("muteButton");
        muteButton.setPosition(Gdx.graphics.getWidth()/2 - muteButton.getWidth()/2, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/10 - muteButton.getHeight()/2);
    }

    private void createAndPrepareBackground(){
        background = new Image(new Texture(Gdx.files.internal("mainMenuBackground.png")));
        background.setTouchable(Touchable.disabled);
    }
}
