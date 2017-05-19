package com.mygdx.game.model.objects;

/**
 * Created by Kacper on 2017-05-16.
 */
public class BallObject extends GameObject {
    private float radius;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public BallObject(float radius){
        super();
        this.radius = radius;

    }
}
