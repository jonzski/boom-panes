package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.App;
import game.BoomPanes;

public class BattleRoom extends GameScreen {

    private final Skin skin;

    public BattleRoom(final App app) {
        super(app);

        // Create a simple default skin
        skin = new Skin();

        // Create a font for the buttons
        BitmapFont font = new BitmapFont();

        // Set up the button style
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;

        // Add the button style to the skin
        skin.add("default", buttonStyle);
    }

    @Override
    public void show() {
        // Background
        stage.addActor(new Image(new Texture("main-menu-bg.png")) {{
            setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }});

        stage.addActor(new Image(new Texture("caption-box.png")) {{
            setSize(Gdx.graphics.getWidth(), 64);
            setPosition(0, 0);
        }});


        // Set input processor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {

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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose(); // Dispose the skin when done
    }

}