package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import scenes.*;
import scenes.multiplayer.CreateJoin;
import scenes.singleplayer.Lobby;

import java.io.IOException;

public class MenuController {

    private Lobby lobby;
    private CreateJoin createJoin;
    private About aboutPage;

    Image singleHover = new Image("assets/Buttons/singleHover.png");
    Image singleImg = new Image("assets/Buttons/single.png");

    Image battleHover = new Image("assets/Buttons/battleHover.png");
    Image battleImg = new Image("assets/Buttons/battle.png");

    Image aboutHover = new Image("assets/Buttons/aboutHover.png");
    Image aboutImg = new Image("assets/Buttons/about.png");

    Image quitHover = new Image("assets/Buttons/hoverQuit.png");
    Image quitImg = new Image("assets/Buttons/Quit.png");

    Image boomClick = new Image("assets/Buttons/boomClick.png");

    @FXML
    ImageView single;
    @FXML
    ImageView battle;
    @FXML
    ImageView about;
    @FXML
    ImageView exit;
    @FXML
    ImageView boom;


    public void switchToLobbySingle(MouseEvent event) throws IOException {
        lobby = new Lobby();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(lobby.getScene());
    }

    public void switchToCreateJoin(MouseEvent event) throws IOException {
        createJoin = new CreateJoin();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createJoin.getScene());
    }

    public void switchToAbout(MouseEvent event) throws IOException{
        aboutPage = new About();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(aboutPage.getScene());
    }


    public void exitGame(){
        System.exit(1);
    }

    public void hoverSingle(MouseEvent event) {
        single.setImage(singleHover);
    }

    public void exitSingle(MouseEvent event) {
        single.setImage(singleImg);
    }

    public void hoverBattle(MouseEvent event) {
        battle.setImage(battleHover);
    }

    public void exitBattle(MouseEvent event) {
        battle.setImage(battleImg);
    }

    public void hoverAbout(MouseEvent event) {
        about.setImage(aboutHover);
    }

    public void exitAbout(MouseEvent event) {
        about.setImage(aboutImg);
    }

    public void hoverQuit(MouseEvent event) {
        exit.setImage(quitHover);
    }

    public void exitQuit(MouseEvent event) {
        exit.setImage(quitImg);
    }

    public void boomClickFunc (MouseEvent event) {
        boom.setImage(boomClick);
    }

}
