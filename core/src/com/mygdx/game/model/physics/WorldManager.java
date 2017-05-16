package com.mygdx.game.model.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
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

    public WorldManager(){
        physicsWorld = new World(new Vector2(0, 0), true);
        physicsWorld.setContactListener(new ContactListener());
    }

    public void createGameLevelBody(GameLevelModel gameLevel){

    }

    public void createPaddleBody(PaddleObject paddle){

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
