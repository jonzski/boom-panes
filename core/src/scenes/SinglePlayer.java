package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.BoomPanes;
import classes.*;

public class SinglePlayer extends ScreenAdapter {

    private final BoomPanes game;
    private final Stage stage;

    public SinglePlayer(BoomPanes game) {
        this.game = game;
        SpriteBatch batch = game.getSpriteBatch();
        this.stage = new Stage(new FitViewport(1280, 720), batch);
    }

    @Override
    public void show() {
    // Background
        stage.addActor(new Image(new Texture("main-menu-bg.png")) {{
            setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }});

    // Caption Box
        stage.addActor(new Image(new Texture("caption-box.png")) {{
            setSize(Gdx.graphics.getWidth(), 64);
            setPosition(0, 0);
        }});
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Placeholder for game rendering logic
    }

    @Override
    public void resize(int width, int height) {
        // Placeholder for resize logic
    }

    @Override
    public void hide() {
        // Placeholder for hide logic
    }

    @Override
    public void dispose() {
        // Placeholder for dispose logic
    }
}
