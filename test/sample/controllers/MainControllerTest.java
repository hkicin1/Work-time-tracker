package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import sample.Main;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MainControllerTest {
    Stage theStage;
    MainController ctrl;

    @Start
    public void start (Stage stage) throws Exception {
        /*Parent mainNode = FXMLLoader.load(Main.class.getResource("prijava.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();*/
        Parent loader = FXMLLoader.load(Main.class.getResource("/fxml/main.fxml"));
        //ctrl = new MainController();
        //loader.setController(ctrl);
        //Parent root = loader.load();
        stage.setTitle("");
        stage.setScene(new Scene(loader, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }


    @Test
    public void testPoljaPostoje(FxRobot robot) {
        Button b = robot.lookup("#btnLoginAdmin").queryButton();
        assertNotNull(b);
        Button b2 = robot.lookup("#btnLoginRadnik").queryButton();
        assertNotNull(b2);
    }

    @Test
    void adminAction(FxRobot robot) {
        Button b = robot.lookup("#btnLoginAdmin").queryButton();
        robot.clickOn("#btnLoginAdmin");
        robot.lookup("#txtUsername").tryQuery().isPresent();
        robot.write("admin");
        robot.lookup("#pwdPassword").tryQuery().isPresent();
        robot.clickOn("#pwdPassword");
        robot.write("password");
        Button b1 = robot.lookup("#btnLogin").queryButton();
        assertNotNull(b1);
        robot.clickOn("#btnLogin");
        /*boolean vidljiv = true;
        if (robot.lookup("#manageEmployees").query().isVisible()) assertTrue(vidljiv);
        else assertFalse(vidljiv);*/
    }

    @Test
    void employeeAction(FxRobot robot) {
        Button b = robot.lookup("#btnLoginRadnik").queryButton();
        robot.clickOn("#btnLoginRadnik");
        robot.lookup("#txtUsername").tryQuery().isPresent();
        robot.write("akicin1");
        robot.lookup("#pwdPassword").tryQuery().isPresent();
        robot.clickOn("#pwdPassword");
        robot.write("pass123");
        Button b1 = robot.lookup("#btnLogin").queryButton();
        assertNotNull(b1);
        robot.clickOn("#btnLogin");
        /*boolean vidljiv = true;
        if (robot.lookup("#btnSelectProject").query().isVisible()) assertTrue(vidljiv);
        else assertFalse(vidljiv);*/
    }
}