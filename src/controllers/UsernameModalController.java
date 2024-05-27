package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UsernameModalController {

    @FXML
    private TextField usernameField;

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

    public void handleOk(ActionEvent event) {
        username = usernameField.getText();
        okClicked = true;
        dialogStage.close();
    }

    public void handleCancel(ActionEvent event) {
        dialogStage.close();
    }
}
