package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextArea txtUsername;
    public PasswordField pwdPassword   ;

    private String username;
    private String password;

    public void loginAction(ActionEvent actionEvent) {
        username = txtUsername.getText();
        password = pwdPassword.getText();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
