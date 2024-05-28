package classes;

import scenes.multiplayer.Room;

import java.io.*;
import java.net.*;

public class GameClient {
    private final String host;
    private final int port;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Room room;

    public GameClient(String host, int port, Room room) {
        this.host = host;
        this.port = port;
        this.room = room;
    }

    public void start() throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        new Thread(new Listener()).start();
    }

    public void send(Object message) throws IOException {
        out.writeObject(message);
    }

    private class Listener implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Object message = in.readObject();
                    if (message instanceof Player) {
                        room.updatePlayer((Player) message);
                    } else if (message instanceof String) {
                        handleCommand((String) message);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void handleCommand(String command) {
            // Handle game-specific commands here
        }
    }
}
