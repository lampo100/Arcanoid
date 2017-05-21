package com.mygdx.game.model.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Model object containing paddle's data
 */
public class PaddleObject extends GameObject {

    public PaddleObject(){
        super();
        setColor(255f/255, 168f/255, 1f/255, 1f);
    }

    /**
     * Draw the paddle using @see com.badlogic.gdx.graphics.glutils.ShapeRenderer
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
}
