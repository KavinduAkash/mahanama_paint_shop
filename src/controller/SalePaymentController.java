package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.PlaceorderController.*;

public class SalePaymentController implements Initializable {

    @FXML
    private AnchorPane publicpanepay;

    @FXML
    private Hyperlink skippayhyper;

    private String orderid;

    private double nettot;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void myfunction1(String id,String nettot){
        this.orderid = id;
        this.nettot = Double.parseDouble(nettot);
        System.out.println("start");
        loadPaymentPane();
    }


    private void loadPaymentPane(){
        Parent subroot = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Salepaymentpane.fxml"));
            subroot = loader.load();
            SalepaymentpaneController paymentcontrollerpane = loader.getController();
            paymentcontrollerpane.getValueFunction(orderid,nettot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        publicpanepay.getChildren().add(subroot);
        System.out.println("End");
    }
    @FXML
    void skipPayment(ActionEvent event) {
        PlaceorderController.s.close();
    }


}


