package classes;

import controllers.multiplayer.WaitingRoomController;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private VBox chatBox;
    private String username;

    public ChatClient(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
    }

    public void sendMessageToChat(String message) {
        out.println(username + ": " + message);
    }

    public void listenForMessages(VBox chatBox) {
        this.chatBox = chatBox;
        new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {
                    if (!message.startsWith(username + ": ")) {
                        WaitingRoomController.addMessage(message, chatBox);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                closeConnection();
            }
        }).start();
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
