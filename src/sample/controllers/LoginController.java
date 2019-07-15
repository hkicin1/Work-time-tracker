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

    private WorkTimeTracker wtt = WorkTimeTracker.createWorkTimeTracker();

    public Person getRegisteredPerson() {
        return registeredPerson;
    }

    public void setRegisteredPerson(Person registeredPerson) {
        this.registeredPerson = registeredPerson;
    }

    private boolean admin = false;

    private Person personExistsInDatabase(boolean admin, String enteredUsername, String enteredPassword) {
        return wtt.findPersonByUsernameAndPasaword(admin, enteredUsername, enteredPassword);
    }

    public void loginAction(ActionEvent actionEvent) {
        username = txtUsername.getText();
        password = pwdPassword.getText();

        if(password != null || username != null) {
            setRegisteredPerson(personExistsInDatabase(admin, username, password));
        }

        if(getRegisteredPerson() == null){
            lblConfirmation.setText("Neispravni pristupni podaci, unesite ponovo!");
            txtUsername.clear();
            pwdPassword.clear();
        }
        else {
            lblConfirmation.setText("Uspješno ste prijavljeni!");
            Stage stage = (Stage) pwdPassword.getScene().getWindow();
            stage.close();
            if(admin) openAdminPannel();
            else openEmployeePannel();
        }

    }

    private void openEmployeePannel() {
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
