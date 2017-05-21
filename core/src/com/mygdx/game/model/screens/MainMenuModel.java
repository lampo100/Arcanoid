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
import com.mygdx.game.model.ModelManager;

import java.util.List;

/**
 * This class holds all of the main menu data(things like background, elements positions, etc.)
 */
public class MainMenuModel{
    private Label title;
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton quitButton;
    private Image background;
    private ModelManager modelManager;

    public MainMenuModel(ModelManager modelManager){
        this.modelManager = modelManager;
        createAndPrepareTitle();
        createAndPreparePlayButton();
        createAndPrepareOptionsButton();
        createAndPrepareBackground();
        createAndPrepareQuitButton();
    }

    /**
     * Add to the list all the actors in the main menu
     * @param actors
     */
    public void getActors(List<Actor> actors){
        actors.clear();
        actors.add(background);
        actors.add(title);
        actors.add(playButton);
        actors.add(optionsButton);
        actors.add(quitButton);
    }

    private void createAndPrepareTitle(){
        title = new Label("ArcanoiD", modelManager.getGameSkin());
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        title.setName("titleLabel");
    }

    private void createAndPreparePlayButton(){
        playButton = new TextButton("Play", modelManager.getGameSkin(), "default");
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/2-playButton.getHeight()/2);
        playButton.setName("playButton");
    }

    private void createAndPrepareOptionsButton(){
        optionsButton = new TextButton("Options", modelManager.getGameSkin(), "default");
        optionsButton.setWidth(Gdx.graphics.getWidth()/2);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2-optionsButton.getWidth()/2,Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/10 - optionsButton.getHeight()/2);
        optionsButton.setName("optionsButton");
    }

    private void createAndPrepareBackground(){
        background = new Image(new Texture(Gdx.files.internal("mainMenuBackground.png")));
        background.setTouchable(Touchable.disabled);
    }

    private void createAndPrepareQuitButton(){
        quitButton = new TextButton("Quit", modelManager.getGameSkin(), "default");
        quitButton.setWidth(Gdx.graphics.getWidth()/2);
        quitButton.setPosition(Gdx.graphics.getWidth()/2 - quitButton.getWidth()/2, Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/5 - quitButton.getHeight()/2);
        quitButton.setName("quitButton");
    }
}
