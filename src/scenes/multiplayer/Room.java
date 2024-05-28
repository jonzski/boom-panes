package scenes.multiplayer;

import classes.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Room extends AnimationTimer {

    private GraphicsContext gc;
    private Scene scene;
    private Group root;
    private Canvas canvas;
    private TextField answerField;

    private int health;
    private int duration;
    private int currentPlayerIndex = 0;

    private Player player;
    private Bomb bomb;

    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;
    private boolean isRunning = true;

    private final GameTimer timer;
    private final GameTimer waitTimer;

    private final ArrayList<Player> players = new ArrayList<>();
    private final Image background = new Image("assets/Backgrounds/battleroom-bg.png");

    private GameClient client;

    public Room(int playerCount, int duration, int health) throws IOException {
        this.canvas = new Canvas(Room.WINDOW_WIDTH, Room.WINDOW_HEIGHT);
        this.gc = this.canvas.getGraphicsContext2D();
        this.root = new Group();
        this.answerField = new TextField();
        this.scene = new Scene(root, Room.WINDOW_WIDTH, Room.WINDOW_HEIGHT);
        this.root.getChildren().add(this.canvas);
        this.root.getChildren().add(this.answerField);
        this.timer = new GameTimer();
        this.waitTimer = new GameTimer();

        this.duration = duration;
        this.health = health;

        this.initializePlayer();
        this.placePlayersInCircle(playerCount);
        this.initializeBomb();
        this.initializeLobbyPlayers(playerCount);

        this.client = new GameClient("localhost", 1234, this);
        this.client.start();

        new Thread(() -> {
            while (true) {
                try {
                    Player player = (Player) client.in.readObject();
                    updatePlayer(player);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initializeLobbyPlayers(int playerCount) {
        this.players.add(this.player);

        for (int i = 1; i < playerCount; i++) {
            Player placeholder = new Player("Player " + i);
            placeholder.setHealth(this.health);
            this.players.add(placeholder);
        }
    }

    public void updatePlayer(Player updatedPlayer) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(updatedPlayer.getName())) {
                players.set(i, updatedPlayer);
                break;
            }
        }
    }

    private void initializePlayer() {
        this.player = new Player("Player");
        this.player.setHealth(this.health);
        this.player.setStatus(false);
    }

    public void initializeBomb() {
        this.bomb = new Bomb(false, this.duration);
    }

    private void placePlayersInCircle(int playerCount) {
        double centerX = WINDOW_WIDTH / 2;
        double centerY = WINDOW_HEIGHT / 2.5;
        double radius = 240;
        Random random = new Random();
        double angle = 0 + random.nextInt(360);

        for (int i = 0; i < playerCount; i++) {
            double x = centerX + radius * Math.cos(Math.toRadians(angle));
            double y = centerY + radius * Math.sin(Math.toRadians(angle));

            if (i == 0) {
                this.player.setPosition(x, y);
            } else {
                this.players.get(i).setPosition(x, y);
            }

            angle += 360 / playerCount;
        }
    }

    @Override
    public void handle(long now) {
        initBackground();
        renderSprite();

        timer.start();
        waitTimer.start();

        if (isRunning) runGame();
    }

    private void renderPlayers() {
        for (Player player : this.players) {
            player.render(gc);
            player.renderHealthBar(gc);
            player.renderName(gc);
        }
    }

    private void renderSprite() {
        renderPlayers();
        renderBomb();
    }

    private void renderBomb() {
        bomb.setPosition(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2.5);
        bomb.render(gc);
        bomb.renderHint(gc);
    }

    public void initBackground() {
        gc.drawImage(background, 0, 0);
    }

    private void endGame() {
        isRunning = false;
    }

    private void runGame() {
        currentPlayerIndex = currentPlayerIndex % players.size();
        String bufferPlayerAnswer = answerField.getText();

        if (timer.getElapsedTimeSeconds() > duration) {
            timer.reset();
            players.get(currentPlayerIndex).reducePlayerHealth();
            if (players.get(currentPlayerIndex).isDead()) {
                players.remove(currentPlayerIndex);
                if (players.size() == 1) endGame();
            }
            else {
                currentPlayerIndex++;
            }
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);
        waitTimer.reset();

        answerField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String playerAnswer = bufferPlayerAnswer;
                int result = currentPlayer.answer(bomb, playerAnswer);

                if (result == 1) {
                    timer.reset();
                    currentPlayerIndex++;
                }

                try {
                    client.send(currentPlayer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                answerField.clear();
            }
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}