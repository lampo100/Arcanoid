package com.mygdx.game.model.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.model.ModelManager;
import com.mygdx.game.model.objects.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GameLevelModel {
    private Label scoreLabel;
    private float score;
    private int level = 0;
    private ModelManager modelManager;

    private List<BrickObject> bricks = new LinkedList<BrickObject>();
    private List<Vector2> bricksPositions = new LinkedList<Vector2>();
    private List<BrickProperties> bricksProperties = new LinkedList<BrickProperties>();

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
        loadBricksPositions();
        if(bricksPositions.isEmpty())
            createFreshBricks();
        else
            createBricksFromBricksPositions();
    }

    private void loadBricksPositions(){
        try{
            bricksPositions = modelManager.getFileHandler().loadBricksPositions(0);
            bricksProperties = modelManager.getFileHandler().loadBricksProperties(0);
        }catch(IOException ignored){}
    }

    private void createBricksFromBricksPositions(){
        int i = 0;
        for(Vector2 position: bricksPositions) {
            bricks.add(createBrick(position.x, position.y,
                    bricksProperties.get(i).getWidth(), bricksProperties.get(i).getHeight(), 2.0f));
            ++i;
        }
    }

    private void createFreshBricks(){
        generateBricks(12, 8);
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
                float life = j<3 ? 2.0f : 1.0f;
                bricks.add(createBrick(x, y, brickWidth, brickHeight, life));
                bricksPositions.add(new Vector2(x, y));
                bricksProperties.add(new BrickProperties(brickWidth, brickHeight));
            }
        }
    }

    private BrickObject createBrick(float x, float y, float width, float height, float life){
        BrickObject brick = new BrickObject();
        brick.setPosition(x, y);
        brick.setWidth(width);
        brick.setHeight(height);
        brick.setName("brick");
        brick.setOriginalLife(life);
        return brick;
    }

    public void resetModel(){
        for(BrickObject brick: bricks){
            brick.reviveBrick();
        }
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

    public List<Vector2> getBricksPositions() {
        return bricksPositions;
    }

    public List<BrickProperties> getBricksProperties() {
        return bricksProperties;
    }
}
