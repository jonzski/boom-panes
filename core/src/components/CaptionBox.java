package components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CaptionBox {
    private final Stage stage;

    public CaptionBox(Stage stage) {
        this.stage = stage;
    }

    public void addCaptionBox(String imagePath, float width, float height, float x, float y) {
        Image captionBox = new Image(new Texture(imagePath));
        captionBox.setSize(width, height);
        captionBox.setPosition(x, y);
        stage.addActor(captionBox);
    }
}