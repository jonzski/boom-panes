package controllers.multiplayer;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.multiplayer.CreateJoin;
import scenes.multiplayer.Lobby;

import java.io.IOException;

public class JoinRoomController {

    Lobby lobby;
    CreateJoin createJoin;
    String username;

    public void switchToLobby(MouseEvent event) throws IOException {
        lobby = new Lobby(false, username);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(lobby.getScene());
    }

    public void switchToCreateJoin(MouseEvent event) throws IOException {
        createJoin = new CreateJoin();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createJoin.getScene());
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
