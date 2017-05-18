package com.mygdx.game.model.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Kacper on 2017-05-14.
 */
public class GameObject extends Actor {
    protected ShapeRenderer shapeRenderer;
    protected Body physicalBody;

    public GameObject() {
        super();
    }

    public Vector2 getPosition(){
        return new Vector2(this.getX(), this.getY());
    }
}
