package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField usernametxt;

    @FXML
    private JFXPasswordField passwordtxt;

    @FXML

    private JFXButton loginbtn;

    @FXML
    private AnchorPane loginimage;

    @FXML
    private ImageView loginggifviewer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("work..");
        loginggifviewer.setVisible(false);
    }

    public void gotodashboard(MouseEvent mouseEvent) throws IOException {
       callDashboard();
    }
    private void callDashboard() throws IOException {

        String username = usernametxt.getText();
        String password = passwordtxt.getText();

        loginggifviewer.setVisible(true);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(10000000),
                        new KeyValue(loginggifviewer.visibleProperty(), false)));
        timeline.play();

        if (username.equalsIgnoreCase("kavi") && password.equals("123") || username.equalsIgnoreCase("akash") && password.equals("1234")) {
            Parent childpane = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
            Scene scene = new Scene(childpane);
            Main.stage.setScene(scene);
            Main.stage.centerOnScreen();
            Main.stage.show();
        }else{
            new Alert(Alert.AlertType.WARNING, "You Have Entered Wrong User Name Or Password. And Try Again !", ButtonType.OK).show();
        }
    }

    @FXML
    void getLoginActionByEnterKey(KeyEvent event) throws IOException {
        if(event.getCode().equals(KeyCode.ENTER)){
            callDashboard();
        }
    }

    @FXML
    void getFocusPasswordfieldAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            passwordtxt.requestFocus();
        }

    }

    private void loaderload() {
        loginggifviewer.setVisible(true);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(10000000),
                        new KeyValue(loginggifviewer.visibleProperty(), false)));
        timeline.play();
    }
}
