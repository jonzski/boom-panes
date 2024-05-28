package classes;

import javafx.application.Platform;
import javafx.stage.Stage;
import scenes.multiplayer.Room;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private ObjectOutputStream out;
    public ObjectInputStream in;
    private Room room;

    public GameClient(String host, int port, Room room) throws IOException {
        this.socket = new Socket(host, port);
        this.room = room;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void start() {
        new Thread(() -> {
            try {
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof Player) {
                        Player player = (Player) obj;
                        Platform.runLater(() -> room.updatePlayer(player));
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                try {
                    closeConnection();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.printStackTrace();
            }
        }).start();
    }

    public void connect(String hostname, int port, Room room) {
        try {
            Socket socket = new Socket(hostname, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Send a message to the server to indicate that the client has connected
            out.writeObject("Client connected");

            // Change the room scene
            Platform.runLater(() -> {
                ((Stage) room.getScene().getWindow()).setScene(room.getScene());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Player player) throws IOException {
        out.writeObject(player);
        out.flush();
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}