package scenes.multiplayer;

import controllers.multiplayer.JoinRoomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class JoinRoom {

    private Scene scene;
    private Parent root;
    private JoinRoomController controller;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;

    public JoinRoom(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../screens/multiplayer/JoinRoom.fxml"));
        this.root = loader.load();
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.controller = loader.getController();
        this.controller.setUsername(username);
    }

    public Scene getScene() {
        return this.scene;
    }

}
