package edu.cis.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import edu.cis.demo.Sprites.Player;

public class InputListener implements InputProcessor
{
    private Player player;
    private DemoGame game;

    public InputListener(Player player, DemoGame game)
    {
        this.player = player;
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if(keycode == Input.Keys.UP) //if up button pressed
        {
            player.setMovement(Constants.UP);
        }
        if(keycode == Input.Keys.RIGHT) //if right key is being pressed or held down
        {
            player.setMovement(Constants.RIGHT);
        }
        if(keycode == Input.Keys.LEFT) //if left key is being pressed or held down
        {
            player.setMovement(Constants.LEFT);
        }
        if(keycode == Input.Keys.ESCAPE)
        {
            if(game.getState() == GameState.PAUSE)
            {
                game.setState(GameState.RESUME);
            }
            else
            {
                game.setState(GameState.PAUSE);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
