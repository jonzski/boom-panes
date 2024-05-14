package scenes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu {

	private Stage stage;
	private Scene scene;
	private Parent root;

	protected final static int WINDOW_WIDTH = 1280;
	protected final static int WINDOW_HEIGHT = 720;

	public Menu() throws IOException {
		this.root = FXMLLoader.load(getClass().getResource("../screens/Menu.fxml"));
		this.scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
	}

	public void setMenu(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
		this.stage.setTitle("Boom Panes");
		this.stage.setScene(this.scene);
		this.stage.centerOnScreen();
		this.stage.show();
	}

	public Scene getScene() {
		return this.scene;
	}
	
	public Stage getStage() {
		return this.stage;
	}

}
