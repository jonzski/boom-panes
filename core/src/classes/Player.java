package classes;

import classes.Health;
import classes.Bomb;

public class Player {
    private final Health health;
    private String name;
    private boolean status;

    public Player(int health, String name, boolean status) {
        this.health = new Health(health);
        this.name = name;
        this.status = status;
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
}
