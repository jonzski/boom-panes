package classes;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Health {
    private int health;
    private Image healthImg;
    public static int MAXHEALTH = 5;

    public Health(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getHealthImg() {
        return healthImg;
    }

    public void reduceHealth() {
        health -= 1;
    }

}
