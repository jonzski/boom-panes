package controllers.multiplayer;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.multiplayer.CreateJoin;
import scenes.multiplayer.WaitingRoom;

import java.io.IOException;

public class JoinRoomController {

    WaitingRoom waitingRoom;
    CreateJoin createJoin;
    String username;

    public void switchToLobby(MouseEvent event) throws IOException {
        waitingRoom = new WaitingRoom(false, username, 0, 0, 0, 0);
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(waitingRoom.getScene());
    }

    public void switchToCreateJoin(MouseEvent event) throws IOException {
        createJoin = new CreateJoin();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createJoin.getScene());
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
