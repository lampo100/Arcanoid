package com.mygdx.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.model.objects.GameObject;
import com.mygdx.game.view.ArcanoidGame;
import com.mygdx.game.view.listeners.GameLevelListener;

import java.util.List;

/**
 * This class belongs to the View. It handles drawing of everything in game.
 */
public class GameLevelScreen implements Screen {
    private ArcanoidGame game;
    private Stage gameLevelStage;
    private List<Actor> actors;
    private Box2DDebugRenderer debuger = new Box2DDebugRenderer();
    private SpriteBatch batch = new SpriteBatch();

    public GameLevelScreen(ArcanoidGame game, ModelManager model){
        super();
        initializeAttributes(game);
        game.getModel().createPhysicalGameLevel();
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

    /**
     * Restart the game
     */
    public void resetScreen(){
        this.pause();
        for(Actor actor: gameLevelStage.getActors())
            actor.remove();
        fetchActorsFromModel();
        addActorsToStage();
        this.resume();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameLevelStage);
    }

    @Override
    public void render(float delta) {
        game.getModel().getWorldManager().stepPhysicsWorld();
        game.getModel().getWorldManager().updateModelObjectsPositions();
        removeDeadActorsFromStage();
        if(playerWon())
            game.getController().getScreenManager().changeScreen("youWin");
        clearBlack();
        gameLevelStage.act();
        gameLevelStage.draw();
       // debuger.render(game.getModel().getWorldManager().getWorld(), batch.getProjectionMatrix().cpy().scale(60, 60, 0));
    }

    private void removeDeadActorsFromStage(){
        for(Actor actor: gameLevelStage.getActors())
            if(isGameObject(actor)&&((GameObject)actor).isDead()){
                actor.remove();
                game.getModel().getGameLevelModel().addScore(((GameObject) actor).getScore());
            }
    }

    /**
     * Check if player has already won.
     * @return
     * false if player hasn't won and true if he has
     */
    private boolean playerWon(){
        for(Actor actor: gameLevelStage.getActors())
            if(actor.getName().equals("brick") && !((GameObject)actor).isDead()){
                return false;
            }
        return true;
    }

    private boolean isGameObject(Object objectToCheck){
        return objectToCheck.getClass().getGenericSuperclass().equals(GameObject.class);
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
