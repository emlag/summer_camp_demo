package edu.cis.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cis.demo.Constants;
import edu.cis.demo.DemoGame;
import edu.cis.demo.GameState;

public class GameOver implements Screen
{
    private Viewport viewport;
    private Stage stage;

    public DemoGame game;



    public GameOver(DemoGame game) {
        this.game = game;

        viewport =  new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverWords = new Label("GAME OVER", font);
        Label playAgainWords = new Label("PLAY AGAIN", font);

        table.add(gameOverWords).expandX();
        table.row();
        table.add(playAgainWords).expandX();

        stage.addActor(table);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if(Gdx.input.justTouched()) {
            game.setState(GameState.LEVELONE);
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1); //black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen
        stage.draw();
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
        stage.dispose();
    }
}
