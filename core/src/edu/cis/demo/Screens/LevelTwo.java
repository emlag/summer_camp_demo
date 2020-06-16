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

public class LevelTwo implements Screen
{
    DemoGame myGame;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera camera;
    private Viewport viewport;


    public LevelTwo(DemoGame game)
    {
        this.myGame = game;

        mapLoader = new TmxMapLoader();
        map = mapLoader.load(Constants.MAP_FILENAME);
        renderer = new OrthogonalTiledMapRenderer(map);


        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

    }

    private void update(float deltaTime)
    {
        camera.update();
        renderer.setView(camera);
    }

    //TEMPORARY METHOD
    private void handleInput(float delta)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            Gdx.app.log("right key", "pressed");
            camera.position.x += 5 * 16;
        }
    }

    @Override
    public void render(float delta)
    {
        update(delta);
        handleInput(delta);

        //clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        myGame.getBatch().begin(); //open batch

        myGame.getBatch().end(); //close the batch

        myGame.getBatch().setProjectionMatrix(camera.combined);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
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