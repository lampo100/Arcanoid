package com.mygdx.game.model.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.objects.*;
import com.mygdx.game.model.screens.GameLevelModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class creates, manages and disposes of box2d physics simulation
 */
public class WorldManager {
    private World physicsWorld;
    private Body paddle;
    private Body ball;
    private LinkedList<Body> walls = new LinkedList<Body>();
    private Body floor;
    private LinkedList<Body> bricks = new LinkedList<Body>();
    private float PIXELS_TO_METERS_RATIO = 60;
    private boolean ballInMotion = false;

    public World getWorld(){return physicsWorld;}

    public WorldManager(){
        physicsWorld = new World(new Vector2(0, 0), true);
        physicsWorld.setContactListener(new ContactListener());
    }

    /**
     * Move the paddle to new horizontal position
     * @param newX
     */
    public void movePaddle(float newX){
        paddle.setTransform(newX/PIXELS_TO_METERS_RATIO, paddle.getPosition().y, paddle.getAngle());
    }

    /**
     * Create new physics simulation
     * @param gameLevel model that will be simulated
     */
    public void createGameLevelBody(GameLevelModel gameLevel){
        PaddleObject paddleModel = gameLevel.getPaddle();
        createPaddle(paddleModel);

        List<WallObject> wallsObjects = gameLevel.getWalls();
        createWalls(wallsObjects);
        createFloor();

        BallObject ballModel = gameLevel.getBall();
        createBall(ballModel);

        List<BrickObject> brickObjects = gameLevel.getBricks();
        createBricks(brickObjects);
    }

    private void createPaddle(PaddleObject paddle){
        createPaddleBody(paddle);
        Shape paddleShape = createPaddleShape(paddle.getWidth(), paddle.getHeight());
        createAndAttachPaddleFixture(paddleShape);
    }

    private void createPaddleBody(PaddleObject paddle){
        BodyDef bodyDef = createPaddleBodyDefinition(paddle);
        this.paddle = physicsWorld.createBody(bodyDef);
        this.paddle.setUserData(paddle);
    }

    private BodyDef createPaddleBodyDefinition(PaddleObject paddleObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(Gdx.graphics.getWidth()/(2*PIXELS_TO_METERS_RATIO), 40/PIXELS_TO_METERS_RATIO);
        return bodyDef;
    }

    private Shape createPaddleShape(float width, float height){
        PolygonShape paddleShape = new PolygonShape();
        paddleShape.setAsBox(width/(2*PIXELS_TO_METERS_RATIO), height/(2*PIXELS_TO_METERS_RATIO));
        return paddleShape;
    }

    private void createAndAttachPaddleFixture(Shape padleShape){
        FixtureDef fixtureDef = createPaddleFixtureDef(padleShape);
        paddle.createFixture(fixtureDef);
    }

    private FixtureDef createPaddleFixtureDef(Shape paddleShape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = paddleShape;
        fixtureDef.restitution = 1f;
        return fixtureDef;
    }

    private void createBall(BallObject ballModel){
        createBallBody(ballModel);
        Shape ballShape = createBallShape(ballModel.getRadius()/PIXELS_TO_METERS_RATIO);
        createAndAttachBallFixture(ballShape);
    }

    private void createBallBody(BallObject ball){
        BodyDef bodyDef = createBallBodyDefinition(ball);
        this.ball = physicsWorld.createBody(bodyDef);
        this.ball.setUserData(ball);
    }

    private BodyDef createBallBodyDefinition(BallObject ball){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        bodyDef.position.set(Gdx.graphics.getWidth()/(2*PIXELS_TO_METERS_RATIO), 180/PIXELS_TO_METERS_RATIO);
        bodyDef.linearVelocity.set(0f, 0f);
        bodyDef.angularVelocity = 5f;
        return bodyDef;
    }

    private Shape createBallShape(float radius){
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        return circleShape;
    }

    private void createAndAttachBallFixture(Shape ballShape){
        FixtureDef fixtureDef = createBallFixtureDef(ballShape);
        ball.createFixture(fixtureDef);
    }

    private FixtureDef createBallFixtureDef(Shape ballShape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.restitution = 1.0f;
        fixtureDef.friction = 0.f;
        fixtureDef.density = 1f;
        return fixtureDef;
    }

    private void createWalls(List<WallObject> walls){
        createWallsBodies(walls);
        int i = 0;
        for(Body wall: this.walls){
            Shape wallShape = createWallShape(walls.get(i).getWidth(), walls.get(i).getHeight());
            createAndAttachWallFixture(wallShape, wall);
            i++;
        }
    }

    private void createWallsBodies(List<WallObject> walls){
        BodyDef bodyDef = createWallBodyDefinition();
        for(WallObject wall: walls){
            createWallBody(wall, bodyDef);
        }
    }

    private BodyDef createWallBodyDefinition(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    private void createWallBody(WallObject wall, BodyDef bodyDef){
        bodyDef.position.set((wall.getX() + wall.getWidth()/2)/PIXELS_TO_METERS_RATIO, (wall.getY() + wall.getHeight()/2)/PIXELS_TO_METERS_RATIO);
        Body wallBody = physicsWorld.createBody(bodyDef);
        wallBody.setUserData(wall);
        walls.add(wallBody);
    }

    private Shape createWallShape(float width, float height){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/(2*PIXELS_TO_METERS_RATIO), height/(2*PIXELS_TO_METERS_RATIO));
        return shape;
    }

