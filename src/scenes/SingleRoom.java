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
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SingleRoom extends AnimationTimer {

    private GraphicsContext gc;
    private Scene scene;
    private Group root;
    private Canvas canvas;
    private TextField answerField;

    private final int difficulty = 1;
    private final int health = 3;
    private final int duration = 10;
    private final int numBots = 3;
    private final int botsThinkingTime = 3;

    private int currentPlayerIndex = 0;
    private int playerIndex = 0;

    private Player player;
    private Bomb bomb;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;
    private boolean isRunning = true;

    private GameTimer timer;
    private GameTimer waitTimer;

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Bot> bots;
    private Image background = new Image("assets/Backgrounds/battleroom-bg.png");
    private String playerAnswer;

    public SingleRoom() throws IOException {
        this.canvas = new Canvas(SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.gc = this.canvas.getGraphicsContext2D();
        this.root = new Group();
        this.answerField = new TextField();
        this.scene = new Scene(root, SingleRoom.WINDOW_WIDTH, SingleRoom.WINDOW_HEIGHT);
        this.root.getChildren().add(this.canvas);
        this.root.getChildren().add(this.answerField);
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
        this.player = new Player("Player");
        this.player.setHealth(this.health);
        this.player.setStatus(false);
    }

    public void initializeBots() {
        this.bots = new ArrayList<>();
        for (int i = 0; i < this.numBots; i++) {
            this.bots.add(new Bot("Bot " + i, this.difficulty));
            this.bots.get(i).setHealth(this.health);
            this.bots.get(i).setStatus(false);
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

    private void endGame() {
        isRunning = false;
    }

    private void simulateAnswer() {
        currentPlayerIndex = currentPlayerIndex % players.size();
        String bufferPlayerAnswer = answerField.getText();
        // if bots turn and is thinking, do nothing
        if(players.get(currentPlayerIndex) instanceof Bot && waitTimer.getElapsedTimeSeconds() < botsThinkingTime){
            return;
        }

        // if duration has elapsed, proceed to next player;
        if(timer.getElapsedTimeSeconds() > duration){
            timer.reset();
            players.get(currentPlayerIndex).reducePlayerHealth();
            if (players.get(currentPlayerIndex).isDead()) {
                System.out.println(players.get(currentPlayerIndex).getName() + " is dead!");
//                players.get(currentPlayerIndex).setImageDead();
                players.remove(players.get(currentPlayerIndex));
                if (players.size() == 1) endGame();
            }
            else {
                currentPlayerIndex++;
            }
            return;
        }

        // this part of code means: has not elapsed duration and bots are able to answer
        // initialize result
        int result = 0;

        // if currentPlayer is a Bot
        if(players.get(currentPlayerIndex) instanceof Bot currentBot){
            String answer = currentBot.simulateAnswer(bomb);
            result = currentBot.answer(bomb, answer);
            // reset wait timer since bot already answered
            waitTimer.reset();

            // answer is correct
        }
        // if currentPlayer is a player
        else {
            Player currentPlayer = players.get(currentPlayerIndex);
            waitTimer.reset();

            answerField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    this.playerAnswer = bufferPlayerAnswer;
                }
            });

            result = currentPlayer.answer(bomb, playerAnswer);
        }

        if (result == 1){
            timer.reset();
            currentPlayerIndex++;
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}