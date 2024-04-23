package classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

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
    private Label nameLabel;

    public Player(int healthValue, String name, boolean status) {
        this.name = name;
        this.status = status;
        this.playerImage = new Image(new Texture("player-head.png"));
        this.playerImage.setPosition(x, y); // Set initial position
        this.font = new BitmapFont();
        this.healthValue = healthValue;

        this.playerImage.setPosition(x, y);
        for (int i = 0; i < healthValue; i++) {
            Health heart = new Health(x, y);
            health.add(heart);
        }

        BitmapFont newFont = new BitmapFont();
        newFont.getData().setScale(1.5f); // Increase font size by 1.5 times, you can adjust the value as needed
        Label.LabelStyle labelStyle = new Label.LabelStyle(newFont, font.getColor());

        this.nameLabel = new Label(name, labelStyle); // Assign the modified LabelStyle to the label
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.playerImage.setPosition(x, y);
    }

    public void playerAnswer(Bomb bomb, String answer) {
        bomb.setAnsweredWord(answer);
    }

    public Label getNameLabel() {
        return nameLabel;
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

    public List<Health> getHearts() {
        placeHeart();
        return health;
    }


    public void placeHeart() {
        float totalWidthOfHearts = health.size() * (health.get(0).getWidth());
        float startX = playerImage.getX() + (playerImage.getWidth() - totalWidthOfHearts) / 2;
        float heartY = playerImage.getY() - playerImage.getHeight();

        for (int i = 0; i < health.size(); i++) {
            Health currentHealth = health.get(i);
            float heartX = startX + i * (currentHealth.getWidth() + 10);
            currentHealth.setPosition(heartX, heartY);
        }
    }

    public void placeLabel(){
        nameLabel.setPosition(playerImage.getX() + (playerImage.getWidth() - nameLabel.getWidth()) / 2,
                playerImage.getY() - (playerImage.getHeight() / 2) );
    }

}
