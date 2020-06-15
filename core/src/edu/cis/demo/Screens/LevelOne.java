package edu.cis.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cis.demo.Constants;
import edu.cis.demo.DemoGame;

public class LevelOne implements Screen
{
    DemoGame myGame;
    Texture myPicture;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera camera;
    private Viewport viewport;

    public LevelOne(DemoGame game)
    {
        this.myGame = game;
        //loadMap();

        mapLoader = new TmxMapLoader(); //create an instance of built-in map loader object
        map = mapLoader.load(Constants.MAP_FILENAME); //using map loader object, load the tiled map that you made
        renderer = new OrthogonalTiledMapRenderer(map); //render the map.

        Gdx.app.log("load map", "ok");

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, camera);

        Gdx.app.log("width", "" + viewport.getWorldWidth() / 2);
        Gdx.app.log("height", "" + viewport.getWorldHeight() / 2);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0); //set init game cam position

    }

    private void loadMap()
    {
        mapLoader = new TmxMapLoader(); //create an instance of built-in map loader object
        map = mapLoader.load(Constants.MAP_FILENAME); //using map loader object, load the tiled map that you made
        renderer = new OrthogonalTiledMapRenderer(map); //render the map.
    }

    private void update(float dt)
    {
        camera.update();
        renderer.setView(camera); //sets the view from our camera so it would render only what our camera can see.
    }

    //TEMP METHOD TO SHOW ENTIRE MAP
    private void handleInput(float dt)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) //if up button pressed)
        {
            camera.position.x += 5 * 16; //move 5 tiles per right press
        }
    }
    @Override
    public void render(float delta)
    {
        update(delta);
        handleInput(delta);

        //clear screen
        Gdx.gl.glClearColor(0, 0 , 0 ,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

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

    }
}
