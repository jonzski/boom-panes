package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.App;
import scenes.GameScreen;
import utils.ScreenManager;


public class ServerRoom extends GameScreen {

    private final Skin skin;

    public ServerRoom(final App app) {
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
    public void update(float delta) {

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

        // Header Box
        stage.addActor(new Image(new Texture("Rectangle 1.png")) {{
            setSize(Gdx.graphics.getWidth(), 128);
            setPosition(0, 500);
        }});


        // Add buttons
        TextButton quitButton = new TextButton("QUIT", skin);
        TextButton startButton = new TextButton("READY", skin);
        TextButton waitButton = new TextButton("Waiting for host to start...", skin);
        TextButton roomName = new TextButton("Player 1's Room", skin);

        // Add texts


        // Set button positions
        quitButton.setPosition(50, 25);
        startButton.setPosition(1150, 25);
        waitButton.setPosition(500, 25);
        roomName.setPosition(50, 560);

        // Add listeners to buttons
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.gsm.setScreen(ScreenManager.STATE.MAIN_MENU);
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle VS button click
                app.gsm.setScreen((ScreenManager.STATE.BATTLE));
            }
        });


        // Add buttons to stage
        stage.addActor(quitButton);
        stage.addActor(startButton);
        stage.addActor(waitButton);
        stage.addActor(roomName);


        // Set input processor
        Gdx.input.setInputProcessor(stage);
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