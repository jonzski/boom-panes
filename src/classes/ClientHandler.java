package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private static Server server;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientHandler(Socket socket, String username, Server server) {
        try {
            this.server = server;
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clients.add(this);
        } catch (Exception e) {
            System.out.println("Error creating client handler");
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println(username + " has joined the chat");
        } catch (Exception e) {
            System.out.println("Error reading username");
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
            return;
        }

        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient= bufferedReader.readLine();
                if (messageFromClient != null) {
                    broadcastMessage(messageFromClient);
                }
            } catch (Exception e) {
                System.out.println("Error receiving message");
                closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
                break;
            }
        }

    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            if (!client.username.equals(username)) {
                try {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                } catch (Exception e) {
                    System.out.println("Error sending message");
                    e.printStackTrace();
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
        // Add this line to broadcast message to server
        server.handleBroadcastFromClient(message);
    }

    public void removeClient() {
        clients.remove(this);
        System.out.println(username + " has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing everything");
            e.printStackTrace();
        }
    }


}
