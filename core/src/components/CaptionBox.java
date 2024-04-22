package components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class CaptionBox {
    private final Stage stage;
    private final Skin skin = new Skin();

    public CaptionBox(Stage stage) {
        this.stage = stage;
        TextButton.TextButtonStyle captionText = new TextButton.TextButtonStyle();
        captionText.font = new BitmapFont();
        captionText.font.getData().setScale(1.5f);
        captionText.fontColor = Color.WHITE;

        skin.add("default", captionText);
    }

    public void addCaptionBox(String imagePath, float width, float height, float x, float y) {
        Image captionBox = new Image(new Texture(imagePath));
        captionBox.setSize(width, height);
        captionBox.setPosition(x, y);
        stage.addActor(captionBox);
    }

    public void insertCaptionBox(String caption, float x, float y) {
        TextButton captionBox = new TextButton(caption, skin);
        captionBox.setPosition(x, y);
        stage.addActor(captionBox);
    }


}