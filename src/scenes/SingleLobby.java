package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SingleLobby {

    private Scene scene;
    private Parent root;

    public SingleLobby() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../screens/SingleLobby.fxml"));
        this.scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return this.scene;
    }
}
