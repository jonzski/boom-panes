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

public class MainMenu extends ScreenAdapter {

    private final BoomPanes game;
    private final SpriteBatch batch;
    private FitViewport viewport;
    private Stage stage;
    private Image backgroundImage;

    public MainMenu(BoomPanes game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
    }

    @Override
    public void show() {
        viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport, batch);

        // Load the background image
        Texture backgroundTexture = new Texture("main-menu-bg.png");

        // Create an Image Actor with the background texture
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Add the background image to the stage
        stage.addActor(backgroundImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
