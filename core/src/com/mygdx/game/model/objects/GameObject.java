package com.mygdx.game.model.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * General game object
 */
public class GameObject extends Actor {
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Color color = new Color(0f, 0f, 0f, 1);
    private boolean dead = false;

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setColor(Color color){this.color = color;}

}
