package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.model.objects.BrickObject;

import java.io.File;
import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class GameLevelModel {
    private Label score;
    private int level = 0;
    private ModelManager modelManager;
    private List<BrickObject> bricks = new LinkedList<BrickObject>();
    private List<Vector2> bricksPositions = new LinkedList<Vector2>();

    public GameLevelModel(ModelManager model){
        modelManager = model;
        createAndPrepareScore();
    }

    public void getActors(List<Actor> actors){
        actors.clear();
        actors.add(score);
    }

    private void createAndPrepareScore(){
        score = new Label("Score: 0", modelManager.getGameSkin(), "default");
        score.setPosition(40f, Gdx.graphics.getHeight() - 100f);
        score.setWidth(100f);
        score.setHeight(40f);
        score.setName("scoreLabel");
        score.setTouchable(Touchable.disabled);
    }

    private void loadBricksPositions(int level){
        Json jsonParser = new Json();
        String jsonString = readJsonFromFile("level" + Integer.toString(level) + ".json");
        bricksPositions.clear();
        bricksPositions.addAll(jsonParser.fromJson(bricksPositions.getClass(), jsonString));
    }

    private void saveBricksPositions(){
        String fileName = "level" + Integer.toString(level) + ".json";
        String jsonString = convertBrickPositionsToJson();
        saveToFile(jsonString, fileName);
    }

    private String convertBrickPositionsToJson(){
        Json jsonParser = new Json();
        return jsonParser.toJson(bricksPositions);
    }

    private void saveToFile(String jsonString, String filename){
        FileHandle handle = Gdx.files.local(filename);
        handle.writeString(jsonString, false);
    }

    private String readJsonFromFile(String fileName){
        FileHandle handle = Gdx.files.internal( fileName);
        return handle.readString();
    }
}
