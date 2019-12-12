package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import main.Main;

public class DashboardController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/PDashboard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }


    @FXML
    private AnchorPane dashboardtxtpaneDash;

    @FXML
    private AnchorPane dashboardtxtpanePO;

    @FXML
    private AnchorPane dashboardItemnpane;

    @FXML
    private AnchorPane dashboardtxtpaneSP;

    @FXML
    private AnchorPane dashboardtxtpaneMReturns;

    @FXML
    private AnchorPane dashboardtxtSR;

    @FXML
    private AnchorPane dashboardtxtpaneMB;

    @FXML
    private AnchorPane dashboardtxtpaneGR;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    private AnchorPane dashboardtxtpane;

    @FXML
    private AnchorPane dashboardtxtpane1;

    @FXML
    private AnchorPane dashboardtxtpane11;

    @FXML
    private AnchorPane dashboardtxtpane111;

    @FXML
    private AnchorPane dashboardtxtpane1111;

    @FXML
    private AnchorPane dashboardtxtpane12;

    @FXML
    private AnchorPane dashboardtxtpane121;

    @FXML
    private AnchorPane dashboardtxtpane1211;

    @FXML
    private AnchorPane dashboardtxtpane11111;

    @FXML
    private AnchorPane publicpane;

    @FXML
    void goToManageBrand(MouseEvent event) throws IOException {

        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle("-fx-background-color:  #636e72;\n"+
                "                \"    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        //--------------End-When click navibtnSETon---------------------------

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/BrandManage.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);

    }


    @FXML
    void goToPlaceOrder(MouseEvent event) throws IOException {

        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle("-fx-background-color:  #636e72;\n"+
                "                \"    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle(" -fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        //--------------End-When click navibtnSETon---------------------------

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Placeorder.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }

    @FXML
    void goToSalePayment(MouseEvent event) throws IOException {


        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle("-fx-background-color:  #636e72;\n"+
                "                \"    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardtxtpaneMReturns.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        //--------------End-When click navibtnSETon---------------------------

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Salepaymentpane.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }

    @FXML
    void goToManageItem(MouseEvent event) throws IOException {

        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle("-fx-background-color:  #636e72;\n"+
                "                \"    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle(" -fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        //--------------End-When click navibtnSETon---------------------------

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Items.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }

    public void goToManageReturns(MouseEvent mouseEvent) {
        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle(" -fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle(" -fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        //--------------End-When click navibtnSETon---------------------------
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/Returns.fxml"));
            publicpane.getChildren().clear();
            publicpane.getChildren().add(childpane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToPDashboard(MouseEvent event) throws IOException {
        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle("-fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        //--------------End-When click navibtnSETon---------------------------

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/PDashboard.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }

    @FXML
    void goToStockRefill(MouseEvent event) throws IOException {
        //--------------Start-When click navibtnSETon---------------------------
        dashboardtxtpaneDash.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle(" -fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/StockRefill.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }

    @FXML
    void goToGenerateReports(MouseEvent event) throws IOException {
        dashboardtxtpaneDash.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpanePO.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardItemnpane.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneSP.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMReturns.setStyle(" -fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtSR.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneMB.setStyle("-fx-background-color:  #636e72;\n" +
                "    -fx-background-radius: 0 50 50 0;");
        dashboardtxtpaneGR.setStyle("-fx-background-color: #54a0ff;\n" +
                "    -fx-background-radius: 0 50 50 0;\n" +
                "    -fx-pref-width: 180px;");

        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        publicpane.getChildren().clear();
        publicpane.getChildren().add(childpane);
    }

    @FXML
    void logOutAction(ActionEvent event) {
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(childpane);
        Main.stage.setScene(scene);
        Main.stage.centerOnScreen();
        Main.stage.show();
    }

}
