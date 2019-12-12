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

public class StockRefillController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--------------------------------
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/StockRefillingUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stockmaindisply.getChildren().clear();
        stockmaindisply.getChildren().add(childpane);
        //--------------------------------
    }

    @FXML
    private AnchorPane stockmaindisply;

    @FXML
    private AnchorPane itemmaindisply;

    @FXML
    private AnchorPane stockRefillingbtn;

    @FXML
    private AnchorPane manageBadgebtn;

    @FXML
    void goToManageBadge(MouseEvent event) throws IOException {
        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Badge.fxml"));
        stockmaindisply.getChildren().clear();
        stockmaindisply.getChildren().add(childpane);
    }

    @FXML
    void goTostockRefillingUI(MouseEvent event) throws IOException {
        Parent childpane = FXMLLoader.load(getClass().getResource("../view/StockRefillingUI.fxml"));
        stockmaindisply.getChildren().clear();
        stockmaindisply.getChildren().add(childpane);
    }

}
