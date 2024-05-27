package controllers;

import classes.Client;
import classes.Server;
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

public class LobbyController implements Initializable {

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
    private Server server;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatBox.heightProperty().addListener((observableValue, oldValue, newValue) -> chatScroll.setVvalue((Double) newValue));

        if (isServer) {
            if (server != null) {
                server.receiveMessageFromClient(chatBox);
            } else {
                System.out.println("Server is null on receive message");
            }
        } else {
            if (client != null) {
                client.receiveMessageFromServer(chatBox);
            } else {
                System.out.println("Client is null on receive message");
            }
        }

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
                if (server != null) {
                    server.sendMessageToClient(message);
                } else {
                    System.out.println("Server is null on send message");
                }
            } else {
                if (client != null) {
                    client.sendMessageToServer(message);
                } else {
                    System.out.println("Client is null on send message");
                }
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
        textFlow.setStyle("-fx-color: rgb(239,242,255); -fx-background-color: rgb(101,101,101); -fx-background-radius: 20px;");
        text.setFill(Color.color(0.934, 0.935, 0.996));
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(() -> vbox.getChildren().add(hBox));
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    public void setServer(Server server) {
        this.server = server;

        Platform.runLater(()->playersContainer.getChildren().add(new Button()));
        if (server != null) {
            server.receiveMessageFromClient(chatBox);
        }
    }

    public void setClient(Client client) {
        this.client = client;
        Platform.runLater(()->playersContainer.getChildren().add(new TextField()));

        if (client != null) {
            client.receiveMessageFromServer(chatBox);
        }
    }
}
