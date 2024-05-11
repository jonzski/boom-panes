package screens;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.*;

public class SceneController {

	private Stage stage;

	Menu menu;
	SingleLobby singleLobby;
	SingleRoom singleGame;
	Lobby lobby;


	public void switchToMenu(MouseEvent event) throws IOException{
		menu = new Menu();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(menu.getScene());
		stage.show();
	}

	public void switchToLobbySingle(MouseEvent event) throws IOException {
		singleLobby = new SingleLobby();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(singleLobby.getScene());
		stage.show();
	}

	public void switchToSingleGame(MouseEvent event) throws IOException{
		singleGame = new SingleRoom();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(singleGame.getScene());
		stage.show();
		singleGame.start();
	}

	public void switchToLobby(MouseEvent event) throws IOException{
		lobby = new Lobby();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(lobby.getScene());
		stage.show();
	}

	public void exitGame(){
		System.exit(1);
	}

}
