package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChangeRegController {
    private Controller controller;

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField nickField;
    @FXML
    public TextField new_nickField;
    @FXML
    public TextArea textArea;

    public void tryToChangeAuth(ActionEvent actionEvent) {
        controller.tryToChangeAuth(loginField.getText().trim(),
                passwordField.getText().trim(),
                nickField.getText().trim(),
                new_nickField.getText().trim());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void addMsgToTextArea(String msg) {
        textArea.appendText(msg + "\n");
    }


}
