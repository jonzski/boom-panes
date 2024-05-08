package classes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Player extends Sprite {

    private int health;
    private String name;
    private Boolean isDead;
    private final static Image HEAD = new Image("assets/player-head.png");
    private ArrayList<Health> healthBar;
    private int scaling;

    public Player(int health, String name, Boolean isDead, double xPos, double yPos, double width, double height) {
        super(xPos, yPos, width, height, HEAD);
        this.health = health;
        this.name = name;
        this.isDead = isDead;
    }

    public void reducePlayerHealth() {
        this.health -= 1;
        if (this.health <= 0) {
            this.isDead = true;
        }
    }

    public void renderName(GraphicsContext gc) {
        gc.setFont(Font.font("Poppins Bold", 40));
        gc.fillText(this.name, xPos, yPos + height + 30);
        gc.setFill(Color.WHITE);
    }

    public void renderHealthBar(GraphicsContext gc) {
        this.healthBar = new ArrayList<>();
        for (int i = 0; i < this.health; i++) {
            this.healthBar.add(new Health(xPos - 37 + (i * width / 1.5), yPos + height * 1.5, 44, 37));
        }
        for (Health health : this.healthBar) {
            health.render(gc);
        }
    }

    public void setScaling(int scaling) {
        this.scaling = scaling;
    }

    public String getName() {
        return this.name;
    }
}
