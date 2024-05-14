package controllers;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.Lobby;

import java.io.IOException;

public class CreateJoinController {

    private Lobby lobby;

    public void switchToServerLobby(MouseEvent event) throws IOException {
        lobby = new Lobby(true);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(lobby.getScene());
    }

    public void switchToClientLobby(MouseEvent event) throws IOException{
        lobby = new Lobby(false);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(lobby.getScene());
    }

}
