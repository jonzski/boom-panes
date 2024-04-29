package classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private boolean isDead;
    private final Image playerImage;
    private float x;
    private float y;
    private BitmapFont font;
    private int healthValue;
    private final List<Image> health = new ArrayList<>();
    private Label nameLabel;

    public Player(int healthValue, String name, boolean isDead) {
        this.name = name;
        this.isDead = isDead;
        this.playerImage = new Image(new Texture("player-head.png"));
        this.font = new BitmapFont();
        this.healthValue = healthValue;

        for (int i = 0; i < healthValue; i++) {
            Image heart = new Image(new Texture("health.png"));
            heart.setPosition(x, y);
            heart.setScale(0.8f);
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

    public void reducePlayerHealth() {
        this.healthValue -= 1;
        Image lastHealth = health.remove(health.size() - 1);
        lastHealth.remove();

        if (this.healthValue < 0) {
            this.healthValue = 0;
        }
        if (this.healthValue == 0) {
            setDead();
        }
    }

    public String getName() {
        return name;
    }

    private void setDead() {
        this.isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public List<Image> getHearts() {
        placeHeart();
        return health;
    }

    public void placeHeart() {
        float totalWidthOfHearts = health.size() * (health.get(0).getWidth());
        float startX = playerImage.getX() + (playerImage.getWidth() - totalWidthOfHearts) / 2;
        float heartY = playerImage.getY() - playerImage.getHeight();

        for (int i = 0; i < health.size(); i++) {
            Image currentHealth = health.get(i);
            float heartX = startX + i * (currentHealth.getWidth() + 10);
            currentHealth.setPosition(heartX, heartY);
        }
    }

    public void placeLabel(){
        nameLabel.setPosition(playerImage.getX() + (playerImage.getWidth() - nameLabel.getWidth()) / 2,
                playerImage.getY() - (playerImage.getHeight() / 2) );
    }

    public Image[] getHealth() {
        return health.toArray(new Image[0]);
    }
}