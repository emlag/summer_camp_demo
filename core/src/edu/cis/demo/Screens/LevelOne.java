package edu.cis.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cis.demo.Constants;
import edu.cis.demo.DemoGame;
import edu.cis.demo.Sprites.Player;

public class LevelOne implements Screen
{
    DemoGame myGame;

    private World world; //add this
    private Box2DDebugRenderer box2DDebugRenderer;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera camera;
    private Viewport viewport;

    //sprites
    private Player player;

    public LevelOne(DemoGame game)
    {
        this.myGame = game;

        loadMap();

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0); //set init game cam position

        world = new World(new Vector2(0,-10), true); //gravity, don't calculate bodies that are at rest
        box2DDebugRenderer = new Box2DDebugRenderer();

        player = new Player(world); //add this

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //for ground
        for(MapObject mapObject : map.getLayers().get(Constants.GROUND_LAYER).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX() + rectangle.getWidth() / 2,
                    rectangle.getY() + rectangle.getHeight() / 2);

            body = world.createBody(bodyDef);
            shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
    }

    private void loadMap()
    {
        mapLoader = new TmxMapLoader(); //create an instance of built-in map loader object
        map = mapLoader.load(Constants.MAP_FILENAME); //using map loader object, load the tiled map that you made
        renderer = new OrthogonalTiledMapRenderer(map); //render the map.
    }

    private void update(float dt)
    {
        world.step(1/60f, 6, 2); //add this

        camera.update();
        renderer.setView(camera); //sets the view from our camera so it would render only what our camera can see.
    }

    //TEMP METHOD TO SHOW ENTIRE MAP
    private void handleInput(float dt)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) //if up button pressed
        {
            //2 ways to move objects in box2d, force (gradual change), and impulse (immediate change)
            //2nd arg: where you want to apply the impulse.  Applying it at center bcs we don't want torque
            //3rd arg: true = want to wake body up
            player.box2Body.applyLinearImpulse(new Vector2(0, 15f), player.box2Body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.box2Body.getLinearVelocity().x <= 10f) //if right key is being pressed or held down
        {
            player.box2Body.applyLinearImpulse(new Vector2(10f, 0), player.box2Body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.box2Body.getLinearVelocity().x >= -10f) //if left key is being pressed or held down
        {
            player.box2Body.applyLinearImpulse(new Vector2(-10f, 0), player.box2Body.getWorldCenter(), true);
        }
    }
    @Override
    public void render(float delta)
    {
        handleInput(delta);
        update(delta);

        //clear screen
        Gdx.gl.glClearColor(0, 0 , 0 ,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //add a b2d renderer
        box2DDebugRenderer.render(world, camera.combined);

        myGame.getBatch().begin(); //open batch

        myGame.getBatch().end(); //close the batch

        myGame.getBatch().setProjectionMatrix(camera.combined); //updates our batch with our Camera's view and projection matrices.
    }

    @Override
    public void show()
    {

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height); //viewport gets adjusted accordingly
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
