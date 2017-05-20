package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.model.objects.BallObject;
import com.mygdx.game.model.objects.BrickObject;
import com.mygdx.game.model.objects.PaddleObject;
import com.mygdx.game.model.objects.WallObject;

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

    private PaddleObject paddle;
    private BallObject ball;
    private WallObject leftWall, rightWall, ceiling;


    public GameLevelModel(ModelManager model){
        modelManager = model;
        createAndPrepareScore();
        createAndPreparePaddle();
        createAndPrepareBall();
        createAndPrepareWalls();
        createAndPrepareBricks();
    }

    public void getActors(List<Actor> actors){
        actors.clear();
        modelManager.getWorldManager().updateModelObjectsPositions();
        actors.add(score);
        actors.add(paddle);
        actors.add(ball);
        actors.addAll(bricks);
    }

    private void createAndPrepareScore(){
        score = new Label("Score: 0", modelManager.getGameSkin(), "default");
        score.setPosition(40f, Gdx.graphics.getHeight() - 100f);
        score.setWidth(100f);
        score.setHeight(40f);
        score.setName("scoreLabel");
        score.setTouchable(Touchable.disabled);
    }

    private void createAndPreparePaddle(){
        paddle = new PaddleObject();
        paddle.setWidth(120f);
        paddle.setHeight(10f);
        paddle.setName("paddleActor");
    }

    private void createAndPrepareBall(){
        ball = new BallObject(4f);
        ball.setName("ballActor");
    }

    private void createAndPrepareWalls(){
        createAndPrepareLefWall();
        createAndPrepareRightWall();
        createAndPrepareCeiling();
    }

    private void createAndPrepareLefWall(){
        leftWall = new WallObject();
        leftWall.setWidth(1f);
        leftWall.setHeight(Gdx.graphics.getHeight());
        leftWall.setPosition(-1f, 0f);
        leftWall.setName("leftWall");
    }

    private void createAndPrepareRightWall(){
        rightWall = new WallObject();
        rightWall.setWidth(1f);
        rightWall.setHeight(Gdx.graphics.getHeight());
        rightWall.setPosition(Gdx.graphics.getWidth(), 0);
        rightWall.setName("rightWall");
    }

    private void createAndPrepareCeiling(){
        ceiling = new WallObject();
        ceiling.setWidth(Gdx.graphics.getWidth());
        ceiling.setHeight(1f);
        ceiling.setPosition(0, Gdx.graphics.getHeight());
        ceiling.setName("ceiling");
    }

    private void createAndPrepareBricks(){
        //loadBricksPositions();
        if(bricksPositions.isEmpty())
            createFreshBricks();
    }

    private void createFreshBricks(){
        generateBricks(12, 18);
    }

    private void generateBricks(int bricksInRow, int levelsOfBricks){
        float gameHeight = Gdx.graphics.getHeight();
        float gameWidth = Gdx.graphics.getWidth();
        float brickWidth = gameWidth/bricksInRow;
        float brickHeight =  gameHeight/(levelsOfBricks*2);
        for(float y = gameHeight/2 + brickHeight; y < gameHeight - 2*brickHeight; y+=brickHeight + 10f)
            for(float x = brickWidth; x < gameWidth - brickWidth*2; x+= brickWidth + 10f){
                bricks.add(createBrick(x, y, brickWidth, brickHeight));
                bricksPositions.add(new Vector2(x, y));
            }
    }

    private BrickObject createBrick(float x, float y, float width, float height){
        BrickObject brick = new BrickObject();
        brick.setPosition(x, y);
        brick.setWidth(width);
        brick.setHeight(height);
        brick.setName("brick");
        return brick;
    }

    public int getLevel(){return level;}

    public List<BrickObject> getBricks() {
        return bricks;
    }

    public PaddleObject getPaddle() {
        return paddle;
    }

    public BallObject getBall() {
        return ball;
    }

    public List<WallObject> getWalls(){
        LinkedList<WallObject> walls = new LinkedList<WallObject>();
        walls.add(leftWall);
        walls.add(rightWall);
        walls.add(ceiling);
        return walls;
    }

    //TODO encapsulate these loading methods into another class(do the same for Settings and SettingsManager)

    private void loadBricksPositions(){
        Json jsonParser = new Json();
        String jsonString = readJsonFromFile("level" + Integer.toString(level) + ".json");
        bricksPositions.clear();
        bricksPositions.addAll(jsonParser.fromJson(bricksPositions.getClass(), jsonString));
    }

    public void saveBricksPositions(){
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