    private void createAndAttachWallFixture(Shape wallShape, Body wall){
        FixtureDef fixtureDef = createWallFixtureDef(wallShape);
        wall.createFixture(fixtureDef);
    }

    private FixtureDef createWallFixtureDef(Shape wallShape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = wallShape;
        fixtureDef.restitution = 1f;
        fixtureDef.friction = 0f;
        return fixtureDef;
    }

    private void createFloor(){
        createFloorBody();
        Shape shape = createFloorShape();
        createAndAttachFloorFixture(shape);
    }

    private void createFloorBody(){
        BodyDef bodyDef = createFloorBodyDefinition();
        floor = physicsWorld.createBody(bodyDef);
    }

    private BodyDef createFloorBodyDefinition(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(Gdx.graphics.getWidth()/(2*PIXELS_TO_METERS_RATIO), 0/PIXELS_TO_METERS_RATIO);
        return bodyDef;
    }

    private Shape createFloorShape(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Gdx.graphics.getWidth()/(2*PIXELS_TO_METERS_RATIO), 10/PIXELS_TO_METERS_RATIO);
        return shape;
    }

    private void createAndAttachFloorFixture(Shape floorShape){
         FixtureDef fixtureDef = createFloorFixtureDef(floorShape);
         floor.createFixture(fixtureDef);
    }

    private FixtureDef createFloorFixtureDef(Shape floorShape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = floorShape;
        return fixtureDef;
    }

    private void createBricks(List<BrickObject> bricksObjects){
        createBricksBodies(bricksObjects);
        int i = 0;
        for(Body brick: bricks){
            Shape brickShape = createBrickShape(bricksObjects.get(i).getWidth(), bricksObjects.get(i).getHeight());
            createAndAttachBrickFixture(brickShape, brick);
            i++;
        }
    }

    private void createBricksBodies(List<BrickObject> bricks){
        for(BrickObject brick: bricks){
            BodyDef bodyDef = createBrickBodyDefinition(brick);
            createBrickBody(bodyDef, brick);
        }
    }

    private BodyDef createBrickBodyDefinition(BrickObject brick){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((brick.getX()+brick.getWidth()/2)/PIXELS_TO_METERS_RATIO, (brick.getY() + brick.getHeight()/2)/PIXELS_TO_METERS_RATIO);
        return bodyDef;
    }

    private void createBrickBody(BodyDef bodyDef, BrickObject brick){
        Body brickBody = physicsWorld.createBody(bodyDef);
        brickBody.setUserData(brick);
        bricks.add(brickBody);
    }

    private Shape createBrickShape(float width, float height){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width/2)/PIXELS_TO_METERS_RATIO, (height/2)/PIXELS_TO_METERS_RATIO);
        return shape;
    }

    private void createAndAttachBrickFixture(Shape brickShape, Body brickToAttach){
        FixtureDef fixtureDef = createBrickFixtureDef(brickShape);
        brickToAttach.createFixture(fixtureDef);
    }

    private FixtureDef createBrickFixtureDef(Shape brickShape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = brickShape;
        fixtureDef.restitution = 1;
        fixtureDef.friction = 0.1f;
        return fixtureDef;
    }

    public void updateModelObjectsPositions(){
        Array<Body> bodies = new Array<Body>();
        physicsWorld.getBodies(bodies);
        updatePositionOfEachBody(bodies);
    }

    private void updatePositionOfEachBody(Array<Body> bodies){
        for(Body body: bodies)
            if(!body.getFixtureList().first().isSensor())
                if(!((GameObject)body.getUserData()).isDead())
                    updatePositionOfBody(body);
    }

    private void updatePositionOfBody(Body body){
        float newX = body.getPosition().x*PIXELS_TO_METERS_RATIO;
        float newY = body.getPosition().y*PIXELS_TO_METERS_RATIO;
        ((Actor)body.getUserData()).setPosition(newX, newY);
    }

    public void stepPhysicsWorld(){
        physicsWorld.step(1f/60f, 6, 2);
        removeDeadBodies();
    }

    private void removeDeadBodies(){
        Array<Body> bodies = new Array<Body>();
        physicsWorld.getBodies(bodies);
        for(Body body: bodies)
            if(bodyIsDead(body))
                body.setActive(false);
    }

    public void resetBall(){
        ball.setTransform(new Vector2(Gdx.graphics.getWidth()/(2*PIXELS_TO_METERS_RATIO), 180/PIXELS_TO_METERS_RATIO), 0);
        ball.setLinearVelocity(0f, 0f);
        ballInMotion = false;
    }

    public void addDeadBodiesAgain(){
        Array<Body> bodies = new Array<Body>();
        physicsWorld.getBodies(bodies);
        for(Body body: bodies)
            if(bodyIsDead(body))
                body.setActive(true);
    }

    private boolean bodyIsDead(Body body){
        try{
            return ((GameObject)body.getUserData()).isDead();
        }catch(NullPointerException e){return false;}
    }

    public void setBallInMotion(){
        ball.setLinearVelocity(0f, -8f);
        ballInMotion = true;
    }

    public boolean isBallInMotion(){
        return ballInMotion;
    }

    public void dispose(){
        physicsWorld.dispose();
    }

}
