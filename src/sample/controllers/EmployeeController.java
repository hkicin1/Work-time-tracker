package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import sample.enums.ReportType;
import sample.models.User;
import sample.utilities.PrintReport;
import sample.utilities.WorkTimeTrackerSQLiteDAO;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class EmployeeController {

    private User registeredEmployee;
    public Label lblUsername;
    private WorkTimeTrackerSQLiteDAO dao;

    @FXML
    public void initialize() {
        lblUsername.setText("Welcome " + registeredEmployee.getName() + "!");
    }

    public EmployeeController(User user) {
        this.registeredEmployee = user;
    }

    public void setRegisteredEmployee(User a) {
        this.registeredEmployee = a;
    }

    public void selectProjectAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/selectProject.fxml"));
            SelectProjectController controller = new SelectProjectController(registeredEmployee);
            loader.setController(controller);
            root = loader.load();
            stage.setResizable(false);
            stage.setTitle("Select project");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startWorkTimeAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startWorkTime.fxml"));
            StartWorkTimeController controller = new StartWorkTimeController(registeredEmployee);
            loader.setController(controller);
            root = loader.load();
            stage.setResizable(false);
            controller.setRegisteredEmployee(registeredEmployee);
            stage.setTitle("Start working");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void helpAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employeeHelp.fxml"));
        root = loader.load();
        stage.setTitle("Help");
        stage.setResizable(false);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void showWorkHoursReportAction(ActionEvent actionEvent) throws JRException {

        dao = new WorkTimeTrackerSQLiteDAO();

        try {
            new PrintReport().showReport(dao.getConn(), ReportType.MY_RESULTS_BY_WORK_HOURS, registeredEmployee.getId());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void showProjectReportAction(ActionEvent actionEvent) {
        dao = new WorkTimeTrackerSQLiteDAO();

        try {
            new PrintReport().showReport(dao.getConn(), ReportType.MY_RESULTS_BY_PROJECT_WORK_HOURS, registeredEmployee.getId());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }
}
