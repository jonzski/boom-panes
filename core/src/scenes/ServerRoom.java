package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.BoomPanes;

public class ServerRoom extends ScreenAdapter {

    private final BoomPanes game;
    private Sprite splash;
    private SpriteBatch batch;
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

    public ServerRoom(BoomPanes game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
//        stage.act(delta);
//        splash.draw(batch);
        batch.end();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        Texture splashTexture = new Texture("./assets/badlogic.jpg");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        viewport = new FitViewport(1280, 720);
//        stage = new Stage(viewport, batch);
    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
    }


    @Override
    public void dispose() {
    }

}