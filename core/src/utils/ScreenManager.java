package utils;

import java.util.EnumMap;
import game.App;
import scenes.*;

public class ScreenManager {

    public final App app;

    private final EnumMap<STATE, GameScreen> gameScreens;
    private boolean allowBackNavigation;

    public enum STATE {
        SINGLE,
        BATTLE,
        SETTING,
        LOBBY,
        MAIN_MENU
    }   

    public ScreenManager(final App app) {
        this.app = app;
        this.allowBackNavigation = true; // Initially allow backward navigation
        this.gameScreens = new EnumMap<>(STATE.class); // Use EnumMap for better performance
        initGameScreens();
        setScreen(STATE.MAIN_MENU);
    }

    private void initGameScreens() {
        this.gameScreens.put(STATE.SINGLE, new SinglePlayer(app));
        this.gameScreens.put(STATE.BATTLE, new BattleRoom(app));
        this.gameScreens.put(STATE.MAIN_MENU, new MainMenu(app));
        this.gameScreens.put(STATE.LOBBY, new ServerRoom(app));
    }

    public void setAllowBackNavigation(boolean allowBackNavigation) {
        this.allowBackNavigation = allowBackNavigation;
    }

    public void setScreen(STATE nextScreen) {
        if (!allowBackNavigation && nextScreen != STATE.MAIN_MENU) {
            return; // Prevent backward navigation if not allowed
        }
        app.setScreen(gameScreens.get(nextScreen));
    }

    public void dispose() {
        gameScreens.values().forEach(GameScreen::dispose);
    }

    public void reset() {
        gameScreens.put(STATE.BATTLE, new BattleRoom(app)); // Just replace the existing BattleRoom instance
    }
}
