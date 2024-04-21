package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.BoomPanes;

public class SinglePlayer extends ScreenAdapter {

    private final BoomPanes game;
    private final SpriteBatch batch;

    public SinglePlayer(BoomPanes game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
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
