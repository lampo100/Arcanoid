package com.mygdx.game.model.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.objects.BallObject;
import com.mygdx.game.model.objects.BrickObject;
import com.mygdx.game.model.objects.PaddleObject;
import com.mygdx.game.model.objects.WallObject;
import com.mygdx.game.model.screens.GameLevelModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kacper on 2017-05-14.
 */
public class WorldManager {
    World physicsWorld;
    List<Body> avaiableBodies = new LinkedList<Body>();
    Body paddle;
    Body ball;

    public WorldManager(){
        physicsWorld = new World(new Vector2(0, 0), true);
        physicsWorld.setContactListener(new ContactListener());
    }

    public void createGameLevelBody(GameLevelModel gameLevel){
    }

    public void createPaddle(PaddleObject paddle){
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
        bodyDef.position.set(Gdx.graphics.getWidth()/2 - paddleObject.getWidth(), 40);
        return bodyDef;
    }

    private Shape createPaddleShape(float width, float height){
        PolygonShape paddleShape = new PolygonShape();
        paddleShape.setAsBox(width, height);
        return paddleShape;
    }

    private void createAndAttachPaddleFixture(Shape padleShape){
        FixtureDef fixtureDef = preatePaddleFixtureDef(padleShape);
        paddle.createFixture(fixtureDef);
    }

    private FixtureDef preatePaddleFixtureDef(Shape paddleShape){
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

    public void dispose(){
        physicsWorld.dispose();
    }

}
