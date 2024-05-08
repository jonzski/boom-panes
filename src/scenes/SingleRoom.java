package scenes;

import classes.Bomb;
import classes.Bot;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import classes.Player;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class SingleRoom extends AnimationTimer {

    private GraphicsContext gc;
    private Scene scene;
    private Group root;
    private Canvas canvas;

    private int difficulty = 1;
    private int health = 3;
    private int duration = 3;
    private int numBots = 4;
    private Boolean isMultiplayer = false;

    private Player player;
    private Bomb bomb;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;
    private Timer timer;
    private boolean isRunning = true;

    private ArrayList<Bot> bots;
    private Image background = new Image("assets/Backgrounds/battleroom-bg.png");

    public SingleRoom() throws IOException {
        this.canvas = new Canvas(SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.gc = this.canvas.getGraphicsContext2D();
        this.root = new Group();
        this.timer = new Timer();
        this.scene = new Scene(root, SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.root.getChildren().add(this.canvas);
        this.initializeBots();
        this.initializeBomb();
    }

    public void initializeBots() {
        this.bots = new ArrayList<>();
        for (int i = 0; i < this.numBots; i++) {
            int scaling = (int) ((double) i / (this.numBots - 1) * 4); // calculate scaling value
            this.bots.add(new Bot(this.health, "Bot " + i, false, this.difficulty, 0, 0, 90, 90));
            this.bots.get(i).setScaling(scaling);
        }
    }

    public void initializeBomb() {
        this.bomb = new Bomb(false, this.duration, 50, 50, 128, 128);
    }
    private void placePlayerInCircle() {
        double centerX = WINDOW_WIDTH / 3.5;
        double centerY = WINDOW_HEIGHT / 2.5;
        double radius = 200;
        double angle = 0;
        double angleIncrement = 360 / this.numBots;
        for (Bot bot : this.bots) {
            double x = centerX + radius * Math.cos(Math.toRadians(angle));
            double y = centerY + radius * Math.sin(Math.toRadians(angle));
            bot.setPosition(x, y);
            angle += angleIncrement;
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

    private void renderSprite() {
        placePlayerInCircle();
        renderBots();
        renderBomb();
        updateBombCooldown();
        simulateGame();
    }

    private void renderBomb() {
        bomb.setPosition(WINDOW_WIDTH / 3.5, WINDOW_HEIGHT / 2.5);
        bomb.render(gc);
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

    private void simulateGame() {
        simulateBattle();
        if (bots.size() == 1) {
            System.out.println(bots.get(0).getName() + " wins!");
            endGame();
        }
    }

    private void simulateBattle() {
        for (Bot bot : bots) {
            if (bomb.isExploded()) {
                String answer = bot.simulateAnswer(bomb);
                if (answer.equals("x")) {
                    bot.reducePlayerHealth();
                } else {
                    if (bomb.checkAnswer(answer)) {
                        bot.reducePlayerHealth();
                    }
                }
            }
        }
    }


    public Scene getScene() {
        return this.scene;
    }

}
