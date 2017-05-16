package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class MainMenuModel{
    private Label title;
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton quitButton;
    private Image background;
    Skin screenSkin;

    public MainMenuModel(Skin skin){
        screenSkin = skin;
        createAndPrepareTitle();
        createAndPreparePlayButton();
        createAndPrepareOptionsButton();
        createAndPrepareBackground();
        createAndPrepareQuitButton();
    }

    public void getActors(List<Actor> actors){
        actors.clear();
        actors.add(background);
        actors.add(title);
        actors.add(playButton);
        actors.add(optionsButton);
        actors.add(quitButton);
    }

    private void createAndPrepareTitle(){
        title = new Label("ArcanoiD", screenSkin);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        title.setName("titleLabel");
    }

    private void createAndPreparePlayButton(){
        playButton = new TextButton("Play", screenSkin, "default");
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/2-playButton.getHeight()/2);
        playButton.setName("playButton");
    }

    private void createAndPrepareOptionsButton(){
        optionsButton = new TextButton("Options", screenSkin, "default");
        optionsButton.setWidth(Gdx.graphics.getWidth()/2);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2-optionsButton.getWidth()/2,Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/10 - optionsButton.getHeight()/2);
        optionsButton.setName("optionsButton");
    }

    private void createAndPrepareBackground(){
        background = new Image(new Texture(Gdx.files.internal("mainMenuBackground.png")));
        background.setTouchable(Touchable.disabled);
    }

    private void createAndPrepareQuitButton(){
        quitButton = new TextButton("Quit", screenSkin, "default");
        quitButton.setWidth(Gdx.graphics.getWidth()/2);
        quitButton.setPosition(Gdx.graphics.getWidth()/2 - quitButton.getWidth()/2, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/5 - quitButton.getHeight()/2);
        quitButton.setName("quitButton");
    }
}
