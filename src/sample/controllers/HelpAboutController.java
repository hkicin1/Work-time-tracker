package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.enums.ContentType;

import java.net.URL;
import java.util.ResourceBundle;


public class HelpAboutController implements Initializable {
    private ContentType type = null;

    public Label lblHelpAbout;
    public ImageView helpAdminEmployee;
    public Button btnClose1;

    public void initializeType(ContentType type) {
        this.type = type;
        initialize(null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(type == null) return;
        if(type.equals(ContentType.HELP)){
            lblHelpAbout.setVisible(false);
            helpAdminEmployee.setVisible(true);
        }else {
            lblHelpAbout.setText("This application trakes employee work time. \n" +
                    "                 Author: Haris Kičin\n");
        }
    }

    public void initializeText(String text) {
        lblHelpAbout.setText(text);
    }

    public void exitAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose1.getScene().getWindow();
        stage.close();
    }

}
