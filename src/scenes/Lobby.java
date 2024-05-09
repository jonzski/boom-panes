package scenes;

import classes.GameTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import java.io.IOException;

public class Lobby {

    private Scene scene;
    private Parent root;

    private int health;
    private int duration;
    private int difficulty;
    private int numPlayers;

    public Lobby() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../screens/Lobby.fxml"));
        this.scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }
}
