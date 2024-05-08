package screens;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.*;

public class SceneController {

	private Stage stage;
	double x, y = 0;
	Menu menu;
	SingleLobby singleLobby;
	SingleRoom singleGame;

	public void switchToMenu(MouseEvent event) throws IOException{
		menu = new Menu();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(menu.getScene());
		stage.show();
	}

	public void switchToSinglePlayer(MouseEvent event) throws IOException {
		singleLobby = new SingleLobby();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(singleLobby.getScene());
		stage.show();
	}

	public void switchToBattleSingle(MouseEvent event) throws IOException{
		singleGame = new SingleRoom();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(singleGame.getScene());
		stage.show();
		singleGame.start();
	}

	public void exitGame(){
		System.exit(1);
	}
	

}
