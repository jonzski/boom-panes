package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import components.Background;
import components.CaptionBox;
import game.App;
import utils.ScreenManager;

public class MainMenu extends GameScreen {
    private final Skin skin;
    private final Background backgroundComponent;
    private final CaptionBox captionBox;

    public MainMenu(final App app) {
        super(app);
        skin = setupSkin();
        backgroundComponent = new Background(stage);
        captionBox = new CaptionBox(stage);
    }

    private Skin setupSkin() {
        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        skin.add("default", buttonStyle);
        return skin;
    }

    @Override
    public void show() {
        backgroundComponent.addBackgroundImage("main-menu-bg.png", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        captionBox.addCaptionBox("caption-box.png", Gdx.graphics.getWidth(), 64, 0, 0);
        captionBox.insertCaptionBox("Main Menu", (float) Gdx.graphics.getWidth() / 2, (float) 16);

        setupImages();

        addButtons();

        Gdx.input.setInputProcessor(stage);
    }

    private void setupImages() {
        addImage("mainmenu.png", Gdx.graphics.getWidth(), 140, 0, 540);
        addImage("bomb.png", 650, 650, -100, 0);
        addImage("button1.png", 600, 70, 700, 400);
        addImage("button2.png", 600, 70, 700, 320);
        addImage("button3.png", 600, 70, 700, 240);
        addImage("button4.png", 600, 70, 700, 160);
    }

    private void addImage(String path, float width, float height, float x, float y) {
        stage.addActor(new Image(new Texture(path)) {{
            setSize(width, height);
            setPosition(x, y);
        }});
    }

    private void addButtons() {
        addButton("Single Player", 800, 420, () -> app.gsm.setScreen(ScreenManager.STATE.SINGLE));
        addButton("Battle", 800, 340, () -> app.gsm.setScreen(ScreenManager.STATE.LOBBY));
        addButton("Settings", 800, 260, () -> app.gsm.setScreen(ScreenManager.STATE.SETTING));
        addButton("Exit", 800, 180, () -> Gdx.app.exit());
    }

    private void addButton(String text, float x, float y, Runnable action) {
        TextButton button = new TextButton(text, skin);
        button.setPosition(x, y);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });
        stage.addActor(button);
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        app.gsm.setAllowBackNavigation(true);
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
    }
}
