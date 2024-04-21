package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.BoomPanes;

public class MainMenu extends ScreenAdapter {

    private final BoomPanes game;
    private final Stage stage;
    private final Skin skin;

    public MainMenu(BoomPanes game) {
        this.game = game;
        SpriteBatch batch = game.getSpriteBatch();
        this.stage = new Stage(new FitViewport(1280, 720), batch);

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

        // Caption Box
        stage.addActor(new Image(new Texture("caption-box.png")) {{
            setSize(Gdx.graphics.getWidth(), 64);
            setPosition(0, 0);
        }});


        // Add buttons
        TextButton singlePlayerButton = new TextButton("Single Player", skin);
        TextButton battleButton = new TextButton("Battle", skin);
        TextButton settingsButton = new TextButton("Settings", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Set button positions
        singlePlayerButton.setPosition(100, 300);
        battleButton.setPosition(100, 250);
        settingsButton.setPosition(100, 200);
        exitButton.setPosition(100, 150);

        // Add listeners to buttons
        singlePlayerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SinglePlayer(game)); // Switch to SinglePlayerScreen
            }
        });

        battleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle VS button click
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle Settings button click
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit(); // Exit the game
            }
        });

        // Add buttons to stage
        stage.addActor(singlePlayerButton);
        stage.addActor(battleButton);
        stage.addActor(settingsButton);
        stage.addActor(exitButton);

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
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose(); // Dispose the skin when done
    }
}
