package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.listeners.MainScreenListener;
import com.mygdx.game.view.listeners.YouWinListener;

import java.util.List;

/**
 * Created by Kacper on 2017-05-21.
 */
public class YouWinScreen implements Screen {
    private ArcanoidGame game;
    private List<Actor> actors;
    private Stage youWinStage;
    private Image text;

    public YouWinScreen(ArcanoidGame game, ModelManager model){
        super();
        initializeAttributes(game);
        addTextToStage();
    }

    private void initializeAttributes(ArcanoidGame game){
        this.game = game;
        youWinStage = new Stage(new ScreenViewport());
        youWinStage.addListener(new YouWinListener(game));
    }

    private void addTextToStage(){
        text = new Image(new Texture(Gdx.files.internal("youWin.png")));
        text.setPosition(0f, 0f);
        youWinStage.addActor(text);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(youWinStage);
    }

    @Override
    public void render(float delta) {
        clearWhite();
        youWinStage.act();
        youWinStage.draw();
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
        youWinStage.dispose();
    }
}
