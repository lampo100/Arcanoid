package com.mygdx.game.model.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Kacper on 2017-05-16.
 */
public class WallObject extends GameObject {
    private Sound hit;
    {
        hit = Gdx.audio.newSound(Gdx.files.internal("ballWall.wav"));
    }

    public void playSound(){
        hit.play();
    }
}
