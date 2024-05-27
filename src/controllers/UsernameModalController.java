package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UsernameModalController {

    @FXML
    private TextField usernameField;

    Image okButton = new Image("assets/Buttons/ok.png");
    Image okButtonHover = new Image("assets/Buttons/okHover.png");

    Image cancelButton = new Image("assets/Buttons/cancel.png");
    Image cancelButtonHover = new Image("assets/Buttons/cancelHover.png");

    @FXML
    ImageView okButtonView;
    @FXML
    ImageView cancelButtonView;

    private Stage dialogStage;
    private boolean okClicked = false;
    private String username;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getUsername() {
        return username;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void handleOk(MouseEvent event) {
        username = usernameField.getText();
        okClicked = true;
        dialogStage.close();
    }

    public void handleCancel(MouseEvent event) {
        dialogStage.close();
    }

    public void hoverOKButton(MouseEvent event) {
        okButtonView.setImage(okButtonHover);
    }

    public void notHoverOKButton(MouseEvent event) {okButtonView.setImage(okButton);
    }

    public void hoverCancelButton(MouseEvent event) {
        cancelButtonView.setImage(cancelButtonHover);
    }

    public void notHoverCancelButton(MouseEvent event) {
        cancelButtonView.setImage(cancelButton);
    }
}
