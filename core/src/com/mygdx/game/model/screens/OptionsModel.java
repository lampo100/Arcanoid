package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.model.ModelManager;

import java.util.List;

public class OptionsModel {
    private ModelManager modelManager;
    private Label title;
    private TextButton mainMenuButton;
    private TextButton muteButton;
    private Image background;


    public OptionsModel(ModelManager model){
        modelManager = model;
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
        title = new Label("Options", modelManager.getGameSkin());
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        title.setName("optionsLabel");
    }

    private void createAndPrepareMainMenuButton(){
        mainMenuButton = new TextButton("Main Menu", modelManager.getGameSkin(), "default");
        mainMenuButton.setWidth(Gdx.graphics.getWidth()/2);
        mainMenuButton.setPosition(Gdx.graphics.getWidth()/2-mainMenuButton.getWidth()/2,Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/5 - mainMenuButton.getHeight()/2);
        mainMenuButton.setName("mainMenuButton");
    }

    private void createAndPrepareMuteButton(){
        muteButton = new TextButton("Mute", modelManager.getGameSkin(), "toggle");
        muteButton.setWidth(Gdx.graphics.getWidth()/2);
        muteButton.setName("muteButton");
        muteButton.setPosition(Gdx.graphics.getWidth()/2 - muteButton.getWidth()/2, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/10 - muteButton.getHeight()/2);
    }

    private void createAndPrepareBackground(){
        background = new Image(new Texture(Gdx.files.internal("mainMenuBackground.png")));
        background.setTouchable(Touchable.disabled);
    }
}
