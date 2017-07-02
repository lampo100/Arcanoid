package com.mygdx.game.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BrickObject extends GameObject{
    private Sound beep;
    private float life;

    public BrickObject(){
        super();
        this.setColor(236/255f, 107/255, 107/255, 1);
        beep = Gdx.audio.newSound(Gdx.files.internal("beep.wav"));
        score = 100f;
        life = 1f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getX() - this.getWidth()/2, this.getY() - this.getHeight()/2, this.getWidth(), this.getHeight());
        shapeRenderer.end();
        batch.begin();
    }

    public float getRemainingLife(){
        return life;
    }

    public void receiveDamage(float amount){
        life-=amount;
    }

    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
        if(dead)
            beep.play();
    }

}
