package scenes.multiplayer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class CreateRoom {

    private Scene scene;
    private Parent root;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;

    public CreateRoom() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../../screens/multiplayer/CreateRoom.fxml"));
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return this.scene;
    }


}
