package classes;

import controllers.LobbyController;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error creating server");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessageToClient(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("Server sent message: " + message);
        } catch (IOException e) {
            System.out.println("Error sending message");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMessageFromClient(VBox vbox) {
        new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    String messageFromClient = bufferedReader.readLine();
                    LobbyController.addMessage(messageFromClient, vbox);
                    System.out.println("Server received message: " + messageFromClient);
                } catch (IOException e) {
                    System.out.println("Error receiving message");
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
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
