package scenes.singleplayer;

import classes.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import scenes.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Room extends AnimationTimer {

    private GraphicsContext gc;
    private Scene scene;
    private Group root;
    private Canvas canvas;
    private TextField answerField;

    private int difficulty;
    private int health;
    private int duration;
    private int numBots;
    private Perk roundPerk;
    private boolean doubleDuration = false;
    private double botsThinkingTime;

    private int currentPlayerIndex = 0;
    private final int playerIndex = 0;

    private Player player;
    private Bomb bomb;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;
    private boolean isRunning = true;

    private final GameTimer timer;
    private final GameTimer waitTimer;

    private final ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Bot> bots;
    private final Image background = new Image("assets/Backgrounds/pxsprite-space.gif");
    private String playerAnswer;

    private Label perkLabel;
    private Label currentAnswer;

    public Room(int playerCount, int difficulty, int duration, int health) throws IOException {

        this.canvas = new Canvas(Room.WINDOW_WIDTH, Room.WINDOW_HEIGHT);
        this.canvas = new Canvas(Room.WINDOW_WIDTH, Room.WINDOW_HEIGHT);
        this.gc = this.canvas.getGraphicsContext2D();
        this.root = new Group();

        this.answerField = new TextField();
        this.perkLabel = new Label();
        this.currentAnswer = new Label();

        this.perkLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Arial'; -fx-font-size: 20px;");
        this.perkLabel.setText("Perk: None");

        this.currentAnswer.setLayoutX(50);
        this.currentAnswer.setLayoutY(WINDOW_HEIGHT/2);
        this.currentAnswer.setStyle("-fx-text-fill: white; -fx-font-family: 'Arial'; -fx-font-size: 20px;");

        this.scene = new Scene(root, Room.WINDOW_WIDTH, Room.WINDOW_HEIGHT);
        this.root.getChildren().add(this.canvas);

        this.root.getChildren().add(this.answerField);
        this.root.getChildren().add(this.perkLabel);
        this.root.getChildren().add(this.currentAnswer);

        this.answerField.setLayoutY(WINDOW_HEIGHT-50);
        this.answerField.setLayoutX(50);

        this.timer = new GameTimer();
        this.waitTimer = new GameTimer();

        // get parameters
        this.numBots = playerCount-1;
        this.duration = duration;
        this.health = health;
        this.difficulty = difficulty;
        this.botsThinkingTime = ((double) duration /3) - .1;

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

    public void randomizeRoundPerk(){
        System.out.println("Randomizing perk...");
        Random random = new Random();
        int percentage = random.nextInt(101);
        if (percentage > 98) {
            this.roundPerk = new Perk("bomb-them-all");
            this.perkLabel.setText("Perk: bomb-them-all");
        }
        else if (percentage > 95) {
            this.roundPerk =  new Perk("defused");
            this.perkLabel.setText("Perk: defused!");
        }
        else if(percentage > 90) {
            this.roundPerk =  new Perk("slow-motion");
            this.perkLabel.setText("Perk: slow-motion");
        }
        else {
            this.roundPerk = null;
            this.perkLabel.setText("Perk:None");
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
//        SHOULD BE FIRST TO FIX INDEXING
        currentPlayerIndex = currentPlayerIndex % players.size();

        if (players.get(currentPlayerIndex) instanceof Bot) {
            answerField.setVisible(false);
        } else {
            answerField.setVisible(true);
        }

        if(timer.getElapsedTimeSeconds() < 0.18) System.out.println(players.get(currentPlayerIndex).getName() +"s turn!");
        if (players.get(currentPlayerIndex).getPerk() != null)
            System.out.println(players.get(currentPlayerIndex).getName() + " has a perk named: " + players.get(currentPlayerIndex).getPerk().name);
        String bufferPlayerAnswer = answerField.getText();
        // if bots turn and is thinking, do nothing
        if (players.get(currentPlayerIndex) instanceof Bot && waitTimer.getElapsedTimeSeconds() < botsThinkingTime) {
            return;
        }

        // if duration has elapsed, proceed to next player;
        if (timer.getElapsedTimeSeconds() > duration) {
            if (doubleDuration) {
                duration /= 2;
                doubleDuration = false;
            }

            timer.reset();
            players.get(currentPlayerIndex).reducePlayerHealth();
            if (players.get(currentPlayerIndex).isDead()) {
                System.out.println(players.get(currentPlayerIndex).getName() + " is dead!");
//                players.get(currentPlayerIndex).setImageDead();
                players.remove(players.get(currentPlayerIndex));
                if (players.size() == 1) endGame();
            } else {
                currentPlayerIndex++;
            }
            // start of next round
            System.out.println("Next round start...");
            randomizeRoundPerk();
            System.out.println(this.roundPerk == null ? "No perks this round" : "Perk: " + this.roundPerk.name);
            return;
        }

        // this part of code means: has not elapsed duration and bots are able to answer
        // initialize result
        int result = 0;

        // if currentPlayer is a Bot
        if (players.get(currentPlayerIndex) instanceof Bot currentBot) {
            String answer = currentBot.simulateAnswer(bomb);
            this.currentAnswer.setText(answer);
            result = currentBot.answer(bomb, answer);
            // reset wait timer since bot already answered
            waitTimer.reset();

        }
        // if currentPlayer is a player
        else {
            Player currentPlayer = players.get(currentPlayerIndex);
            waitTimer.reset();

            answerField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    this.playerAnswer = bufferPlayerAnswer;
                    this.currentAnswer.setText(bufferPlayerAnswer);
                }

                else if (event.getCode() == KeyCode.TAB) {
                    Perk currentPerk = currentPlayer.getPerk();
                    System.out.println(currentPlayer.getName() + " used: " + currentPerk.name + "!\n");
                    currentPlayer.setPerk(null);
                    switch (currentPerk.name) {
                        case "bomb-them-all":
                            for (Player p : players) {
                                if (p != currentPlayer) p.reducePlayerHealth();
                            }
                            players.removeIf(Player::isDead);
                            if (players.size() == 1) endGame();
                            randomizeRoundPerk();
                            currentPlayerIndex++;
                            break;
                        case "defused":
                            waitTimer.reset();
                            timer.reset();
                            randomizeRoundPerk();
                            currentPlayerIndex++;
                            break;
                        case "slow-motion":
                            doubleDuration = true;
                            duration *= 2;
                            break;
                        default:
                            break;
                    }
                }
            });

            result = currentPlayer.answer(bomb, playerAnswer);
        }

        if (result == 1) {
            if (this.roundPerk != null) players.get(currentPlayerIndex).setPerk(this.roundPerk);
            timer.reset();
            System.out.println("Next round start...");
            randomizeRoundPerk();
            System.out.println(this.roundPerk == null ? "No perks this round" : "Perk: " + this.roundPerk.name);
            currentPlayerIndex++;
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}