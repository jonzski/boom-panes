package controllers;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.Menu;
import scenes.SingleRoom;

import java.io.IOException;

public class SingleLobbyController {

    Menu menu;
    SingleRoom singleGame;

    public void switchToSingleGame(MouseEvent event) throws IOException {
        singleGame = new SingleRoom();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(singleGame.getScene());
        singleGame.start();
    }

    public void switchToMenu(MouseEvent event) throws IOException{
        menu = new Menu();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(menu.getScene());
    }

}
