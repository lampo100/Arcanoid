package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.listeners.MainScreenListener;

import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class MainMenuScreen implements Screen {
    private Stage mainMenuStage;
    private ArcanoidGame game;
    private List<Actor> actors;

    public MainMenuScreen(ArcanoidGame game, ModelManager model){
        super();
        initializeAttributes(game);
        fetchActorsFromModel();
        addActorsToStage();
        model.playMusic();
    }

    private void initializeAttributes(ArcanoidGame game){
        mainMenuStage = new Stage(new ScreenViewport());
        this.game = game;
    }

    private void fetchActorsFromModel(){
        actors = game.getModel().getActorsFromScreen("MainMenuScreen");
    }

    private void addActorsToStage(){
        for(Actor actor: actors) {
            actor.addListener(new MainScreenListener(game, actor));
            mainMenuStage.addActor(actor);
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainMenuStage);
        Gdx.input.setCursorCatched(false);
    }

    @Override
    public void render(float delta) {
        clearWhite();
        mainMenuStage.act();
        mainMenuStage.draw();
    }

    private void clearWhite(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mainMenuStage.dispose();
    }
}
