package classes;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
    protected Image img;
    protected double xPos, yPos, dx, dy;
    protected boolean eaten;
    protected double width;
    protected double height;

    public Sprite(double xPos, double yPos, double width, double height, Image image) {
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.loadImage(image);
    }

    protected void loadImage(Image image) {
        try {
            this.img = image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.img, this.xPos, this.yPos, this.width, this.height);
    }

    public void setPosition(double newXPos, double newYPos) {
        this.xPos = newXPos;
        this.yPos = newYPos;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Image getImage() {
        return this.img;
    }

}
