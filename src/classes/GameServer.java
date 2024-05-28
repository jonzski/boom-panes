package classes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private int playerCount;
    private int duration;
    private int lives;

    public GameServer(int port, int playerCount, int duration, int lives) throws IOException {
        this.playerCount = playerCount;
        this.duration = duration;
        this.lives = lives;
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcast(Player player) {
        for (ClientHandler client : clients) {
            client.send(player);
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private GameServer server;

        public ClientHandler(Socket socket, GameServer server) throws IOException {
            this.socket = socket;
            this.server = server;
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof Player) {
                        Player player = (Player) obj;
                        server.broadcast(player);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                clients.remove(this);
            } finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void send(Player player) {
            try {
                out.writeObject(player);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}