package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.models.StopWatch;
import sample.utilities.WorkTimeTrackerSQLiteDAO;

//TODO: projektno vrijeme.. kako prenijeti projekat i za njega pratiti vrijeme

public class StartProjectTimeController {

    public Label currentProjectLabel;
    public ToggleButton mainButton;
    public ToggleButton breakButton;
    public Label millsLabel;
    public Label secondsLabel;
    public Label millsLabelLunch;
    public Label secondsLabelLunch;
    public WorkTimeTrackerSQLiteDAO dao;

    private double xOffset = 0;
    private double yOffset = 0;
    private StopWatch stopWatch = new StopWatch();
    private Color glowingRed = Color.web("ff2800");
    private Color glowingGreen = Color.web("00bf0f");
    private DropShadow dropShadow = new DropShadow(10.81, glowingRed);
    private InnerShadow innerShadow = new InnerShadow(20.58, glowingRed);
    public ProgressIndicator progressIndicator;
    public ProgressIndicator progressIndicatorLunch;

    @FXML
    public void initialize() {
//        currentProjectLabel.setText(controller.getSelectedProject().getName());
    }

    public void startAction(ActionEvent actionEvent) {
        if (mainButton.getText().equals("Start")) {
            stopWatch.run(secondsLabel, millsLabel);
            mainButton.setText("Stop");
            makeButtonRed(mainButton, dropShadow, innerShadow);
            progressIndicator.setVisible(true);
        } else if (mainButton.getText().equals("Stop")) {
            stopWatch.stop();
            mainButton.setText("Start");
            makeButtonGreen(mainButton, dropShadow, innerShadow);
            progressIndicator.setVisible(false);
        }
    }
    public void lunchAction(ActionEvent actionEvent) {
        if (breakButton.getText().equals("Lunch")) {
            stopWatch.run(secondsLabelLunch, millsLabelLunch);
            breakButton.setText("Stop");
            makeButtonRed(breakButton, dropShadow, innerShadow);
            progressIndicatorLunch.setVisible(true);
        } else if (breakButton.getText().equals("Stop")) {
            stopWatch.stop();
            breakButton.setText("Lunch");
            makeButtonGreen(breakButton, dropShadow, innerShadow);
            progressIndicatorLunch.setVisible(false);
        }
    }

    private void makeButtonRed(ToggleButton button, DropShadow dropShadow, InnerShadow innerShadow) {
        button.setStyle("-fx-border-color: #ff2800;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;");
        dropShadow.setColor(glowingRed);
        innerShadow.setColor(glowingRed);
        innerShadow.setInput(dropShadow);
        button.setEffect(innerShadow);
    }

    private void makeButtonGreen(ToggleButton button, DropShadow dropShadow, InnerShadow innerShadow) {
        button.setStyle("-fx-border-color: #00bf0f;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;");
        dropShadow.setColor(glowingGreen);
        innerShadow.setColor(glowingGreen);
        innerShadow.setInput(dropShadow);
        button.setEffect(innerShadow);
    }


    private void makeDraggable(Stage stage, Parent parent) {
        parent.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {
            stageOf(stage, parent).setX(event.getScreenX() - xOffset);
            stageOf(stage, parent).setY(event.getScreenY() - yOffset);
        });
    }

    private Stage stageOf(Stage stage, Parent parent) {
        stage = (Stage) parent.getScene().getWindow();
        return stage;
    }
}
