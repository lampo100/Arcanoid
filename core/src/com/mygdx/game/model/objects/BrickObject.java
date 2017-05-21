package com.mygdx.game.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Model object containing brick's data
 */
public class BrickObject extends GameObject{
    private Sound beep;

    public BrickObject(){
        super();
        this.setColor(236/255f, 107/255, 107/255, 1);
        beep = Gdx.audio.newSound(Gdx.files.internal("beep.wav"));
    }
    /**
     * Draw the brick using @see com.badlogic.gdx.graphics.glutils.ShapeRenderer
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getX() - this.getWidth()/2, this.getY() - this.getHeight()/2, this.getWidth(), this.getHeight());
        shapeRenderer.end();
        batch.begin();
    }

    /**
     * Set the brick as dead and play sound
     */
    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
        if(dead)
            beep.play();
    }

}
