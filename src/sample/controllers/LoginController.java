package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Injector;
import sample.exceptions.InvalidCredentialException;
import sample.exceptions.PersonDoesNotExistException;
import sample.models.User;
import sample.utilities.WorkTimeTracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController implements Initializable {

    public TextField txtUsername;
    public PasswordField pwdPassword;
    public Label lblConfirmation;
    public Button btnLogin;

    private String username;
    private String password;

    private User registeredUser = null;

    private WorkTimeTracker workTimeTracker = Injector.getWorkTimeTracker();


    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }



    public void loginAction(ActionEvent actionEvent) {
        username = txtUsername.getText();
        password = pwdPassword.getText();

        if (username.isEmpty() || username == null) {
            lblConfirmation.setText("Enter your username!");

            return;
        } else if (password == null || password.isEmpty()) {
            lblConfirmation.setText("Enter your password!");
            return;
        }

        try {
            User user = workTimeTracker.loginPerson(username,password);
            setRegisteredUser(user);
            lblConfirmation.setText("You are successfully logged in!");
            Stage stage = (Stage) pwdPassword.getScene().getWindow();
            stage.close();
            setRegisteredUser(user);
            if (user.getIsAdmin() == 1) openAdminPannel();
            else if (user.getIsAdmin() == 0 && user.getIsAdmin() != 1) openEmployeePannel();
        } catch (PersonDoesNotExistException e) {
            lblConfirmation.setText("User does not exist!");
            txtUsername.clear();
            pwdPassword.clear();
        } catch (InvalidCredentialException e) {
            lblConfirmation.setText("Password is not correct, please enter again!"); //TODO sročiti fino na engleskom!
            txtUsername.clear();
            pwdPassword.clear();
        }

    }

    private void openEmployeePannel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
        EmployeeController controller = new EmployeeController(getRegisteredUser());
        loader.setController(controller);

        Parent root;
        try {

            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Employee panel");
            stage.setResizable(false);
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAdminPannel() {
        User a = registeredUser;//e.getAdminByUsername(enteredUsername);
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            root = loader.load();
            AdminController controller = loader.getController();
            controller.setRegisteredAdmin(a);
            stage.setTitle("Admin panel");
            stage.setResizable(false);
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setDefaultButton(true);
    }
}
