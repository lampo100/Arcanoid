package com.mygdx.game.model.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.objects.BallObject;
import com.mygdx.game.model.objects.BrickObject;
import com.mygdx.game.model.objects.GameObject;
import com.mygdx.game.model.objects.WallObject;

import java.util.Random;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body firstBody = contact.getFixtureA().getBody();
        Body secondBody = contact.getFixtureB().getBody();
        String firstName = getNameFromBody(firstBody);
        String secondName = getNameFromBody(secondBody);
        System.out.println("Contact");
        if(firstName.equals("floor")){
            ((GameObject)secondBody.getUserData()).setDead(true);
        }else if(secondName.equals("floor")) {
            ((GameObject) firstBody.getUserData()).setDead(true);
        }else if(firstName.equals("brick")){
            dealDamageToBrick((BrickObject) firstBody.getUserData(), ((BallObject) secondBody.getUserData()).getDamage());
            killBrickIfBelowZeroLife((BrickObject) firstBody.getUserData());
            addRandomnessToLinearVelocity(secondBody);
        }else if(secondName.equals("brick")){
            dealDamageToBrick((BrickObject) secondBody.getUserData(), ((BallObject) firstBody.getUserData()).getDamage());
            killBrickIfBelowZeroLife((BrickObject) secondBody.getUserData());
            addRandomnessToLinearVelocity(firstBody);
        }else if(firstName.equals("paddleActor")){
            addRandomnessToLinearVelocity(secondBody);
        }else if(secondName.equals("paddleActor")){
            addRandomnessToLinearVelocity(firstBody);
        }else{
            if(((GameObject)firstBody.getUserData()).getClass() == WallObject.class)
                ((WallObject)firstBody.getUserData()).playSound();
            else
                ((WallObject)secondBody.getUserData()).playSound();
        }
    }

    private void dealDamageToBrick(BrickObject brick, float damage){
        brick.receiveDamage(damage);
    }

    private void killBrickIfBelowZeroLife(BrickObject brick){
        if(brick.getRemainingLife() <= 0)
            brick.setDead(true);
    }

    private void addRandomnessToLinearVelocity(Body body){
        float xVelocity;
        float yVelocity;
        if(body.getLinearVelocity().x > 0)
            xVelocity = getRandomFloatFromNewRange(10, 12);
        else
            xVelocity = getRandomFloatFromNewRange(-12, -10);
        if(body.getLinearVelocity().y > 0)
            yVelocity = getRandomFloatFromNewRange(12, 14);
        else
            yVelocity = getRandomFloatFromNewRange(-14, -12);
            body.setLinearVelocity(xVelocity, yVelocity);
    }

    private float getRandomFloatFromNewRange(float newMin, float newMax){
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
