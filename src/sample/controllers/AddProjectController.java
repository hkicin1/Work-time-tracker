package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Project;
import sample.utilities.ProjectDAO;

public class AddProjectController {

    public Button btnEnd;
    public Button btnOk;
    public TextField fldProject;
    public CheckBox cbActivity;

    private Project project = null;
    private ProjectDAO daoProject;

    public AddProjectController(Project project) {
        this.project = project;
    }
    public AddProjectController() {
        daoProject = ProjectDAO.getInst();
    }

    @FXML
    public void initialize() {
        if (project != null) {
            fldProject.setText(project.getName());
        }
    }

    public void okAction(ActionEvent actionEvent) {
        boolean sveOk;
        sveOk = isEmpty(fldProject);

        if (!sveOk){
            project = null;
            return;
        }

        if (project == null) project = new Project();

        project.setName(fldProject.getText());
        if (cbActivity.isSelected()) project.setActivity(1);
        else project.setActivity(0);
        daoProject.addProject(project);
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


    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnEnd.getScene().getWindow();
        stage.close();
    }
}
