package components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Background {
    private final Stage stage;

    public Background(Stage stage) {
        this.stage = stage;
    }

    public void addBackgroundImage(String imagePath, float width, float height) {
        Image backgroundImage = new Image(new Texture(imagePath));
        backgroundImage.setSize(width, height);
        stage.addActor(backgroundImage);
    }

    public void addImage(String imagePath, float width, float height, float x, float y) {
        Image image = new Image(new Texture(imagePath));
        image.setSize(width, height);
        image.setPosition(x, y);
        stage.addActor(image);
    }
}
