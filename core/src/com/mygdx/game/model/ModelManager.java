package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.model.physics.WorldManager;
import com.mygdx.game.model.screens.GameLevelModel;
import com.mygdx.game.model.screens.MainMenuModel;
import com.mygdx.game.model.screens.OptionsModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class ModelManager {
    private MainMenuModel mainMenuModel;
    private GameLevelModel gameLevelModel;
    private OptionsModel optionsModel;
    private Skin gameSkin;
    private WorldManager worldManager;



    public void initializeModelManager(){
        gameSkin = new Skin(Gdx.files.internal("quantum-horizon-ui.json"));
        mainMenuModel = new MainMenuModel(this);
        worldManager = new WorldManager();
        gameLevelModel = new GameLevelModel(this);
        optionsModel = new OptionsModel(this);
    }

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


    public void createPhysicalGameLevel(){
        worldManager.createGameLevelBody(gameLevelModel);
    }

    public WorldManager getWorldManager(){return worldManager;}

    public GameLevelModel getGameLevelModel(){return gameLevelModel;}

    public void dispose(){
        gameSkin.dispose();
        gameLevelModel.saveBricksPositions();
    }


    /**
     * This funtion HAS to be used after we dispose of main menu screen's stage in view.
     * Otherwise very bad things could happen(maybe).
     */
    public void disposeMainMenuModel(){

    }

    /**
     * This funtion HAS to be used after we dispose of game level screen's stage in view.
     * Otherwise very bad things could happen(maybe).
     */
    public void disposeGameLevelModel(){
        worldManager.dispose();
    }

    /**
     * This funtion HAS to be used after we dispose of options screen's stage in view.
     * Otherwise very bad things could happen(maybe).
     */
    public void disposeOptionsModel(){

    }
}
