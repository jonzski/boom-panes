package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import game.BoomPanes;
import jdk.javadoc.internal.doclets.formats.html.markup.Text;

public class SinglePlayer extends ScreenAdapter {

    private final BoomPanes game;
    private final Stage stage;
    private final Skin skin;

    public SinglePlayer(BoomPanes game) {
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

    public void addAnActor(String pathname, int width, int height, int x, int y) {
        stage.addActor(new Image(new Texture(pathname)) {{
            setSize(width, height);
            setPosition(x, y);
        }});
    }

    @Override
    public void show() {
        addAnActor("main-menu-bg.png", Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0);    // Main Menu Background
        addAnActor("clock-svgrepo-com 1.png", Gdx.graphics.getWidth()-500, Gdx.graphics.getHeight()-300, 300, 0);   // Clock
        addAnActor("caption-box.png", Gdx.graphics.getWidth(), 64, 0, 0);   // Caption Box
        addAnActor("Rectangle 1.png", Gdx.graphics.getWidth(), 128, 0, 550);    // Header Box

        // Options
        addAnActor("options/Player.png", Gdx.graphics.getWidth()-300, 64, 400, 400);
        addAnActor("options/Sets.png", Gdx.graphics.getWidth()-500, 54, 500, 325);
        addAnActor("options/Difficulty.png", Gdx.graphics.getWidth()-500, 54, 500, 250);
        addAnActor("options/Duration.png", Gdx.graphics.getWidth()-500, 54, 500, 175);
        addAnActor("options/Lives.png", Gdx.graphics.getWidth()-500, 54, 500, 100);

        // Add buttons
        TextButton quitButton = new TextButton("QUIT", skin);
        TextButton startButton = new TextButton("READY", skin);
        TextButton waitButton = new TextButton("Please select number of players", skin);
        TextButton roomName = new TextButton("Single Player", skin);

        // Add texts


        // Set button positions
        quitButton.setPosition(50, 25);
        startButton.setPosition(1150, 25);
        waitButton.setPosition(500, 25);
        roomName.setPosition(50, 610);

        // Add listeners to buttons
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game)); // Switch to SinglePlayerScreen
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle VS button click
                game.setScreen(new BattleRoom(game));
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
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose(); // Dispose the skin when done
    }

}