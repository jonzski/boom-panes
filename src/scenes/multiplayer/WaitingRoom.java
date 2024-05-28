package scenes.multiplayer;
import classes.ChatClient;
import classes.ChatServer;
import controllers.multiplayer.WaitingRoomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WaitingRoom {

    private Parent root;
    private Scene scene;
    private WaitingRoomController controller;

    private ChatServer chatServer;
    private ChatClient chatClient;

    private final static int WINDOW_WIDTH = 1280;
    private final static int WINDOW_HEIGHT = 720;

    public WaitingRoom(boolean isServer, String username, int playerCount, int duration, int lives) throws IOException {
        System.out.println("Lobby created");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../screens/multiplayer/WaitingRoom.fxml"));
        this.root = loader.load();
        this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.controller = loader.getController();
        this.controller.setIsServer(isServer);
        this.controller.setUsername(username);
        this.controller.setPlayerCount(playerCount);
        this.controller.setDuration(duration);
        this.controller.setLives(lives);

        if (isServer) {
            this.chatServer = new ChatServer(new ServerSocket(1234), username);
            this.controller.setServer(chatServer);
            this.chatServer.startServer(this.controller.getChatBox());
        } else {
            this.chatClient = new ChatClient(new Socket("localhost", 1234), username);
            this.controller.setClient(chatClient);
        }
    }

    public Scene getScene() {
        return scene;
    }

}
