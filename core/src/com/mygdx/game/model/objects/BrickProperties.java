package com.mygdx.game.model.objects;

/**
 * Created by Kacper on 29.06.2017.
 */
public class BrickProperties {
    float width;
    float height;

    public BrickProperties(){}

    public BrickProperties(float width, float height){
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
