package scenes;
import classes.Client;
import classes.Server;
import controllers.LobbyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Lobby {

    private Parent root;
    private Scene scene;
    private LobbyController controller;

    private Server server;
    private Client client;
    private String username;

    public Lobby(boolean isServer) throws IOException {
        System.out.println("Lobby created");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../screens/Lobby.fxml"));
        this.root = loader.load();
        this.scene = new Scene(root, Menu.WINDOW_WIDTH, Menu.WINDOW_HEIGHT);
        this.controller = loader.getController();
        this.controller.setIsServer(isServer);

        if (isServer) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username: ");
            this.username = scanner.nextLine();
            this.server = new Server(new ServerSocket(1234), username);
            this.controller.setServer(server);
            this.server.startServer(this.controller.getChatBox());
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username: ");
            this.username = scanner.nextLine();
            this.client = new Client(new Socket("localhost", 1234), username);
            this.controller.setClient(client);
        }
    }

    public Scene getScene() {
        return scene;
    }

}
