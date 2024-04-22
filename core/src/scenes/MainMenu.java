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
import game.App;
import utils.ScreenManager;

public class MainMenu extends GameScreen {
    private final Skin skin;

    public MainMenu(final App app) {
        super(app);

        skin = new Skin();

        // Create a font for the buttons
        BitmapFont font = new BitmapFont();

        font.getData().setScale(2);

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

        stage.addActor(new Image(new Texture("mainmenu.png")) {{
            setSize(Gdx.graphics.getWidth(), 140);
            setPosition(0, 540);
        }});

        stage.addActor(new Image(new Texture("bomb.png")) {{
            setSize(650, 650);
            setPosition(-100, 0);
        }});

        stage.addActor(new Image(new Texture("button1.png")) {{
            setSize(600, 70);
            setPosition(700, 400);
        }});

        stage.addActor(new Image(new Texture("button2.png")) {{
            setSize(600, 70);
            setPosition(700, 320);
        }});

        stage.addActor(new Image(new Texture("button3.png")) {{
            setSize(600, 70);
            setPosition(700, 240);
        }});

        stage.addActor(new Image(new Texture("button4.png")) {{
            setSize(600, 70);
            setPosition(700, 160);
        }});


        // Add buttons
        TextButton singlePlayerButton = new TextButton("Single Player", skin);
        TextButton battleButton = new TextButton("Battle", skin);
        TextButton settingsButton = new TextButton("Settings", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Set button positions
        singlePlayerButton.setPosition(800, 420);
        battleButton.setPosition(800, 340);
        settingsButton.setPosition(800, 260);
        exitButton.setPosition(800, 180);


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

    }

}
