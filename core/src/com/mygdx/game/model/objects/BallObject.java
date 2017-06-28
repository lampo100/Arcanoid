package com.mygdx.game.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Model object containing ball's data
 */
public class BallObject extends GameObject {
    private float radius;
    private Sound gameOver;

    /**
     *
     * @return ball radius
     */
    public float getRadius() {
        return radius;
    }

    public BallObject(float radius){
        super();
        this.radius = radius;
        gameOver = Gdx.audio.newSound(Gdx.files.internal("gameOver.wav"));
        setColor(new Color(55f/255, 244f/255, 46f/255, 1f));
    }

    /**
     * Draw the ball using @see com.badlogic.gdx.graphics.glutils.ShapeRenderer
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.circle(this.getX(), this.getY(), this.getRadius());
        shapeRenderer.end();
        batch.begin();
    }

    /**
     * Set the ball as dead and play sound
     */
    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
        if(dead)
            gameOver.play();
    }
}
