package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.models.Position;
import sample.models.User;
import sample.models.UserDAO;
import sample.utilities.WorkTimeTrackerSQLiteDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ManageEmployeesController {
    public Button btnEnd;
    public TextField fldName, fldSurname, fldAddress;
    public TextField fldPostalNumber, fldCity;
    public ChoiceBox cbPosition;
    public TextField fldUsername, fldPassword;
    public TableView<User> tblEmployees;

    public TableColumn nameCol;
    public TableColumn surnameCol;

    private UserDAO daoUser;
    private WorkTimeTrackerSQLiteDAO daoWtt;
    private ObservableList<User> userListObs;
    private User user;

    private List<Position> allPositions = new ArrayList<Position>();

    public ManageEmployeesController() {
        daoUser = UserDAO.getInst();
        userListObs = FXCollections.observableArrayList(daoUser.listUsers());
    }

    public ManageEmployeesController(User user){
        this.user = user;
    }
    @FXML
    public void initialize() {
        tblEmployees.setItems(userListObs);
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory("surname"));

        //addPositionChoices();

        user = tblEmployees.getSelectionModel().getSelectedItem();
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
    }



    public void deleteEmployeeAction(ActionEvent actionEvent) {
        User user = tblEmployees.getSelectionModel().getSelectedItem();
        if (user == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Deleting user " + user.getName());
        alert.setContentText("Are you sure you want to delete user " + user.getName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            daoUser.removeUser(user);
            userListObs.setAll(daoUser.listUsers());
        }
    }

    public void exitAction(ActionEvent actionEvent) {
       Stage stage = (Stage) btnEnd.getScene().getWindow();
      stage.close();
    }

    public void addEmployeeAction(ActionEvent actionEvent) {
        tblEmployees.getItems().add(new User());
        tblEmployees.getSelectionModel().selectLast();



        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adding confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            daoUser.addUser(user);
        }
    }

   /* private void addPositionChoices() {
        try {
            allPositions = daoWtt.getAllPositions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Position> list = FXCollections.observableArrayList(allPositions);
        cbPosition.setItems(list);
    }*/
}
