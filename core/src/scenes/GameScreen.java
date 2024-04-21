package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.App;

public abstract class GameScreen implements Screen {

    protected final App app;
    Stage stage = new Stage();

    public GameScreen(final App app) {
        this.app = app;
    }

    public abstract void update(float delta);

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
