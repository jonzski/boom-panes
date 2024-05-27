package controllers.multiplayer;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.multiplayer.CreateJoin;
import scenes.multiplayer.Lobby;

import java.io.IOException;

public class CreateRoomController {

    Lobby lobby;
    CreateJoin createJoin;

    public void switchToLobby(MouseEvent event) throws IOException {
        lobby = new Lobby(true);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(lobby.getScene());
    }

    public void switchToCreateJoin(MouseEvent event) throws IOException {
        createJoin = new CreateJoin();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createJoin.getScene());
    }

}
