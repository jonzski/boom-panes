package scenes.multiplayer;

import controllers.multiplayer.CreateRoomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class CreateRoom {

    private Scene scene;
    private Parent root;
    private CreateRoomController controller;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;

    public CreateRoom(String username) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../screens/multiplayer/CreateRoom.fxml"));
        this.root = loader.load();
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.controller = loader.getController();
        this.controller.setUsername(username);
    }

    public Scene getScene() {
        return this.scene;
    }


}
