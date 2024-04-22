package classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player {
    private final Health health;
    private String name;
    private boolean status;
    private final Image playerImage;
    private float x; // Player's x-coordinate
    private float y; // Player's y-coordinate

    public Player(int health, String name, boolean status) {
        this.health = new Health(health);
        this.name = name;
        this.status = status;
        this.playerImage = new Image(new Texture("player-head.png"));
        this.playerImage.setPosition(x, y); // Set initial position
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.playerImage.setPosition(x, y); // Update position
    }

    public void playerAnswer(Bomb bomb, String answer) {
        bomb.setAnsweredWord(answer);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void reducePlayerHealth() {
        health.reduceHealth();
    }

    public String getName() {
        return name;
    }

    public void setDead() {
        this.status = true;
    }

    public boolean getStatus() {
        return status;
    }

    // Add getters for x and y coordinates
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    // Add a method to get the playerImage
    public Image getPlayerImage() {
        return playerImage;
    }
}
