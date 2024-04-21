package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import utils.ScreenManager;

import java.io.IOException;


public class App extends Game {

    public static String TITLE= "Boom Panes";
    public static int RES_WIDTH = 1280;
    public static int RES_HEIGHT = 720;
    public static boolean RESIZABLE = false;
    public static boolean VSYNC = true;
    public static int FPS = 60;

    // Managers
    public AssetManager assets;
    public ScreenManager gsm;

    // Batches
    public SpriteBatch batch;
    public ShapeRenderer shapeBatch;


    Texture bg;

    @Override
    public void create() {

        assets = new AssetManager();
        batch = new SpriteBatch();
        shapeBatch = new ShapeRenderer();
        gsm = new ScreenManager(this);
    }

    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();

        }
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        bg.dispose();
        shapeBatch.dispose();
        gsm.dispose();
    }
}