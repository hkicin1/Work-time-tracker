package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
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
        Parent loader = FXMLLoader.load(Main.class.getResource("/fxml/main.fxml"));
        ctrl = new MainController();
        stage.setTitle("Provjera");
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
    public void testKontrolnihTipki (FxRobot robot) {
        Button b = robot.lookup("#btnAbout").queryButton();
        assertNotNull(b);
        Button b1 = robot.lookup("#btnHelp").queryButton();
        assertNotNull(b1);
        Button b2 = robot.lookup("#btnExit").queryButton();
        assertNotNull(b2);
        robot.press(KeyCode.ALT).press(KeyCode.B).release(KeyCode.B).release(KeyCode.ALT);
        assertNotNull(robot.lookup("#hlpAbout").tryQuery().isPresent());
    }

    @Test
    void adminAction(FxRobot robot) throws InterruptedException {
       /* Button b = robot.lookup("#btnLoginAdmin").queryButton();
        robot.clickOn("#btnLoginAdmin");
        robot.lookup("#txtUsername").tryQuery().isPresent();
        robot.write("admin");
        robot.lookup("#pwdPassword").tryQuery().isPresent();
        robot.clickOn("#pwdPassword");
        robot.write("password");
        Button b1 = robot.lookup("#btnLogin").queryButton();
        assertNotNull(b1);
        robot.clickOn("#btnLogin");
        robot.wait(200);

        assertNotNull(robot.lookup("#welcomeAdmin").tryQuery().isPresent());*/

    }

    @Test
    void employeeAction(FxRobot robot) {
       /* Button b = robot.lookup("#btnLoginRadnik").queryButton();
        robot.clickOn("#btnLoginRadnik");
        robot.lookup("#txtUsername").tryQuery().isPresent();
        robot.write("akicin1");
        robot.lookup("#pwdPassword").tryQuery().isPresent();
        robot.clickOn("#pwdPassword");
        robot.write("pass123");
        Button b1 = robot.lookup("#btnLogin").queryButton();
        assertNotNull(b1);
        robot.clickOn("#btnLogin");
        boolean vidljiv = true;
        assertNotNull(robot.lookup("#btnSelectProject").tryQuery().isPresent());*/

    }
}