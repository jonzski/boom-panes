package scenes.multiplayer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import scenes.Menu;

import java.io.IOException;

public class CreateJoin {

    private Scene scene;
    private Parent root;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;

    public CreateJoin() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../../screens/multiplayer/CreateJoin.fxml"));
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }

}
