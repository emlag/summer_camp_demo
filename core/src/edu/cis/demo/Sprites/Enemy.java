package edu.cis.demo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import edu.cis.demo.Screens.LevelOne;

public class Enemy extends Sprite
{
    private World world;
    private Body  box2Body;
    private TextureRegion alive;

    public Enemy(World world, LevelOne screen, float x, float y) {

        this.world = world;

        alive = new TextureRegion(screen.getAtlas().findRegion("goomba"), 0,0,16,16);

        setPosition(x, y);
        setBounds(getX(), getY(), 16,16);
        defineBody();
        setRegion(alive);
    }

    public void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8); //you can change these values as you want

        fixtureDef.shape = shape;
        box2Body.createFixture(fixtureDef).setUserData(this);
    }

    public void update(float dt) {
        setPosition(box2Body.getPosition().x - getWidth() / 2, box2Body.getPosition().y - getHeight() / 2);
    }

}
