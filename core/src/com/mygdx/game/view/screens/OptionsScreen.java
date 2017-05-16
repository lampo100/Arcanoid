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
import com.mygdx.game.view.listeners.OptionsScreenListener;

import java.util.List;

/**
 * <h1>OptionsScreen</h1>
 * This class handles options screen view
 * @author Kacper Kamiński
 */
public class OptionsScreen implements Screen {
    private Game game;
    private Stage optionsStage;
    private List<Actor> actors;

    public OptionsScreen(Game game, ModelManager model){
        super();
        initializeAttributes(game);
        fetchActorsFromModel();
        addActorsToStage();
    }

    private void initializeAttributes(Game game){
        optionsStage = new Stage(new ScreenViewport());
        this.game = game;
    }

    private void fetchActorsFromModel(){
        actors = ((ArcanoidGame)game).getModel().getActorsFromScreen("OptionsScreen");
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
