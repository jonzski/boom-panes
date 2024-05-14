package scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class CreateJoin {

    @FXML
    Button joinRoom;

    @FXML
    Button createRoom;

    private Lobby lobby;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public CreateJoin() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../screens/CreateJoin.fxml"));
        this.scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }

}
