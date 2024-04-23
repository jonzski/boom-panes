package classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private boolean status;
    private final Image playerImage;
    private float x;
    private float y;
    private BitmapFont font;
    private int healthValue;
    private final List<Health> health = new ArrayList<>();

    public Player(int healthValue, String name, boolean status) {
        this.name = name;
        this.status = status;
        this.playerImage = new Image(new Texture("player-head.png"));
        this.font = new BitmapFont();
        this.healthValue = healthValue;

        float heartWidth = 24; // Width of each heart image
        float gapBetweenHearts = 10;

        // Calculate the starting position for hearts
        float startX = playerImage.getX() + (playerImage.getWidth() - (healthValue * heartWidth + (healthValue - 1) * gapBetweenHearts)) / 2;

        // Initialize hearts with adjusted positions
        for (int i = 0; i < healthValue; i++) {
            Health heart = new Health(startX + i * (heartWidth + gapBetweenHearts), playerImage.getY() - playerImage.getHeight() - 10);
            health.add(heart);
        }
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
        this.healthValue -= 1;
        if (this.healthValue < 0) {
            this.healthValue = 0; // Ensure health doesn't go below zero
        }
        if (this.healthValue == 0) {
            this.status = true;
        }
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

    public Image getPlayerImage() {
        return playerImage;
    }

    // Get the health image based on current health value
    public List<Health> getHearts() {
        return health;
    }
    public void updateHealthImagePosition() {
        float heartWidth = 24;
        float gapBetweenHearts = 10;

        float totalWidth = health.size() * heartWidth + (health.size() - 1) * gapBetweenHearts;

        float startX = playerImage.getX() + (playerImage.getWidth() - totalWidth) / 2;

        for (int i = 0; i < health.size(); i++) {
            Health currentHealth = health.get(i);
            float heartX = startX + i * (heartWidth + gapBetweenHearts);
            float heartY = playerImage.getY() - playerImage.getHeight() - 10;
            currentHealth.setPosition(heartX, heartY);
        }
    }


}
