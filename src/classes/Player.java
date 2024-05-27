package classes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class Player extends Sprite {

    private int health;
    private Perk perk;
    public String name;
    private Boolean isDead;
    private final static Image HEAD = new Image("assets/player-head.png");
    private ArrayList<Health> healthBar;

    private final static int WIDTH = 90;
    private final static int HEIGHT = 90;
    private final static int INITIAL_X = 0;
    private final static int INITIAL_Y = 0;

    public Player(String name) {
        super(INITIAL_X, INITIAL_Y, WIDTH, HEIGHT, HEAD);
        this.name = name;
    }

    public void reducePlayerHealth() {
        this.health -= 1;
        if (this.health <= 0) {
            this.isDead = true;
        }
    }

    public void renderName(GraphicsContext gc) {
        gc.setFont(Font.font("Poppins Bold", 40));
        gc.setTextAlign(TextAlignment.CENTER);
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

    public void setHealth (int health) {
        this.health = health;
    }

    public void setStatus (Boolean isDead) {
        this.isDead = isDead;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public int answer(Bomb bomb, String answer) {
//        System.out.println("Player " + name + " answered: " + answer);
        if (bomb.checkAnswer(answer)) {
//            System.out.println("Correct answer");
            return 1;
        } else {
//            System.out.println("Incorrect answer, try again");
            return -1;
        }
    }

    public Perk getPerk(){ return this.perk;}

    public void setPerk(Perk perk) {this.perk = perk;}
}
