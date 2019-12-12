package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private AnchorPane itemreturnbtn;

    @FXML
    private AnchorPane stockreportbtn;

    @FXML
    private AnchorPane returnreturnsbtn;

    @FXML
    private AnchorPane panereportdisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemReportloader();
    }

    private void itemReportloader(){
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/ItemReports.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panereportdisplay.getChildren().clear();
        panereportdisplay.getChildren().add(childpane);
    }


    @FXML
    void goToItemReports(MouseEvent event) {
        itemReportloader();
    }

    @FXML
    void goToStockReports(MouseEvent event) {
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/StockReports.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panereportdisplay.getChildren().clear();
        panereportdisplay.getChildren().add(childpane);
    }

    @FXML
    void goToReturnReports(MouseEvent event) {
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/ReturnReports.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panereportdisplay.getChildren().clear();
        panereportdisplay.getChildren().add(childpane);
    }

}
