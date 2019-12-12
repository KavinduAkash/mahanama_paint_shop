package controller;

import bo.BOFactory;
import bo.custom.PlaceOrderBO;
import com.jfoenix.controls.JFXButton;
import dto.OrderDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderHandlingController implements Initializable {
    @FXML
    private TableView<OrderDTO> ordertbl;

    @FXML
    private Label orderIdlb;

    @FXML
    private JFXButton orderdeletebtn;

    @FXML
    private Label orderamountlb;

    @FXML
    private Label orderstatelb;

    @FXML
    private Label orderdatelb;

    static PlaceOrderBO bo = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ordertbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        ordertbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        ordertbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("totalprice"));
        ordertbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("state"));


        getAllSkipOrders();
    }

    private void getAllSkipOrders() {
        ArrayList<OrderDTO> allskiporders = null;
        try {
            allskiporders  = bo.getSkipOrders();
            ordertbl.setItems(FXCollections.observableArrayList( allskiporders ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void deleteOrder(ActionEvent event) {
        String id = orderIdlb.getText();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete color");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKOrder(id);
        } else {
            getAllSkipOrders();
        }
    }

    private void deleteOKOrder(String id) {
        try {

            boolean isDeleted = bo.deleteOrder(id);
            if (isDeleted){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete Order");
                alert2.setHeaderText(null);
                alert2.setContentText("Deleted successfully!");
                alert2.showAndWait();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Delete Order");
                alert3.setHeaderText(null);
                alert3.setContentText("Deleting fail!");
                alert3.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        getAllSkipOrders();

    }

    @FXML
    void getSelectedOrder(MouseEvent event) {
        OrderDTO selectvalues = ordertbl.getSelectionModel().selectedItemProperty().get();
        orderIdlb.setText(selectvalues.getOrderId());
        orderdatelb.setText(selectvalues.getOrderdate());
        orderamountlb.setText(String.valueOf(selectvalues.getTotalprice()));
        orderstatelb.setText(selectvalues.getState());
    }

}
