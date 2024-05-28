package controllers.multiplayer;

import classes.ChatClient;
import classes.ChatServer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingRoomController implements Initializable {

    @FXML
    public GridPane playersContainer;

    @FXML
    private Button buttonSend;

    @FXML
    private VBox chatBox;

    @FXML
    private TextField chatField;

    @FXML
    private ScrollPane chatScroll;

    private boolean isServer;
    private ChatServer chatServer;
    private ChatClient chatClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatBox.heightProperty().addListener((observableValue, oldValue, newValue) -> chatScroll.setVvalue((Double) newValue));

        buttonSend.setOnAction(event -> sendMessage());

        chatField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = chatField.getText();
        if (!message.isEmpty()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(message);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            textFlow.setStyle("-fx-color: rgb(239,242,255); -fx-background-color: rgb(15,125, 242); -fx-background-radius: 20px;");
            text.setFill(Color.color(0.934, 0.935, 0.996));

            hBox.getChildren().add(textFlow);
            chatBox.getChildren().add(hBox);
            if (isServer) {
                new Thread(() -> chatServer.broadcastMessage(message)).start();
            } else {
                new Thread(() -> chatClient.sendMessageToChat(message)).start();
            }
            chatField.clear();
        }
    }

    public static void addMessage(String message, VBox vbox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5 , 10));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(101,101,101); -fx-background-radius: 20px;");
        text.setFill(Color.color(0.934, 0.935, 0.996));
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(() -> vbox.getChildren().add(hBox));
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    public void setServer(ChatServer chatServer) {
        this.chatServer = chatServer;
        if (chatServer != null) {
            chatServer.startServer(chatBox);
        }
    }

    public void setClient(ChatClient chatClient) {
        this.chatClient = chatClient;
        if (chatClient != null) {
            chatClient.listenForMessages(chatBox);
        }
    }

    public VBox getChatBox() {
        return chatBox;
    }
}
