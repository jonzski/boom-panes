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

import java.util.Scanner;

public class SinglePlayer extends GameScreen {
    private final Skin skin;
    private final Background backgroundComponent = new Background(stage);
    private final CaptionBox captionBox = new CaptionBox(stage);
    private int numberOfPlayers;
    private int health;
    private int coolDown;
    private int difficulty;
    private float gameTimer = 0;

    public SinglePlayer(final App app) {
        super(app);
        skin = new Skin();
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        skin.add("default", buttonStyle);
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void inputLobby() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        numberOfPlayers = scanner.nextInt();
        System.out.println("Enter health: ");
        health = scanner.nextInt();
        System.out.println("Enter cool down: ");
        coolDown = scanner.nextInt();
        System.out.println("Enter difficulty: ");
        difficulty = scanner.nextInt();

        setHealth(health);
        setCoolDown(coolDown);
        setNumberOfPlayers(numberOfPlayers);
    }


    public void addAnActor(String pathname, int width, int height, int x, int y) {
        stage.addActor(new Image(new Texture(pathname)) {{
            setSize(width, height);
            setPosition(x, y);
        }});
    }

    @Override
    public void update(float delta) {
        // Update logic here if needed
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

        // Set button positions
        quitButton.setPosition(50, 25);
        startButton.setPosition(1150, 25);
        waitButton.setPosition(500, 25);
        roomName.setPosition(50, 610);

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
                inputLobby();
                app.gsm.setSinglePlayer(ScreenManager.STATE.BATTLE, numberOfPlayers, health, coolDown, difficulty);
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
