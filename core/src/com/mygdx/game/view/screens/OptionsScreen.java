package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.listeners.OptionsScreenListener;

import java.util.List;

/**
 * This class belongs to the View. It handles drawing of everything in the options.
 */
public class OptionsScreen implements Screen {
    private ArcanoidGame game;
    private Stage optionsStage;
    private List<Actor> actors;

    public OptionsScreen(ArcanoidGame game, ModelManager model){
        super();
        initializeAttributes(game);
        fetchActorsFromModel();
        addActorsToStage();
    }

    private void initializeAttributes(ArcanoidGame game){
        optionsStage = new Stage(new ScreenViewport());
        this.game = game;
    }

    private void fetchActorsFromModel(){
        actors = game.getModel().getActorsFromScreen("OptionsScreen");
    }

    private void addActorsToStage(){
        for(Actor actor: actors) {
            actor.addListener(new OptionsScreenListener(game, actor));
            optionsStage.addActor(actor);
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(optionsStage);
    }

    @Override
    public void render(float delta) {
        clearWhite();
        optionsStage.act();
        optionsStage.draw();
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
        optionsStage.dispose();
    }
}
