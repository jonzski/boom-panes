package classes;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class GameServer {
    private final int port;
    private final List<PlayerHandler> players = new CopyOnWriteArrayList<>();
    private ServerSocket serverSocket;

    public GameServer(int port) {
        this.port = port;
    }
    
    public void startServer() {
        System.out.println("Game Server started on port " + port);
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    PlayerHandler playerHandler = new PlayerHandler(socket);
                    players.add(playerHandler);
                    new Thread(playerHandler).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private class PlayerHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private Player player;

        public PlayerHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object message = in.readObject();
                    if (message instanceof Player) {
                        player = (Player) message;
                        broadcast(player);
                    } else if (message instanceof String) {
                        handleCommand((String) message);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                players.remove(this);
            }
        }

        private void handleCommand(String command) {
            // Handle game-specific commands here
        }

        private void broadcast(Object message) {
            for (PlayerHandler playerHandler : players) {
                try {
                    playerHandler.out.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
