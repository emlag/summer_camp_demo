package edu.cis.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud
{
    private Viewport viewport;
    private Stage stage;
    private Table table;

    private int score;

    private Label levelLabel;
    private Label scoreLabel;
    private Label levelNumberLabel;
    private Label scoreNumberLabel;

    private TextButton tempButton;

    public Hud()
    {
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.top();
        table.setFillParent(true);
        table.debug();

        score = 0;
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        levelLabel = new Label("LEVEL", labelStyle);
        scoreLabel = new Label("SCORE", labelStyle);
        levelNumberLabel = new Label("1-1", labelStyle);
        scoreNumberLabel = new Label("" + score, labelStyle);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        tempButton = new TextButton("TEMPORARY", buttonStyle);

        tempButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.log("trigger", "button");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.add(levelLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.row();
        table.add(levelNumberLabel).expandX();
        table.add(scoreNumberLabel).expandX();
        table.row();
        table.add(tempButton).colspan(2).fill();

        stage.addActor(table);
    }

    public Stage getStage()
    {
        return stage;
    }
}
