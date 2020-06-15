package edu.cis.demo.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import edu.cis.demo.DemoGame;

public class LevelOne implements Screen
{
    DemoGame myGame;
    Texture myPicture;

    public LevelOne(DemoGame game)
    {
        this.myGame = game;
        myPicture = new Texture("cat.jpg");
    }

    @Override
    public void render(float delta)
    {
        myGame.getBatch().begin(); //open batch

        myGame.getBatch().draw( myPicture, 0, 0);  //store things

        myGame.getBatch().end(); //close the batch
    }

    @Override
    public void show()
    {

    }

    @Override
    public void resize(int width, int height)
    {

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

    }
}
