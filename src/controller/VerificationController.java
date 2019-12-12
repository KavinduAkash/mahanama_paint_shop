package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.SalepaymentpaneController.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.Main;

public class VerificationController implements Initializable {
    @FXML
    private JFXButton conformbtn;

    @FXML
    private JFXPasswordField Passwordtxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void conformVerify(ActionEvent event) {
        String pass = Passwordtxt.getText();
        if (pass.equals("1") || pass.equals("1234")) {
            try {

                Parent root = FXMLLoader.load(this.getClass().getResource("../view/OrderHandling.fxml"));
                Scene scene = new Scene(root);
                SalepaymentpaneController.orderhandlestage.setScene(scene);
                SalepaymentpaneController.orderhandlestage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "You Have Entered Wrong Password. And Try Again !", ButtonType.OK).show();
        }

    }
}
