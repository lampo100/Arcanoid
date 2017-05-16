package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.model.Settings;
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
        mainMenuModel = new MainMenuModel(gameSkin);
        gameLevelModel = new GameLevelModel(gameSkin);
        optionsModel = new OptionsModel(gameSkin);
        worldManager = new WorldManager();
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

    public void dispose(){
        gameSkin.dispose();
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

    }

    /**
     * This funtion HAS to be used after we dispose of options screen's stage in view.
     * Otherwise very bad things could happen(maybe).
     */
    public void disposeOptionsModel(){

    }
}
