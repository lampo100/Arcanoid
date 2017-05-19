package com.mygdx.game.model.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.model.objects.GameObject;

/**
 * Created by Kacper on 2017-05-15.
 */
public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    @Override
    public void beginContact(Contact contact) {
        System.out.print("Contact Made: ");
        Body firstBody = contact.getFixtureA().getBody();
        Body secondBody = contact.getFixtureB().getBody();
        String firstName = getNameFromBody(firstBody);
        String secondName = getNameFromBody(secondBody);
        System.out.println(firstName +": " + secondName);
        if(firstName.equals("floor")){
            ((GameObject)secondBody.getUserData()).setDead(true);
        }
        if(secondName.equals("floor"))
            ((GameObject)firstBody.getUserData()).setDead(true);
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
