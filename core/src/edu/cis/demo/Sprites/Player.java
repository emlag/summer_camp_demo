package edu.cis.demo.Sprites;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import edu.cis.demo.Constants;
import edu.cis.demo.Screens.LevelOne;

public class Player extends Sprite {
    private World world;
    public Body box2Body;

    //texture regions
    private TextureRegion playerIdleTexture;

    //animation frames
    private Animation<TextureRegion> marioRun;
    private TextureRegion marioJump;
    private Animation<TextureRegion> growMario;

    //states
    public enum State {FALLING, JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    private float stateTimer;
    private boolean runningToRight;

    public Player(World world, LevelOne screen) {
        super(screen.getAtlas().findRegion(Constants.LITTLE_MARIO_STRING));

        playerIdleTexture = new TextureRegion(screen.getAtlas()
                .findRegion(Constants.LITTLE_MARIO_STRING), 0, 0, 16, 16);

        currentState = State.STANDING;
        runningToRight = true;

        this.world = world;

        previousState = State.STANDING;
        stateTimer = 0;

        //making animations
        Array<TextureRegion> frames = new Array<TextureRegion>(); //make sure to use the badlogic's Array

        //for little mario run animations
        for(int i = Constants.RUN_ANIM_STRT; i < Constants.RUN_ANIM_END; i++) //i = 1 because the run animation's sprite starts at 1 (starts from 0 from the left
        {
            frames.add(new TextureRegion(screen.getAtlas().findRegion(Constants.LITTLE_MARIO_STRING), i * 16, 0, 16, 16));
        }
        marioRun = new Animation<>(0.1f, frames);
        frames.clear();

        //for jump animations
        marioJump = new TextureRegion(screen.getAtlas().findRegion(Constants.LITTLE_MARIO_STRING), 80, 0, 16, 16);

        playerIdleTexture = new TextureRegion(screen.getAtlas().findRegion(Constants.LITTLE_MARIO_STRING), 0, 0, 16, 16);

        definePlayerBox2d(); //add this

        setBounds(0, 0, 16, 16);
        setRegion(playerIdleTexture);
    }

    public void definePlayerBox2d()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32, 32);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2Body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8); //you can change these values as you want

        fixtureDef.shape = shape;
        box2Body.createFixture(fixtureDef);
    }

    //returns appropriate frame needed to display as sprite's texture region
    private TextureRegion getFrame(float deltaT)
    {
        currentState = getState();
        TextureRegion region;
        switch(currentState)
        {
            case JUMPING:
                region = marioJump;
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer, true);
                break;

            //no breaks for next 3 cases because the following code applies to all 3 cases
            case FALLING:
            case STANDING:
            default:
                region = playerIdleTexture;
                break;
        }

        //region.isFlipX() = true if texture is flipped i.e. Mario running to left
        //if Mario is standing facing right, flip him and run to left
        if((box2Body.getLinearVelocity().x < 0 || !runningToRight) && !region.isFlipX())
        {
            region.flip(true, false);
            runningToRight = false;
        }
        //if Mario is standing facing left, flip him and run to right
        else if((box2Body.getLinearVelocity().x > 0 || runningToRight) && region.isFlipX())
        {
            region.flip(true, false);
            runningToRight = true;
        }

        //if currentState != previousState, it means Mario's in a new state and must reset timer
        stateTimer = currentState == previousState ? stateTimer + deltaT : 0;
        previousState = currentState;
        return region;
    }

    public State getState()
    {
        if(box2Body.getLinearVelocity().y > 0 || box2Body.getLinearVelocity().y < 0 && previousState == State.JUMPING)
        {
            return State.JUMPING;
        }
        if(box2Body.getLinearVelocity().y < 0) //is true only when he's falling off the edge.  If he's falling due to jumping, the previous conditional will catch that
        {
            return State.FALLING;
        }
        else if(box2Body.getLinearVelocity().x != 0) //is moving
        {
            return State.RUNNING;
        }
        return State.STANDING;
    }

    public void update(float dt)
    {
        //set to position of bottom left corner of box2dbody
        setPosition(box2Body.getPosition().x - getWidth() / 2, box2Body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }
}
