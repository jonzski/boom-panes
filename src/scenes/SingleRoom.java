package scenes;

import classes.Bomb;
import classes.Bot;
import classes.GameTimer;
import classes.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SingleRoom extends AnimationTimer {

    private Stage stage;
    private GraphicsContext gc;
    private Scene scene;
    private Group root;
    private Canvas canvas;

    private final int difficulty = 1;
    private final int health = 3;
    private final int duration = 3;
    private final int numBots = 3;
    private final int botsThinkingTime = 1;

    private int currentPlayerIndex = 0;
    private int playerIndex = 0;

    private Player player;
    private Bomb bomb;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;
    private boolean isRunning = false;

    private TextField answerField;

    private GameTimer timer;
    private GameTimer waitTimer;

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Bot> bots;
    private Image background = new Image("assets/Backgrounds/battleroom-bg.png");

    public SingleRoom() throws IOException {
        this.canvas = new Canvas(SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.gc = this.canvas.getGraphicsContext2D();
        this.root = new Group();
        this.scene = new Scene(root, SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.root.getChildren().add(this.canvas);
        this.timer = new GameTimer();
        this.waitTimer = new GameTimer();

        this.initializeBots();
        this.initializePlayer();
        this.placePlayerInCircle();
        this.initializeBomb();

        this.initializeLobbyPlayers();
    }

    private void initializeLobbyPlayers(){
        this.players.add(this.player);
        this.players.addAll(this.bots);
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

        // initialize timers
        timer.start();
        waitTimer.start();
//        System.out.println(currentPlayerIndex);
        // let current player answer his turn
        if(isRunning) simulateAnswer();
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

    private void endGame() throws IOException {
        isRunning = false;
        timer.reset();
        waitTimer.reset();
        switchToVictoryScreen();
    }

    private void simulateAnswer() throws IOException {
        // initialize result
        int result = 0;

        // adjust player index
        currentPlayerIndex = currentPlayerIndex % players.size();

        if (currentPlayerIndex == this.playerIndex && !this.player.isDead()) {
//             player's turn
            Player currentPlayer = this.players.get(currentPlayerIndex);
            // to catch when bot is in index 0 since player died;

            Scanner scanner = new Scanner(System.in);
            String playerAnswer = scanner.nextLine();
//            scanner.close();
            System.out.println(result);

            result = currentPlayer.answer(bomb, playerAnswer);
            System.out.println("isCorrect: " + result);

            if (result == 1) {
                // reset timers
                timer.reset();
                waitTimer.reset();
                currentPlayerIndex++;
                return;
            }

            else if (result == -1) {
                timer.reset();
                waitTimer.reset();
                currentPlayer.reducePlayerHealth();
                if(currentPlayer.isDead()) {
                    this.players.remove(currentPlayer);
                    return;
                }
                currentPlayerIndex++;
                return;
            }

        }

        Bot currentBot = (Bot)players.get(currentPlayerIndex);

        // can still answer
        if(timer.getElapsedTimeSeconds() <= duration){
            // need to wait for thinking time
            if (waitTimer.getElapsedTimeSeconds() > botsThinkingTime) {
                String answer = currentBot.simulateAnswer(bomb);
                result = currentBot.answer(bomb, answer);
                // reset wait timer since bot already answered
                waitTimer.reset();

                // answer is correct
                if (result == 1){
                    timer.reset();
                    currentPlayerIndex++;
                }
            }
        }
        else {
            System.out.println("Time is up!\n\n\n");
            // means that the duration has passed, can only mean that the player did not give correct answer in time
            // lose a life and check if it died
            currentBot.reducePlayerHealth();
            if (currentBot.isDead()) {
                System.out.println(currentBot.getName() + " is dead!");
                currentBot.setImageDead();
                players.remove(currentBot);
            }
            else { // if no one died, go to next index to go to next player
                currentPlayerIndex++;
            }
            if (players.size() == 1) {
                endGame();

            }
            timer.reset();
        }
    }

    public Scene getScene() {
        return this.scene;
    }

}
