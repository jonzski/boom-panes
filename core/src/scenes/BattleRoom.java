package scenes;

import classes.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import components.*;
import classes.Player;
import game.App;
import utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class BattleRoom extends GameScreen {

    private final Skin skin;
    private final Background backgroundComponent = new Background(stage);
    private final CaptionBox captionBox = new CaptionBox(stage);
    private final Button buttons = new Button(stage);
    private final int  numberOfPlayers = 4;
    private final int health = 3;
    private final String difficulty = "Easy";
    private final int coolDown = 5;

    private final List<Player> players = new ArrayList<>();

    public BattleRoom(final App app) {
        super(app);

        skin = new Skin();

        BitmapFont font = new BitmapFont();

        // Set up the button style
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;

        // Add the button style to the skin
        skin.add("default", buttonStyle);

    }

    private void initializePlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(health, "Player " + (i + 1), true);
            players.add(player);
            System.out.println(player);
            stage.addActor(player.getPlayerImage());
        }
    }

    @Override
    public void show() {
        // Background
        backgroundComponent.addBackgroundImage("battleroom-bg.png", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        captionBox.insertCaptionBox("Main Menu", 0,0);
        captionBox.addCaptionBox("caption-box.png", Gdx.graphics.getWidth(), 64, 0, 0);

        ClickListener quitListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.gsm.setScreen(ScreenManager.STATE.SINGLE);
            }
        };

        buttons.placeButton("QUIT", 50, 25, quitListener);
        initializePlayers();
        placePlayersOnCircle();

        // Set input processor
        Gdx.input.setInputProcessor(stage);
    }

    private void placePlayersOnCircle() {
        // Center of the circle
        float centerX = (float) Gdx.graphics.getWidth() / 2;
        float centerY = (float) Gdx.graphics.getHeight() / 2;

        // Radius of the circle
        float radius = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) * 0.4f;

        // Calculate the angle between each player
        float angleStep = 360f / numberOfPlayers;

        // Starting angle
        float currentAngle = 0f;

        // Iterate through each player and position them on the circle
        for (int i = 0; i < numberOfPlayers; i++) {
            // Calculate player position on the circle
            float playerX = centerX + radius * MathUtils.cosDeg(currentAngle);
            float playerY = centerY + radius * MathUtils.sinDeg(currentAngle);

            // Set player position
            Player player = players.get(i);
            player.setPosition(playerX, playerY);

            // Increment the angle for the next player
            currentAngle += angleStep;
        }
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


    public void rotatePlayer(){

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        app.gsm.setAllowBackNavigation(true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose(); // Dispose the skin when done
    }

}