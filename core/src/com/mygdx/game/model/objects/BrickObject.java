package com.mygdx.game.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BrickObject extends GameObject{
    private Sound beep;
    private float originalLife;
    private float life;

    public BrickObject(){
        super();
        this.setColor(236/255f, 107/255, 107/255, 1);
        beep = Gdx.audio.newSound(Gdx.files.internal("beep.wav"));
        score = 100f;
        originalLife = 1f;
        life = originalLife;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setColorDependingOnLife();

        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.getColor());
        shapeRenderer.rect(this.getX() - this.getWidth()/2, this.getY() - this.getHeight()/2, this.getWidth(), this.getHeight());
        shapeRenderer.end();
        batch.begin();
    }

    private void setColorDependingOnLife(){
        if(life > 0 && life <= 1f)
            setColor(1.0f, 0.0f, 0.0f, 1.0f);
        if(life > 1f && life <=2f)
            setColor(1.0f, 1.0f, 0.0f, 1.0f);
    }

    public float getRemainingLife(){
        return life;
    }

    public void setOriginalLife(float life){
        this.originalLife = life;
        this.life = originalLife;
    }

    public void reviveBrick(){
        life = originalLife;
        setDead(false);
    }

    public void receiveDamage(float amount){
        life-=amount;
        beep.play();
    }

    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
    }

}
