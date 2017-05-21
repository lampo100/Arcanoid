package com.mygdx.game.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Model object containing wall's data
 */
public class WallObject extends GameObject {
    private Sound hit;
    {
        hit = Gdx.audio.newSound(Gdx.files.internal("ballWall.wav"));
    }

    /**
     * Play sound of ball hitting the wall
     */
    public void playSound(){
        hit.play();
    }
}
