package controllers.singleplayer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scenes.Menu;
import scenes.SingleGame;
import scenes.singleplayer.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SingleLobbyController {

    // fxml ids
    public Label setCount;
    public Label playerCount;
    public Label difficultyLevel;
    public Label durationCount;
    public Label livesCount;
    public ImageView livesNext;
    public ImageView livesPrev;
    public ImageView durationNext;
    public ImageView durationPrev;
    public ImageView difficultyNext;
    public ImageView difficultyPrev;
    public ImageView setNext;
    public ImageView setPrev;
    public ImageView playerNext;
    public ImageView playerPrev;
    public AnchorPane singleRoot;
    Menu menu;
    Room singleGame;
    SingleGame singleUI;


    // menu values
    public ArrayList<Integer> pCount = new ArrayList<>() {{
        addAll(java.util.Arrays.asList(2,3,4,5,6,7,8,9));
    }};
    public ArrayList<String> difficulty = new ArrayList<>() {{
        addAll(java.util.Arrays.asList("EASY","MEDIUM","HARD"));
    }};
    public ArrayList<Integer> duration = new ArrayList<>() {{
        addAll(java.util.Arrays.asList(5,10,15,20,25,30));
    }};
    public ArrayList<Integer> lives = new ArrayList<>() {{
        addAll(java.util.Arrays.asList(1,2,3,4,5));
    }};

    // menu value iterators
    int pCountIndex = 0;
    int difficultyIndex = 0;
    int durationIndex = 0;
    int livesIndex = 0;

    // onClick functions
    public void switchToSingleGame(MouseEvent event) throws IOException {
        System.out.println("Switching to Single Game");
        System.out.println("Assessing parameters...");
        System.out.println("No. of players: " + pCount.get(pCountIndex));
        System.out.println("Difficulty: " + difficulty.get(difficultyIndex));
        System.out.println("Duration: " + duration.get(durationIndex));
        System.out.println("No. of lives: " + lives.get(livesIndex));
        singleGame = new Room(pCount.get(pCountIndex), difficultyIndex+1, duration.get(durationIndex), lives.get(livesIndex));
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(singleGame.getScene());
        singleGame.start();
    }

    public void switchToMenu(MouseEvent event) throws IOException{
        menu = new Menu();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(menu.getScene());
    }

    public void playerIncrement(MouseEvent event) throws IOException {
        if(pCountIndex < pCount.size()-1) {
            pCountIndex++;
            playerCount.setText(String.valueOf(pCount.get(pCountIndex)));
        }
    }

    public void playerDecrement(MouseEvent event) throws IOException {
        if(pCountIndex > 0) {
            pCountIndex--;
            playerCount.setText(String.valueOf(pCount.get(pCountIndex)));
        }
    }

    public void difficultyIncrement(MouseEvent event) throws IOException {
        if(difficultyIndex < difficulty.size()-1) {
            difficultyIndex++;
            difficultyLevel.setText(difficulty.get(difficultyIndex));
        }
    }

    public void difficultyDecrement(MouseEvent event) throws IOException {
        if(difficultyIndex > 0) {
            difficultyIndex--;
            difficultyLevel.setText(difficulty.get(difficultyIndex));
        }
    }

    public void durationIncrement(MouseEvent event) throws IOException {
        if(durationIndex < duration.size()-1) {
            durationIndex++;
            durationCount.setText("DURATION: ".concat(String.valueOf(duration.get(durationIndex))));
        }
    }

    public void durationDecrement(MouseEvent event) throws IOException {
        if(durationIndex > 0) {
            durationIndex--;
            durationCount.setText("DURATION: ".concat(String.valueOf(duration.get(durationIndex))));
        }
    }

    public void livesIncrement(MouseEvent event) throws IOException {
        if(livesIndex < lives.size()-1) {
            livesIndex++;
            livesCount.setText("LIVES: ".concat(String.valueOf(lives.get(livesIndex))));
        }
    }

    public void livesDecrement(MouseEvent event) throws IOException {
        if(livesIndex > 0) {
            livesIndex--;
            livesCount.setText("LIVES: ".concat(String.valueOf(lives.get(livesIndex))));
        }
    }
}
