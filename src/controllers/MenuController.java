package controllers;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.CreateJoin;
import scenes.SingleLobby;

import java.io.IOException;

public class MenuController {

    private SingleLobby singleLobby;
    private CreateJoin createJoin;

    public void switchToLobbySingle(MouseEvent event) throws IOException {
        singleLobby = new SingleLobby();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(singleLobby.getScene());
    }

    public void switchToCreateJoin(MouseEvent event) throws IOException {
        createJoin = new CreateJoin();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createJoin.getScene());
    }

    public void exitGame(){
        System.exit(1);
    }
}
