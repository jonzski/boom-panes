package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SingleGame {

    private Scene scene;
    private Parent root;

    private int health;
    private int duration;
    private int difficulty;
    private int numPlayers;

    public SingleGame() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../screens/singleplayer/SingleGame.fxml"));
        this.scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return this.scene;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

}
