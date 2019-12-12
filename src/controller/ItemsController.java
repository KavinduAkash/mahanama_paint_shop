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

public class ItemsController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //----------------------
        Parent childpane = null;
        try {
            childpane = FXMLLoader.load(getClass().getResource("../view/Mangeitems.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemmaindisply.getChildren().clear();
        itemmaindisply.getChildren().add(childpane);
        //----------------------
    }

    @FXML
    private AnchorPane ManageColorbtn;


    @FXML
    private AnchorPane itemmaindisply;

    @FXML
    private AnchorPane itemitembtn;

    @FXML
    private AnchorPane itemcatogarybtn;

    @FXML
    void loadItemInItem(MouseEvent event) throws IOException {
        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Mangeitems.fxml"));
        itemmaindisply.getChildren().clear();
        itemmaindisply.getChildren().add(childpane);
    }

    public void goToCatogary(MouseEvent mouseEvent) throws IOException {
        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Catogary.fxml"));
        itemmaindisply.getChildren().clear();
        itemmaindisply.getChildren().add(childpane);
    }

    @FXML
    void goToColors(MouseEvent event) throws IOException {
        Parent childpane = FXMLLoader.load(getClass().getResource("../view/Colors.fxml"));
        itemmaindisply.getChildren().clear();
        itemmaindisply.getChildren().add(childpane);
    }
}