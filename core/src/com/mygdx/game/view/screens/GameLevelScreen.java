package com.mygdx.game.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.listeners.GameLevelListener;

import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class GameLevelScreen implements Screen {
    private ArcanoidGame game;
    private Stage gameLevelStage;
    private List<Actor> actors;
    private int level;

    public GameLevelScreen(ArcanoidGame game, ModelManager model){
        super();
        initializeAttributes(game);
        fetchActorsFromModel();
        addActorsToStage();
        gameLevelStage.addListener(new GameLevelListener(game));
    }

    private void initializeAttributes(ArcanoidGame game){
        gameLevelStage = new Stage(new ScreenViewport());
        this.game = game;
    }

    private void fetchActorsFromModel(){
        actors = game.getModel().getActorsFromScreen("GameLevelScreen");
    }

    private void addActorsToStage(){
        for(Actor actor: actors){
            gameLevelStage.addActor(actor);
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameLevelStage);
    }

    @Override
    public void render(float delta) {
        clearBlack();
        gameLevelStage.act();
        gameLevelStage.draw();
    }

    private void clearBlack(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
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
        gameLevelStage.dispose();
    }
}
