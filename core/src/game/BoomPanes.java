package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import scenes.MainMenu;

public class BoomPanes extends ApplicationAdapter {

	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}