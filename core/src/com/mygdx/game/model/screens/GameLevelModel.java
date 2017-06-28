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
 * This class holds all of the actual game data, like bricks positions, game objects etc.
 */
public class GameLevelModel {
    private Label scoreLabel;
    private float score;
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

    /**
     *
     * @param actors add to the list all the actors in the game
     */
    public void getActors(List<Actor> actors){
        actors.clear();
        modelManager.getWorldManager().updateModelObjectsPositions();
        actors.add(scoreLabel);
        actors.add(paddle);
        actors.add(ball);
        actors.addAll(bricks);
    }

    private void createAndPrepareScore(){
        score = 0;
        scoreLabel = new Label("Score: " + score, modelManager.getGameSkin(), "default");
        scoreLabel.setPosition(40f, Gdx.graphics.getHeight() - 40f);
        scoreLabel.setWidth(100f);
        scoreLabel.setHeight(40f);
        scoreLabel.setName("scoreLabel");
        scoreLabel.setTouchable(Touchable.disabled);
    }

    private void createAndPreparePaddle(){
        paddle = new PaddleObject();
        paddle.setWidth(200f);
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
        generateBricks(15, 17);
    }

    private void generateBricks(int bricksInRow, int levelsOfBricks){
        float halfGameHeight = Gdx.graphics.getHeight()/2f;
        float gameWidth = Gdx.graphics.getWidth();

        float horizontalGapLength = 10f;
        float totalHorizontalGap = (1 + bricksInRow) * horizontalGapLength;
        float verticalGapLength = 5f;
        float totalVerticalGap = (1 + levelsOfBricks) * verticalGapLength;

        float brickWidth = (gameWidth - totalHorizontalGap)/bricksInRow;
        float brickHeight = (halfGameHeight - scoreLabel.getHeight() - totalVerticalGap)/levelsOfBricks;

        float x = horizontalGapLength;
        float y = halfGameHeight;
        for(float j = 1; j <= levelsOfBricks; ++j, y += (brickHeight + verticalGapLength), x = horizontalGapLength){
            for(float i = 1; i <= bricksInRow; ++i, x+= (brickWidth + horizontalGapLength) ){
                bricks.add(createBrick(x, y, brickWidth, brickHeight));
                bricksPositions.add(new Vector2(x, y));
            }
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

    /**
     * Reset model to starting state(basically resurrect all the bricks and ball if needed)
     */
    public void resetModel(){
        for(BrickObject brick: bricks)
            brick.setDead(false);
        ball.setDead(false);
        score = 0;
        scoreLabel.setText("Score: 0");
    }

    public void addScore(float score){
        Float currentScore = this.score;
        currentScore += score;
        this.score = currentScore;
        this.scoreLabel.setText("Score: " + this.score);
        System.out.println("Adding score: " + score);
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

    /**
     * Save bricks positions to json file(default name: 'level0.json')
     */
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
