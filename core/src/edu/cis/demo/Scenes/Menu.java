package edu.cis.demo.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.cis.demo.Constants;
import edu.cis.demo.DemoGame;
import edu.cis.demo.GameState;

public class Menu
{
    private Viewport viewport;
    private Stage stage;
    private DemoGame game;

    private Table table;
    public Menu(final DemoGame game)
    {
        this.game = game;
        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);

        table = new Table();
        table.setFillParent(true);

        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(0,0,0,0.5f);
        bgPixmap.fill();

        TextureRegionDrawable bgDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        table.setBackground(bgDrawable);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        TextButton restartButton = new TextButton("RESTART", buttonStyle);
        TextButton quitButton = new TextButton("QUIT", buttonStyle);

        quitButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                return false;
            }
        });

        restartButton.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setState(GameState.LEVELONE);
                return false;
            }
        });

        table.add(restartButton);
        table.row();
        table.add(quitButton);
        stage.addActor(table);
    }

    public Stage getStage()
    {
        return stage;
    }
}
