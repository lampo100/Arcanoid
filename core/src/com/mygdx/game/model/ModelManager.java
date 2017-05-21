package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.model.physics.WorldManager;
import com.mygdx.game.model.screens.GameLevelModel;
import com.mygdx.game.model.screens.MainMenuModel;
import com.mygdx.game.model.screens.OptionsModel;

import java.util.LinkedList;
import java.util.List;

/**
 * This is the main Model class. It holds all the data in the game that doesn't belong to the view and manages it
 */
public class ModelManager {
    private MainMenuModel mainMenuModel;
    private GameLevelModel gameLevelModel;
    private OptionsModel optionsModel;
    private Skin gameSkin;
    private WorldManager worldManager;
    private Music music;

    /**
     * Initialize all the game data.
     */
    public void initializeModelManager(){
        gameSkin = new Skin(Gdx.files.internal("quantum-horizon-ui.json"));
        mainMenuModel = new MainMenuModel(this);
        worldManager = new WorldManager();
        gameLevelModel = new GameLevelModel(this);
        optionsModel = new OptionsModel(this);
        music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
    }

    /**
     *
     * @return current game skin
     */
    public Skin getGameSkin() {
        return gameSkin;
    }

    /**
     *
     * @param screenName
     * Whose actors should the function return
     * @return
     * Return list of all actors
     *
     */
    public List<Actor> getActorsFromScreen(String screenName){
        List<Actor> actors = new LinkedList<Actor>();
        if(screenName.equals("MainMenuScreen")){
            mainMenuModel.getActors(actors);
        }else if(screenName.equals("OptionsScreen")){
            optionsModel.getActors(actors);
        }else if(screenName.equals("GameLevelScreen")){
            gameLevelModel.getActors(actors);
        }
        return actors;
    }

    /**
     * Reset the game
     */
    public void resetGameLevel(){
        worldManager.resetBall();
        worldManager.addDeadBodiesAgain();
        gameLevelModel.resetModel();
    }

    /**
     * Create box2d physics simulation
     */
    public void createPhysicalGameLevel(){
        worldManager.createGameLevelBody(gameLevelModel);
    }

    /**
     *
     * @return box2d simulation manager
     */
    public WorldManager getWorldManager(){return worldManager;}


    public GameLevelModel getGameLevelModel(){return gameLevelModel;}

    public void muteMusic(){
        music.setVolume(0f);
    }

    public void playMusic(){
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();
    }

    public void dispose(){
        gameSkin.dispose();
        gameLevelModel.saveBricksPositions();
        music.dispose();
    }
}
