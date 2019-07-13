package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.enums.ContentType;

import java.io.IOException;

public class MainController {


    public Button btnClose;

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void helpAction(ActionEvent actionEvent) {
        showHelpOrAbout("Help", ContentType.HELP);
    }

    public void aboutAction(ActionEvent actionEvent) {
        showHelpOrAbout("About", ContentType.ABOUT);
    }

    private void showHelpOrAbout(String title, ContentType type){
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/helpAbout.fxml"));
            root = loader.load();
            HelpAboutController controller = loader.getController();
            controller.initializeType(type);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
