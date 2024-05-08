package classes;

import javafx.scene.image.Image;

public class Health extends Sprite {

    private final static Image HEALTH = new Image("assets/health.png");

    public Health(double xPos, double yPos, double width, double height) {
        super(xPos, yPos, width, height, HEALTH);
    }
}
