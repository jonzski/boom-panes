package classes;

import controllers.multiplayer.WaitingRoomController;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private VBox chatBox;
    private String username;
    private WaitingRoomController controller;

    public ChatServer(ServerSocket serverSocket, String username, WaitingRoomController controller) {
        this.serverSocket = serverSocket;
        this.username = username;
        this.controller = controller;
    }

    public void startServer(VBox chatBox) {
        this.chatBox = chatBox;
        System.out.println("Server started as " + username + " on port " + serverSocket.getLocalPort());
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socket, this);
                    clients.add(clientHandler);
                    new Thread(clientHandler).start();
                    broadcastMessage(username + " has joined the chat!", true); // Notify all clients
                    controller.addPlayer(username);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void broadcastMessage(String message, boolean isFromServer) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
        if (!isFromServer) {
            WaitingRoomController.addMessage(message, chatBox);
        }
    }

    public void closeServer() {
        try {
            serverSocket.close();
            for (ClientHandler client : clients) {
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private ChatServer chatServer;

        public ClientHandler(Socket socket, ChatServer chatServer) {
            this.socket = socket;
            this.chatServer = chatServer;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {
                    chatServer.broadcastMessage(message, false);
                }
            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void close() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
