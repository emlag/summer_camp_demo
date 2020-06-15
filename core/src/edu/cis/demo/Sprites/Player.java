package edu.cis.demo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Sprite {
    private World world;
    public Body box2Body;

    public Player(World world) {
        this.world = world;
        definePlayerBox2d(); //add this
    }

    public void definePlayerBox2d()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32, 32);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5); //you can change these values as you want

        fixtureDef.shape = shape;
        box2Body.createFixture(fixtureDef);
    }

}
