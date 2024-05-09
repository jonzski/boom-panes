package scenes;

import classes.Bomb;
import classes.Bot;
import classes.GameTimer;
import classes.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SingleRoom extends AnimationTimer {

    private GraphicsContext gc;
    private Scene scene;
    private Group root;
    private Canvas canvas;

    private int difficulty = 1;
    private int health = 3;
    private int duration = 3;
    private int numBots = 3;

    private Player player;
    private Bomb bomb;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;
    private boolean isRunning = true;

    private TextField answerField = new TextField();

    private GameTimer timer;
    private GameTimer waitTimer;

    private ArrayList<Bot> bots;
    private Image background = new Image("assets/Backgrounds/battleroom-bg.png");

    public SingleRoom() throws IOException {
        this.canvas = new Canvas(SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.gc = this.canvas.getGraphicsContext2D();
        this.root = new Group();
        this.scene = new Scene(root, SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.root.getChildren().add(this.canvas);
        this.root.getChildren().add(answerField);
        this.timer = new GameTimer();
        this.waitTimer = new GameTimer();
        this.initializeBots();
        this.initializePlayer();
        this.placePlayerInCircle();
        this.initializeBomb();
    }

    private void initializePlayer() {
        this.player = new Player(this.health, "Player", false);
    }

    public void initializeBots() {
        this.bots = new ArrayList<>();
        for (int i = 0; i < this.numBots; i++) {
            int scaling = (int) ((double) i / (this.numBots - 1) * 4); // calculate scaling value
            this.bots.add(new Bot(this.health, "Bot " + i, false, this.difficulty));
        }
    }

    public void initializeBomb() {
        this.bomb = new Bomb(false, this.duration);
    }

    private void placePlayerInCircle() {
        double centerX = WINDOW_WIDTH / 2;
        double centerY = WINDOW_HEIGHT / 2.5;
        double radius = 240;
        Random random = new Random();
        double angle = 0 + random.nextInt(360);

        for (int i = 0; i <= this.numBots; i++) {
            double x;
            double y;
            double x1 = centerX + radius * Math.cos(Math.toRadians(angle));
            if (i < this.numBots) {
                x = x1;
                y = centerY + radius * Math.sin(Math.toRadians(angle));
                this.bots.get(i).setPosition(x, y);
            } else {
                x = x1;
                y = centerY + radius * Math.sin(Math.toRadians(angle));
                this.player.setPosition(x, y); // Set the player's position
            }
            angle += 360 / (this.numBots + 1);
        }
    }

    @Override
    public void handle(long now) {
        initBackground();
        renderSprite();
    }

    private void renderBots() {
        for (Bot bot : this.bots) {
            bot.render(gc);
            bot.renderHealthBar(gc);
            bot.renderName(gc);
        }
    }

    private void renderPlayer() {
        this.player.render(gc);
        this.player.renderHealthBar(gc);
        this.player.renderName(gc);
    }

    private void renderSprite() {
        renderBots();
        renderBomb();
        renderPlayer();
    }

    private void renderBomb() {
        bomb.setPosition(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2.5);
        bomb.render(gc);
        bomb.renderHint(gc);
    }

    private void updateBombCooldown() {
        if (bomb != null && !bomb.isExploded()) {
            bomb.updateCooldownAndExplode();
        }
    }

    public void initBackground() {
        gc.drawImage(background, 0, 0);
    }

    private void endGame() {
        isRunning = false;
    }


    private void playerTurn() {
        String answer = answerField.getText();
        boolean result = player.answer(bomb, answer);
    }

    private void botsTurn() {
        for (Bot bot : bots) {
            boolean result = false;
            timer.reset();
            waitTimer.reset();
            timer.start();
            waitTimer.start();
            System.out.println(bot.getName() + " has lives: " + bot.getHealth());

            if (!result) {
                bot.reducePlayerHealth();
            }

            if (bot.isDead()) {
                System.out.println(bot.getName() + " is dead!");
                bot.setImageDead();
            }

            if (bots.isEmpty()) {
                endGame();
            }
        }
    }

    public Scene getScene() {
        return this.scene;
    }

}
