package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.models.User;
import sample.models.UserDAO;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


public class ManageEmployeesController {
    public Button btnEnd;

    public TableView<User> tblEmployees;

    public TableColumn nameCol;
    public TableColumn surnameCol;
    public TableColumn addressCol;
    public TableColumn postalNumberCol;
    public TableColumn cityCol;
    public TableColumn positionCol;

    private UserDAO daoUser;
    private ObservableList<User> userListObs;
    private User user;


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
        addressCol.setCellValueFactory(new PropertyValueFactory("address"));
        postalNumberCol.setCellValueFactory(new PropertyValueFactory("postalNumber"));
        cityCol.setCellValueFactory(new PropertyValueFactory("city"));
        positionCol.setCellValueFactory(new PropertyValueFactory("position"));

    }



    public void deleteEmployeeAction(ActionEvent actionEvent) {
        User user = tblEmployees.getSelectionModel().getSelectedItem();
        if (user == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Deleting user " + user.getName());
        alert.setContentText("Are you sure you want to delete user " + user.getName() + " " + user.getSurname() + "?");

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

    public void addEmployeeAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addEmployee.fxml"));
        root = loader.load();
        stage.setTitle("Adding new employee");
        stage.setResizable(false);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
