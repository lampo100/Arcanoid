package com.mygdx.game.model.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.objects.GameObject;

import java.util.Random;

/**
 * Created by Kacper on 2017-05-15.
 */
public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Body firstBody = contact.getFixtureA().getBody();
        Body secondBody = contact.getFixtureB().getBody();
        String firstName = getNameFromBody(firstBody);
        String secondName = getNameFromBody(secondBody);
        if(firstName.equals("floor")){
            ((GameObject)secondBody.getUserData()).setDead(true);
        }
        if(secondName.equals("floor")) {
            ((GameObject) firstBody.getUserData()).setDead(true);
        }
        if(firstName.equals("brick")){
            ((GameObject)firstBody.getUserData()).setDead(true);
            addRandomnessToLinearVelocity(secondBody);
        }else if(secondName.equals("brick")){
            ((GameObject)secondBody.getUserData()).setDead(true);
            addRandomnessToLinearVelocity(firstBody);
        }
        if(firstName.equals("paddleActor")){
            addRandomnessToLinearVelocity(secondBody);
        }else if(secondName.equals("paddleActor")){
            addRandomnessToLinearVelocity(firstBody);
        }
    }

    private void addRandomnessToLinearVelocity(Body body){
        float xVelocity;
        float yVelocity;
        if(body.getLinearVelocity().x > 0)
            xVelocity = getRandomFloat(8, 10);
        else
            xVelocity = getRandomFloat(-10, -8);
        if(body.getLinearVelocity().y > 0)
            yVelocity = getRandomFloat(8, 10);
        else
            yVelocity = getRandomFloat(-10, -8);
            body.setLinearVelocity(xVelocity, yVelocity);
        System.out.println(xVelocity + ":" + yVelocity);
    }

    private float getRandomFloat(float newMin, float newMax){
        Random random = new Random();
        float oldRange = 1f;
        float newRange = (newMax - newMin);
        return (((random.nextFloat() - 0)*newRange) / oldRange) + newMin;
    }

    private String getNameFromBody(Body body){
        if(body.getUserData() == null)
            return "floor";
        else
            return ((GameObject)body.getUserData()).getName();
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
