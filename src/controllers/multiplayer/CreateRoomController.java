package controllers.multiplayer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import scenes.Menu;
import scenes.multiplayer.CreateJoin;
import scenes.multiplayer.WaitingRoom;
import scenes.singleplayer.Room;

import java.io.IOException;
import java.util.ArrayList;

public class CreateRoomController {

    public ImageView pCountPrev;
    public ImageView pCountNext;
    public ImageView difficultyPrev;
    public ImageView difficultyNext;
    public ImageView durationPrev;
    public ImageView durationNext;
    public ImageView livesPrev;
    public ImageView livesNext;
    public Label playerCount;
    public Label difficultyLevel;
    public Label durationCount;
    public Label livesCount;
    WaitingRoom waitingRoom;
    CreateJoin createJoin;
    String username;

    public void switchToLobby(MouseEvent event) throws IOException {
        System.out.println("Switching to Lobby Game");
        System.out.println("Assessing parameters...");
        System.out.println("No. of players: " + pCount.get(pCountIndex));
        System.out.println("Difficulty: " + difficulty.get(difficultyIndex));
        System.out.println("Duration: " + duration.get(durationIndex));
        System.out.println("No. of lives: " + lives.get(livesIndex));
        waitingRoom = new WaitingRoom(true, username, pCount.get(pCountIndex), difficultyIndex+1, duration.get(durationIndex), lives.get(livesIndex));
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(waitingRoom.getScene());
    }

    public void switchToCreateJoin(MouseEvent event) throws IOException {
        createJoin = new CreateJoin();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createJoin.getScene());
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
