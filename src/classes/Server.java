package classes;

import controllers.multiplayer.WaitingRoomController;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private String username;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private VBox vbox;

    public Server(ServerSocket serverSocket, String username) {
        this.serverSocket = serverSocket;
        this.username = username;
    }

    public void startServer(VBox vbox) throws IOException {
        System.out.println("Server started as " + username + " on port 1234");
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    this.socket = serverSocket.accept();
                    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    String clientUsername = bufferedReader.readLine();

                    ClientHandler clientHandler = new ClientHandler(socket, clientUsername, this);
                    Thread thread = new Thread(clientHandler);
                    thread.start();

                    receiveMessageFromClient(vbox);
                }
            } catch (IOException e) {
                System.out.println("Error accepting client connection");
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessageToClient(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(username + " sent : " + message);
        } catch (IOException e) {
            System.out.println("Error sending message");
            e.printStackTrace();
        }
    }

    public void receiveMessageFromClient(VBox vbox) {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    String messageFromClient = bufferedReader.readLine();
                    if (messageFromClient != null) {
                        WaitingRoomController.addMessage(messageFromClient, vbox);
                        System.out.println(username + " received message: " + messageFromClient);
                    }
                } catch (IOException e) {
                    System.out.println("Error receiving message");
                    e.printStackTrace();
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }
            }
        }).start();
    }

    public void handleBroadcastFromClient(String message) {
        System.out.println("Message from client: " + message);
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing server resources");
            e.printStackTrace();
        }
    }
}