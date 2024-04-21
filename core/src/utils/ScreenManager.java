package utils;

import java.awt.*;
import java.util.HashMap;
import game.App;
import scenes.*;


public class ScreenManager {

    public final App app;

    private HashMap<STATE, GameScreen> gameScreens;

    public enum STATE {
        SINGLE,
        BATTLE,
        SETTING,
        LOBBY,
        MAIN_MENU
    }

    public ScreenManager(final App app) {
        this.app = app;
        initGameScreens();
        setScreen(STATE.MAIN_MENU);
    }

    private void initGameScreens() {
        this.gameScreens = new HashMap<STATE, GameScreen>();
        this.gameScreens.put(STATE.SINGLE, new SinglePlayer(app));
        this.gameScreens.put(STATE.BATTLE, new BattleRoom(app));
        this.gameScreens.put(STATE.MAIN_MENU, new MainMenu(app));
        this.gameScreens.put(STATE.LOBBY, new ServerRoom(app));
    }

    public void setScreen(STATE nextScreen) {
        app.setScreen(gameScreens.get(nextScreen));
    }

    public void dispose() {
        for (GameScreen screen : gameScreens.values()) {
            if (screen != null) {
                screen.dispose();
            }
        }
    }

    public void reset() {
        this.gameScreens.remove(STATE.BATTLE);
        this.gameScreens.put(STATE.BATTLE, new BattleRoom(app));
    }

}
