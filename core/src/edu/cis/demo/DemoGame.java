package edu.cis.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.cis.demo.Screens.GameOver;
import edu.cis.demo.Screens.LevelOne;
import edu.cis.demo.Screens.LevelTwo;


public class DemoGame extends Game
{
	private SpriteBatch batch;
	Screen currentScreen;

	private GameState currentState;
	private GameState previousState;

	@Override
	public void create () {
		batch = new SpriteBatch();

		currentScreen = new LevelOne(this);
		setScreen(currentScreen);

		currentState = GameState.LEVELONE;
		previousState = GameState.LEVELONE;
	}

	public GameState getState()
	{
		return currentState;
	}

	public void setState(GameState newState) {
		previousState = currentState;
		currentState = newState;
	}

	@Override
	public void render () {
		super.render();

		if(currentState == GameState.LEVELONE && previousState != GameState.LEVELONE) {
			currentScreen.dispose();
			currentScreen = new LevelOne(this);
			setScreen(currentScreen);
			previousState = GameState.LEVELONE;
		}
		else if (currentState == GameState.LEVELTWO && previousState != GameState.LEVELTWO) {
			currentScreen.dispose();
			currentScreen = new LevelTwo(this);
			setScreen(currentScreen);
			previousState = GameState.LEVELTWO;
		}
		else if (currentState == GameState.ENDGAME && previousState != GameState.ENDGAME) {
			currentScreen.dispose();
			currentScreen = new GameOver(this);
			setScreen(currentScreen);
			previousState = GameState.ENDGAME;
		}
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
