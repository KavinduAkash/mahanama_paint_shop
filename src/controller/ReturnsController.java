package controller;

import bo.BOFactory;
import bo.custom.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.*;
import entity.OrderDetailEntity;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import tableModel.CustomTM;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturnsController implements Initializable {

    @FXML
    private Label returnIdlb;

    @FXML
    private TextField orderIdtxt;

    @FXML
    private Label orderIdfoundlb;

    @FXML
    private TableView<OrderDetailDTO> orderdetailtable;

    @FXML
    private Label orderdatelb;

    @FXML
    private Label itemfoundlb;

    @FXML
    private Label badgefoundlb;

    @FXML
    private Label badgeexpDlb;

    @FXML
    private TextField itemIdtext;

    @FXML
    private TextField badgeIdtxt;

    @FXML
    private TextField returnqtytxt;

    @FXML
    private Label returnvaluelb;

    @FXML
    private Label salepricelb;

    @FXML
    private JFXButton returnconformbtn;

    @FXML
    private TextArea reasontxt;

    @FXML
    private JFXComboBox<String> returnmethodcombo;

    @FXML
    private TableView<ReturnDTO> returntable;




    private double saleprice;

    private int itemcountonorder;


    static PlaceOrderBO placeorderbo = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    static ManageItemBO itembo = (ManageItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    static BadgeBO badgebo = (BadgeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BADGE);
    static ReturnBO bo = (ReturnBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        orderdetailtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderdetailtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("index"));
        orderdetailtable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        orderdetailtable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        orderdetailtable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        orderdetailtable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));

        returntable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusreturnId"));
        returntable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        returntable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        returntable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        returntable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnqty"));
        returntable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("returnamount"));
        returntable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("returndate"));
        returntable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("returnMethod"));

        loadReturnId();
        loadAllReturns();
        returnmethodcombo.getItems().addAll(
                "Return to Shop",
                "Return to Stock Hall"
        );
    }

    private void loadAllReturns() {
        try {

            ArrayList<ReturnDTO> all = bo.getAllReturns();
            returntable.setItems(FXCollections.observableArrayList(all));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadReturnId() {
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.RETURN);
            returnIdlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void orderIdCheckAction(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            orderdetailtable.getItems().clear();
            String orderid = orderIdtxt.getText();
            try {

                ArrayList<String> isAvailable = placeorderbo.isOrderIdAvailble(orderid);
                System.out.println(isAvailable);
                if(orderid.equals(isAvailable.get(0))){
                    orderIdfoundlb.setText("Found..");
                    orderIdfoundlb.setTextFill(Color.web("green"));
                    orderdatelb.setText(isAvailable.get(1));

                    ArrayList<OrderDetailDTO> all = placeorderbo.getAllOrdersDetails(orderid);
                    orderdetailtable.setItems(FXCollections.observableArrayList(all));

                }else{
                    orderIdfoundlb.setText("Not Found..");
                    orderIdfoundlb.setTextFill(Color.web("red"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void getSelectRow(MouseEvent event) {
        OrderDetailDTO rowDetails = (OrderDetailDTO) orderdetailtable.getSelectionModel().selectedItemProperty().get();
        itemIdtext.setText(rowDetails.getItemId());
        itemcountonorder = rowDetails.getQty();
        try {
            if(rowDetails.getItemId().equals(itembo.isItemAvailable(rowDetails.getItemId()))){
                itemfoundlb.setText("Found..");
                itemfoundlb.setTextFill(Color.web("green"));
            }else{
                itemfoundlb.setText("Not Found..");
                itemfoundlb.setTextFill(Color.web("red"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        badgeIdtxt.setText(rowDetails.getBadgeId());
        try {
            ArrayList<BadgeDTO> availableBadge = badgebo.isAvailableBadge(rowDetails.getBadgeId());
            String id = null;
            String expD = null;
            for (BadgeDTO bd : availableBadge) {
                id = bd.getBadgeid();
                expD = bd.getExpdate();
                saleprice = bd.getSaleprice();
            }
            if(rowDetails.getBadgeId().equals(id)){
                badgefoundlb.setText("Found..");
                badgefoundlb.setTextFill(Color.web("green"));
                badgeexpDlb.setText(String.valueOf(expD));
                salepricelb.setText(String.valueOf(saleprice));
            }else{
                badgefoundlb.setText("Not Found..");
                badgefoundlb.setTextFill(Color.web("red"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void getReturnQty(KeyEvent event) {
        int returnqty = Integer.valueOf(returnqtytxt.getText());
        if(returnqty<=itemcountonorder){
            returnvaluelb.setText(String.valueOf(returnqty*saleprice));
        }else{
            returnqtytxt.requestFocus();;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Qty is not compatible");
            alert.setHeaderText(null);
            alert.setContentText("Return qty exceed order qty..");
            alert.showAndWait();
        }
    }

    @FXML
    void returnConformAction(ActionEvent event) {
        String returnid = returnIdlb.getText();
        String orderId  = orderIdtxt.getText();
        String itemId = itemIdtext.getText();
        String badgeId = badgeIdtxt.getText();
        int returnqty = Integer.parseInt(returnqtytxt.getText());
        double returnvalue = Double.parseDouble(returnvaluelb.getText());
        String reason = reasontxt.getText();
        String returnMethod = returnmethodcombo.getValue();
        System.out.println(returnMethod);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        String stockid = null;
        try {

            stockid = idgenerator.generateID(idgenerator.IDTypes.STOCK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(returnMethod == "Return to Shop"){
            StockRefillDTO newstock = new StockRefillDTO(date, stockid, itemId, badgeId, returnqty);
            ReturnDTO dto = new ReturnDTO(returnid,date,orderId,itemId,badgeId,returnqty,returnvalue,reason,returnMethod,newstock);
            try {

                boolean isAdded = bo.makeReturnToShop(dto);
                System.out.println("ok1.2"+isAdded);
                if (isAdded) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Make Return");
                    alert.setHeaderText(null);
                    alert.setContentText("Return to Shop successfully!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Make Return");
                    alert.setHeaderText(null);
                    alert.setContentText("Return to Shop fail!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

        }

    }

}
