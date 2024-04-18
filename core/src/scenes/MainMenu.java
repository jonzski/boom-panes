package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.BoomPanes;

public class MainMenu extends ScreenAdapter {

    private final BoomPanes game;
    private final SpriteBatch batch;
    private FitViewport viewport;
    private Stage stage;

    private BitmapFont font;

    private Texture backgroundTexture;

    private Texture indicationsTexture;
    private Image indications;

    private Image indicator0;
    private Image indicator1;
    private float indicatorX;
    private float indicatorY;
    private int currentSelection;
    private boolean selected;

    public MainMenu(BoomPanes game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
    }

    @Override
    public void show() {
        viewport = new FitViewport(1280, 720);
        stage = new Stage(viewport, batch);
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