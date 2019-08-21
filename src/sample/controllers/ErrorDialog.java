package sample.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorDialog {
	
	public ErrorDialog(String message) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setContentText(message);
    	alert.showAndWait();
	}
}
