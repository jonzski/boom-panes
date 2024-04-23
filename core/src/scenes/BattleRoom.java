package scenes;

import classes.Health;
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
import game.App;
import utils.ScreenManager;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class BattleRoom extends GameScreen {

    private final Skin skin;
    private final Background backgroundComponent = new Background(stage);
    private final CaptionBox captionBox = new CaptionBox(stage);
    private final Button buttons = new Button(stage);
    private final int  numberOfPlayers = 4;
    private final int health = 4;
    private final int coolDown = 5;

    private final List<Player> players = new ArrayList<>();

    public BattleRoom(final App app) {
        super(app);

        BitmapFont font = new BitmapFont();

        skin = new Skin();

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;

        // Add the button style to the skin
        skin.add("default", buttonStyle);

    }

    private void initializePlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(health, "Player " + (i + 1), false);
            players.add(player);
            stage.addActor(player.getPlayerImage());
            for (Health health : player.getHearts()) {
                stage.addActor(health);
            }
            stage.addActor(player.getNameLabel());
        }
    }

    @Override
    public void show() {
        // Clear existing players and stage
        players.clear();
        stage.clear();

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
        float centerX = (float) Gdx.graphics.getWidth() / 3;
        float centerY = (float) Gdx.graphics.getHeight() / 2;

        float maxRadius = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) * 0.4f;

        int maxPlayers = (int) (2 * Math.PI * maxRadius / 48);

        int numPlayers = Math.min(numberOfPlayers, maxPlayers);

        float radius = maxRadius / 2 * (1 / MathUtils.cosDeg((float) 180 / numPlayers));

        float angleStep = 360f / numPlayers;

        float currentAngle = 0f;

        for (int i = 0; i < numPlayers; i++) {
            float playerX = centerX + radius * MathUtils.cosDeg(currentAngle);
            float playerY = centerY + radius * MathUtils.sinDeg(currentAngle);

            Player player = players.get(i);
            player.setPosition(playerX, playerY);
            player.placeHeart();
            player.placeLabel();

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

//         simulate random players not answering
        while(players.size() > 1) {
            try {
                // Sleep for 2 seconds
                Thread.sleep(2000); // Sleep for 5000 milliseconds (5 seconds)
            } catch (InterruptedException e) {
                // Handle the InterruptedException, if needed
                e.printStackTrace();
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(players.size());
            Player loseLife = players.get(randomIndex);
            System.out.println(loseLife.getName() + " lost a life...");
            loseLife.reducePlayerHealth();
            if (loseLife.getStatus()){
                System.out.println(loseLife.getName() + " died!");
                players.remove(randomIndex);
            }
        }

        if(players.size() == 1) {
            System.out.println(players.get(0).getName() + " won!");
            app.gsm.setScreen(ScreenManager.STATE.SINGLE);

        }
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
        players.clear();
        stage.clear();

        app.gsm.setAllowBackNavigation(true);
    }

    @Override
    public void dispose() {
        // Dispose existing stage and skin
        stage.dispose();
        skin.dispose();
    }

}