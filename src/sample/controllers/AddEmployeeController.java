package sample.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Position;
import sample.models.User;
import sample.utilities.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AddEmployeeController {

    public Button btnCancel;
    public Button btnOk;
    public TextField fldName, fldSurname, fldAddress;
    public TextField fldPostalNumber, fldCity;
    public ChoiceBox cbPosition;
    public TextField fldUsername;
    public PasswordField fldPassword;
    private ObservableList<Position> positionListObs;


    private User user = null;

    private UserDAO daoUser;
    public AddEmployeeController(User user) {
        this.user = user;
    }
    public AddEmployeeController() {
        daoUser = UserDAO.getInst();
        positionListObs = FXCollections.observableArrayList(daoUser.listPositions());

    }

    @FXML
    public void initialize() {
        if (user != null) {
            fldName.setText(user.getName());
            fldSurname.setText(user.getSurname());
            fldAddress.setText(user.getAddress());
            fldPostalNumber.setText(String.valueOf(user.getPostalNumber()));
            fldCity.setText(user.getCity());
            //cbPosition.setItems;
            fldUsername.setText(user.getUserName());
            fldPassword.setText(user.getPassword());
        }
        cbPosition.setItems(positionListObs);
    }

    public void okAction(ActionEvent actionEvent) {
        boolean sveOk = true;
        sveOk = isEmpty(fldName);
        sveOk &= isEmpty(fldSurname);
        sveOk &= isEmpty(fldAddress);
        sveOk &= isEmpty(fldPostalNumber);
        sveOk &= isEmpty(fldCity);
        sveOk &= isEmpty(fldUsername);
        sveOk &= isEmpty(fldPassword);


        if (!sveOk){
            user = null;
            return;
        }

        try {
            URL location = new URL("http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=%s" + fldPostalNumber.getText());
            fldPostalNumber.getStyleClass().removeAll("poljeIspravno");
            fldPostalNumber.getStyleClass().removeAll("poljeNijeIspravno");
            fldPostalNumber.getStyleClass().add("poljeSeProvjerava");
            new Thread(() -> {
                String json = "", line = null;
                BufferedReader entry = null;
                try {
                    entry = new BufferedReader(new InputStreamReader(location.openStream(), StandardCharsets.UTF_8));
                    while ((line = entry.readLine()) != null)
                        json = json + line;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!json.equals("OK")) {
                    Platform.runLater(() -> {
                        fldPostalNumber.getStyleClass().removeAll("poljeSeProvjerava");
                        fldPostalNumber.getStyleClass().add("poljeNijeIspravno");
                    });
                } else {
                    Platform.runLater(() -> {
                        fldPostalNumber.getStyleClass().removeAll("poljeSeProvjerava");
                        fldPostalNumber.getStyleClass().add("poljeIspravno");
                        Stage stage = (Stage) fldPostalNumber.getScene().getWindow();
                        stage.close();
                    });
                }
            }).start();
        } catch (Exception ex) {
        }
        if (user == null) user = new User();
        user.setName(fldName.getText());
        user.setSurname(fldSurname.getText());
        user.setAddress(fldAddress.getText());
        user.setPostalNumber(Integer.valueOf(fldPostalNumber.getText()));
        user.setCity(fldCity.getText());
        user.setPosition((Position) cbPosition.getValue());
        user.setUserName(fldUsername.getText());
        user.setPassword(fldPassword.getText());
        daoUser.addUser(user);
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    private boolean isEmpty(TextField field) {
        if (field.getText().trim().isEmpty()) {
            field.getStyleClass().removeAll("poljeIspravno");
            field.getStyleClass().add("poljeNijeIspravno");
            return false;
        } else {
            field.getStyleClass().removeAll("poljeNijeIspravno");
            field.getStyleClass().add("poljeIspravno");
        }
        return true;
    }


    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
