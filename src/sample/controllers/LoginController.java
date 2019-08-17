package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Injector;
import sample.exceptions.InvalidCredentialException;
import sample.exceptions.PersonDoesNotExistException;
import sample.models.Person;
import sample.utilities.WorkTimeTracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController implements Initializable {

    public TextField txtUsername;
    public PasswordField pwdPassword;
    public Label lblConfirmation;

    private String username;
    private String password;

    private Person registeredPerson = null;

    private WorkTimeTracker workTimeTracker = Injector.getWorkTimeTracker();

    public Person getRegisteredPerson() {
        return registeredPerson;
    }

    public void setRegisteredPerson(Person registeredPerson) {
        this.registeredPerson = registeredPerson;
    }

    private boolean admin = true;

    public void loginAction(ActionEvent actionEvent) {
        username = txtUsername.getText();
        password = pwdPassword.getText();

        if (username.isEmpty() || username == null) {
            lblConfirmation.setText("Unesite username");

            return;
        } else if (password == null || password.isEmpty()) {
            lblConfirmation.setText("Unesite password");
            return;
        }

        try {
            Person person = workTimeTracker.loginPerson(username,password);
            lblConfirmation.setText("Uspješno ste prijavljeni!");
            Stage stage = (Stage) pwdPassword.getScene().getWindow();
            stage.close();
            setRegisteredPerson(person);
            if (admin) openAdminPannel();
            else openEmployeePannel();
        } catch (PersonDoesNotExistException e) {
            lblConfirmation.setText("User ne postoji");
            txtUsername.clear();
            pwdPassword.clear();
        } catch (InvalidCredentialException e) {
            lblConfirmation.setText("Neispravni pristupni podaci, unesite ponovo!");
            txtUsername.clear();
            pwdPassword.clear();
        }

    }

    private void openEmployeePannel() {
        Person a = registeredPerson;//e.getAdminByUsername(enteredUsername);
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
            root = loader.load();
            AdminController controller = loader.getController();
            controller.setRegisteredAdmin(a);
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
        Person a = registeredPerson;//e.getAdminByUsername(enteredUsername);
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

    }
}
