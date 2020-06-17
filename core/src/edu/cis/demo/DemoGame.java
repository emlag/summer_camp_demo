package edu.cis.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.cis.demo.Screens.LevelOne;
import edu.cis.demo.Screens.LevelTwo;


public class DemoGame extends Game
{
	public SpriteBatch batch;
	Screen currentScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		currentScreen = new LevelOne(this); //TODO: change before lesson tomorrow
		setScreen(currentScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getBatch()
	{
		return batch;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
