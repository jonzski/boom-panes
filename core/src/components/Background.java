package components;

import com.badlogic.gdx.graphics.Texture;
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
}
