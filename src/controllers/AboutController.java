package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.About;
import scenes.Menu;
import scenes.multiplayer.CreateJoin;
import scenes.singleplayer.Lobby;

import java.io.IOException;

public class AboutController {
    Menu menu;

    public void switchToMenu(MouseEvent event) throws IOException {
        menu = new Menu();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(menu.getScene());
    }

}
