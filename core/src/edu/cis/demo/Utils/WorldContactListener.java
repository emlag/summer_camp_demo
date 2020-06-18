package edu.cis.demo.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import edu.cis.demo.Sprites.Player;

public class WorldContactListener implements ContactListener
{
    @Override
    public void beginContact(Contact contact)
    {
        Fixture objectA = contact.getFixtureA();
        Fixture objectB = contact.getFixtureB();

//        if(objectA.getUserData() instanceof Player && objectB.getUserData() instanceof Enemy)
//        {
//            Gdx.app.log("collision", "player collided with enemy");
//            Enemy goomba = (Enemy) objectB.getUserData();
//            goomba.death();
//        }

    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }
}
