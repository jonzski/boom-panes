package controllers.multiplayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scenes.Menu;
import scenes.multiplayer.CreateRoom;
import scenes.multiplayer.JoinRoom;
import controllers.UsernameModalController;

import java.io.IOException;

public class CreateJoinController {

    private CreateRoom createRoom;
    private JoinRoom joinRoom;
    private Menu menu;

    private Image createRoomButton = new Image("assets/Buttons/createRoom.png");
    private Image createRoomHover = new Image("assets/Buttons/createRoomHover.png");

    private Image joinRoomButton = new Image("assets/Buttons/joinRoom.png");
    private Image joinRoomHover = new Image("assets/Buttons/joinRoomHover.png");

    @FXML
    ImageView createRoomButtonView;
    @FXML
    ImageView joinRoomButtonView;
    @FXML


    public void switchToCreateRoom(MouseEvent event) throws IOException {
        String username = showUsernameModal();
        if (username != null) {
            createRoom = new CreateRoom(username);
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(createRoom.getScene());
        }
    }

    public void switchToJoinRoom(MouseEvent event) throws IOException {
        String username = showUsernameModal();
        if (username != null) {
            joinRoom = new JoinRoom(username);
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(joinRoom.getScene());
        }
    }

    public void switchToMenu(MouseEvent event) throws IOException{
        menu = new Menu();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(menu.getScene());
    }

    public void hoverCreateRoom(MouseEvent event) {
        createRoomButtonView.setImage(createRoomHover);
    }

    public void hoverJoinRoom(MouseEvent event) {
        joinRoomButtonView.setImage(joinRoomHover);
    }

    public void notHoverCreateRoom(MouseEvent event) {
        createRoomButtonView.setImage(createRoomButton);
    }

    public void notHoverJoinRoom(MouseEvent event) {
        joinRoomButtonView.setImage(joinRoomButton);
    }

    private String showUsernameModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../screens/UsernameModal.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Username");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(createRoomButtonView.getScene().getWindow());
            dialogStage.setScene(new Scene(loader.load()));

            UsernameModalController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                return controller.getUsername();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
