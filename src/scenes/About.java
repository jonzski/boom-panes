package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class About {

    private Stage stage;
    private Scene scene;
    private Parent root;

    protected final static int WINDOW_WIDTH = 1280;
    protected final static int WINDOW_HEIGHT = 720;

    public About() throws IOException {
        this.root = FXMLLoader.load(getClass().getResource("../screens/About.fxml"));
        this.scene = new Scene(root, About.WINDOW_WIDTH, About.WINDOW_HEIGHT);
    }

    public Scene getScene() {
        return this.scene;
    }

}
