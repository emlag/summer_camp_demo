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
    private int currentTime;
    private float totalDeltaTime;

    private Label levelLabel;
    private Label timeLabel;
    private Label scoreLabel;
    private Label levelNumberLabel;
    private Label timeNumberLabel;
    private Label scoreNumberLabel;

    private TextButton tempButton;

    public Hud()
    {
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);

        table = new Table();
        table.top();
        table.setFillParent(true);
        table.debug();

        score = 0;
        currentTime = 300;
        totalDeltaTime = 0;
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        levelLabel = new Label("LEVEL", labelStyle);
        timeLabel = new Label("TIME", labelStyle);
        scoreLabel = new Label("SCORE", labelStyle);
        levelNumberLabel = new Label("1-1", labelStyle);
        timeNumberLabel = new Label("" + currentTime, labelStyle);
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
        table.add(timeLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        table.row();
        table.add(levelNumberLabel).expandX();
        table.add(timeNumberLabel).expandX();
        table.add(scoreNumberLabel).expandX();
        table.row();
        table.add(tempButton).colspan(3).fill();

        stage.addActor(table);
    }

    public void update(float deltaTime)
    {
        totalDeltaTime += deltaTime;
        if(totalDeltaTime >= 1)
        {
            currentTime--;
            timeNumberLabel.setText("" + currentTime);
            totalDeltaTime = 0;
        }
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
        scoreNumberLabel.setText("" + score);
    }

    public Stage getStage()
    {
        return stage;
    }
}
