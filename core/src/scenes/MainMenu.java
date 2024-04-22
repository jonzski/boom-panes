package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import components.Background;
import components.CaptionBox;
import game.App;
import utils.ScreenManager;

public class MainMenu extends GameScreen {
    private final Skin skin;
    private final Background backgroundComponent = new Background(stage);
    private final CaptionBox captionBox = new CaptionBox(stage);


    public MainMenu(final App app) {
        super(app);

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
        backgroundComponent.addBackgroundImage("main-menu-bg.png", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        captionBox.addCaptionBox("caption-box.png", Gdx.graphics.getWidth(), 64, 0, 0);
        captionBox.insertCaptionBox("Main Menu",  (float) Gdx.graphics.getWidth() /2 , (float) 16);


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
                app.gsm.setScreen(ScreenManager.STATE.SINGLE);
            }
        });

        battleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               app.gsm.setScreen(ScreenManager.STATE.LOBBY);
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              app.gsm.setScreen(ScreenManager.STATE.SETTING);
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
    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        app.gsm.setAllowBackNavigation(true); // Allow back navigation when hiding the screen
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
    }

}
