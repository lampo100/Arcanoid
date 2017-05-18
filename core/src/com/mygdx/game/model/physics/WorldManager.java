package com.mygdx.game.model.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.objects.BallObject;
import com.mygdx.game.model.objects.BrickObject;
import com.mygdx.game.model.objects.PaddleObject;
import com.mygdx.game.model.objects.WallObject;
import com.mygdx.game.model.screens.GameLevelModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class WorldManager {
    private World physicsWorld;
    private Body paddle;
    private Body ball;

    public World getWorld(){return physicsWorld;}

    public WorldManager(){
        physicsWorld = new World(new Vector2(0, 0), true);
        physicsWorld.setContactListener(new ContactListener());
    }

    public void movePadle(float newX){
        paddle.setTransform(newX, paddle.getPosition().y, paddle.getAngle());
    }

    public void createGameLevelBody(GameLevelModel gameLevel){
        int level = gameLevel.getLevel();
        PaddleObject paddleModel = gameLevel.getPaddle();
        createPaddle(paddleModel);
    }

    private void createPaddle(PaddleObject paddle){
        createPaddleBody(paddle);
        Shape paddleShape = createPaddleShape(paddle.getWidth()/2, paddle.getHeight()/2);
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
        bodyDef.position.set(Gdx.graphics.getWidth()/2 - paddleObject.getWidth(), 40);
        return bodyDef;
    }

    private Shape createPaddleShape(float width, float height){
        PolygonShape paddleShape = new PolygonShape();
        paddleShape.setAsBox(width, height);
        return paddleShape;
    }

    private void createAndAttachPaddleFixture(Shape padleShape){
        FixtureDef fixtureDef = createPaddleFixtureDef(padleShape);
        paddle.createFixture(fixtureDef);
    }

    private FixtureDef createPaddleFixtureDef(Shape paddleShape){
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = paddleShape;
        return fixtureDef;
    }

    public void createBrickBody(BrickObject brick){

    }

    public void createBallBody(BallObject ball){

    }

    public void createWallBody(WallObject wall){

    }

    public void updateModelObjectsPositions(){
        Array<Body> bodies = new Array<Body>();
        physicsWorld.getBodies(bodies);
        updatePositionOfEachBody(bodies);
    }

    private void updatePositionOfEachBody(Array<Body> bodies){
        for(Body body: bodies)
            updatePositionOfBody(body);
    }

    private void updatePositionOfBody(Body body){
        float newX = body.getPosition().x;
        float newY = body.getPosition().y;
        ((Actor)body.getUserData()).setPosition(newX, newY);
    }

    public void stepPhysicsWorld(){physicsWorld.step(Gdx.graphics.getDeltaTime(), 6, 2);}

    public void dispose(){
        physicsWorld.dispose();
    }

}
