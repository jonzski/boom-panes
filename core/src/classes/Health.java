package classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Health extends Image {

    public Health(float x, float y) {
        super(new Texture("health.png"));
        setPosition(x, y);
        setScale(0.8f);
    }


}