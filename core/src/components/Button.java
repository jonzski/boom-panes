package components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button {
    private final Stage stage;
    private final Skin skin = new Skin();

    public enum BUTTON_TYPE {
        EXIT,
        BATTLE,
        SETTING,
        LOBBY,
        MAIN_MENU
    }


    public Button(Stage stage) {
        this.stage = stage;
        TextButton.TextButtonStyle captionText = new TextButton.TextButtonStyle();
        captionText.font = new BitmapFont();
        captionText.fontColor = Color.WHITE;

        skin.add("default", captionText);
    }

    public void placeButton(String buttonText, float x, float y, ClickListener listener) {
        TextButton textButton = new TextButton(buttonText, skin);
        textButton.setPosition(x, y);
        textButton.addListener(listener);
        stage.addActor(textButton);
    }





}