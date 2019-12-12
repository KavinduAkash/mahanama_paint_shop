package controller;

import bo.BOFactory;
import bo.custom.PlaceOrderBO;
import com.jfoenix.controls.JFXButton;
import dao.custom.QuaryDAO;
import dto.BadgeDTO;
import dto.CustomDTO;
import dto.OrderDetailDTO;
import dto.PlaceOrderDTO;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tableModel.CustomTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PlaceorderController implements Initializable {
    @FXML
    private JFXButton placeorderbtn;

    @FXML
    private TableView<CustomDTO> itemtable;

    @FXML
    private TextField itemIdsearchbar;

    @FXML
    private TextField itemNamesearchbar;

    @FXML
    private TextField itemColorsearchbar;

    @FXML
    private TextField itemidtxt;

    @FXML
    private TextField itemnametxt;

    @FXML
    private TextField qtytxt;

    @FXML
    private TextField unitpricetxt;

    @FXML
    private TextField badgeIdtxt;

    @FXML
    private TextField itemcolortxt;

    @FXML
    private TextField itemmesuretxt;

    @FXML
    private Label hoqlb;

    @FXML
    private Label amountlb;

    @FXML
    private TableView<CustomDTO> badgetable;

    @FXML
    private JFXButton savebtn;

    @FXML
    private TableView<?> orderdetailtable;

    ObservableList<CustomTM> ordrd;

    @FXML
    private JFXButton deletebtn;

    @FXML
    private Label orderidlb;

    @FXML
    private Label nettotallb;


    private double nettot;

    private int index;

    public static Stage s;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("catName"));
        itemtable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brandName"));
        itemtable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemtable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemtable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("colorName"));
        itemtable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mesure"));
        itemtable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("qty"));

        badgetable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        badgetable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("expDate"));
        badgetable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        badgetable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("stockqty"));

        orderdetailtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("index"));
        orderdetailtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        orderdetailtable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        orderdetailtable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("color"));
        orderdetailtable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("mesure"));
        orderdetailtable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        orderdetailtable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        orderdetailtable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("qty"));
        orderdetailtable.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadOrderId();
        qtytxt.setEditable(false);
        loadAllItems();
        nettot = 0;
        index = 0;

    }

    private void loadOrderId() {
        String id = null;
        try {

            id = idgenerator.generateID(idgenerator.IDTypes.ORDER);
            orderidlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static PlaceOrderBO bo = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);


    private void loadAllItems() {
        ArrayList<CustomDTO> allitems = null;
        try {
            allitems = bo.getAllItem();
            itemtable.setItems(FXCollections.observableArrayList(allitems));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void addOrderDetail(ActionEvent event) {

        index++;

        String itemid = itemidtxt.getText();
        if (Pattern.compile("^(ITEM)[0-9.]+$").matcher(itemid).matches()) {

            String itemname = itemnametxt.getText();
            if (Pattern.compile("^[A-z0-9]{1,100}$").matcher(itemname).matches()) {

                String color = itemcolortxt.getText();
                if (Pattern.compile("^[A-z0-9]{1,100}$").matcher(color).matches()) {

                    String mesure = itemmesuretxt.getText();
                    if (Pattern.compile("^[0-9]{1,14}(ml|L|kg|g)$").matcher(mesure).matches()) {

                        int qty = Integer.valueOf(qtytxt.getText());
                        if (Pattern.compile("^[0-9]{1,}$").matcher(String.valueOf(qty)).matches()) {

                            double unitprice = Double.parseDouble(unitpricetxt.getText());
                            if (Pattern.compile("^[0-9.]+$").matcher(String.valueOf(unitprice)).matches()) {

                                double amount = Double.parseDouble(amountlb.getText());

                                String badgeid = badgeIdtxt.getText();
                                if (Pattern.compile("^(BADGE)[0-9]+$").matcher(badgeid).matches()) {

                                    CustomTM customTM = new CustomTM(index,itemid,itemname,color,mesure,badgeid,unitprice,qty,amount);


                                    ordrd = (ObservableList<CustomTM>) orderdetailtable.getItems();

                                    ordrd.add(customTM);

                                    nettot+=amount;

                                    nettotallb.setText(String.valueOf(nettot));

                                }else {
                                    badgeIdtxt.requestFocus();
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Incompatible Badge ID");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Badge ID is not valid..");
                                    alert.showAndWait();
                                }
                                }else{
                                unitpricetxt.requestFocus();
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Incompatible Qty");
                                alert.setHeaderText(null);
                                alert.setContentText("Qty is not valid..");
                                alert.showAndWait();
                            }
                        }else{
                            qtytxt.requestFocus();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Incompatible Qty");
                            alert.setHeaderText(null);
                            alert.setContentText("Qty is not valid..");
                            alert.showAndWait();
                        }
                    }else{
                        itemmesuretxt.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incompatible mesure");
                        alert.setHeaderText(null);
                        alert.setContentText("Mesure is not valid..");
                        alert.showAndWait();
                    }
                 }else{
                    itemcolortxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible color name");
                    alert.setHeaderText(null);
                    alert.setContentText("Color name does not valid..");
                    alert.showAndWait();
                }
        } else {
                itemnametxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Item name");
                alert.setHeaderText(null);
                alert.setContentText("Item name does not valid..");
                alert.showAndWait();
        }
    }else{
        itemidtxt.requestFocus();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incompatible Item ID");
        alert.setHeaderText(null);
        alert.setContentText("Item ID does not valid..");
        alert.showAndWait();
    }
    }

    @FXML
    void deleteOrderDetail(ActionEvent event) {




        //-----------------------------------------------------------------------------------------------
        CustomTM rowDetails = (CustomTM) orderdetailtable.getSelectionModel().selectedItemProperty().get();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete color");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKRow(rowDetails);
        } else {

        }
    }

    private void deleteOKRow(CustomTM rowDetails) {
        int selectindex = rowDetails.getIndex();
        orderdetailtable.getItems().remove(rowDetails);
        double amount=rowDetails.getAmount();
        nettot-=amount;
        nettotallb.setText(String.valueOf(nettot));
        for(int i = 0; i<orderdetailtable.getItems().size();i++){
            CustomTM cus = (CustomTM) orderdetailtable.getItems().get(i);
            System.out.println(i+" - "+cus.getIndex());
        }
        for(int i = selectindex; i<orderdetailtable.getItems().size()+1;i++){
            CustomTM cus = (CustomTM) orderdetailtable.getItems().get(i-1);
            cus.setIndex(i);
        }
        index--;
    }

    @FXML
    void updateOrderDetail(ActionEvent event) {
        CustomTM row = (CustomTM) orderdetailtable.getSelectionModel().selectedItemProperty().get();
        int i = row.getIndex();
        CustomTM rowDetails = (CustomTM) orderdetailtable.getItems().get(i-1);
        rowDetails.setItemId(itemidtxt.getText());
        rowDetails.setItemName(itemnametxt.getText());
        rowDetails.setColor(itemcolortxt.getText());
        rowDetails.setMesure(itemmesuretxt.getText());
        rowDetails.setQty(Integer.valueOf(qtytxt.getText()));
        rowDetails.setUnitPrice(Double.valueOf(unitpricetxt.getText()));
        rowDetails.setAmount(Double.valueOf(amountlb.getText()));
        rowDetails.setBadgeId(badgeIdtxt.getText());
    }

    @FXML
    void getSelectOrderDetails(MouseEvent event) {
        CustomTM rowDetails = (CustomTM) orderdetailtable.getSelectionModel().selectedItemProperty().get();
        itemidtxt.setText(rowDetails.getItemId());
        itemnametxt.setText(rowDetails.getItemName());
        itemcolortxt.setText(rowDetails.getColor());
        itemmesuretxt.setText(rowDetails.getMesure());
        badgeIdtxt.setText(rowDetails.getBadgeId());
        unitpricetxt.setText(String.valueOf(rowDetails.getUnitPrice()));
        amountlb.setText(String.valueOf(rowDetails.getAmount()));
        qtytxt.setText(String.valueOf(rowDetails.getQty()));
    }



    @FXML
    void conformPlaceOrder(ActionEvent event) throws IOException {
        String orderid = orderidlb.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String orderdate = dtf.format(now);
        double nettotal = Double.parseDouble(nettotallb.getText());
        String state = "Skiped";
        ArrayList<OrderDetailDTO> orderdetails = new ArrayList<>();
        for(int i = 0; i<orderdetailtable.getItems().size();i++){
            CustomTM cus = (CustomTM) orderdetailtable.getItems().get(i);
            orderdetails.add(new OrderDetailDTO(orderidlb.getText(),Integer.valueOf(cus.getIndex()),cus.getItemId(),cus.getBadgeId(),Integer.valueOf(cus.getQty()),Double.valueOf(cus.getAmount())));
            System.out.println("come 1.2");
        }
        System.out.println("come 1.3");
        PlaceOrderDTO placeorderdto = new PlaceOrderDTO(orderid,orderdate,nettotal,state,orderdetails);
        try {
            System.out.println("come 1.4");
            boolean isAdded = bo.addOrder(placeorderdto);

            //----------------------------------------
            s = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SalePayment.fxml"));
            Parent root = loader.load();
            SalePaymentController salecon = loader.getController();
            salecon.myfunction1(orderidlb.getText(),nettotallb.getText());
            Scene scene = new Scene(root);
            s.setScene(scene);
            s.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadOrderId();
        orderdetailtable.getItems().clear();
        badgetable.getItems().clear();
        loadAllItems();
        alltxtClear();
        hoqlb.setText("HOQ");
    }

    private void alltxtClear() {
        itemidtxt.setText("");
        itemnametxt.setText("");
        itemcolortxt.setText("");
        itemmesuretxt.setText("");
        qtytxt.setText("");
        unitpricetxt.setText("");
        amountlb.setText("");
    }

    @FXML
    void getItemValue(KeyEvent event) {
        try {
            ArrayList<CustomDTO> searchitem;
            if(itemIdsearchbar.getText().isEmpty()){
                String value1 = itemNamesearchbar.getText();
                value1 = "'"+value1+"%'";
                String value2 = itemColorsearchbar.getText();
                value2 = "'"+value2+"%'";

                searchitem= bo.searchItem(value1,value2);
                itemtable.setItems(FXCollections.observableArrayList(searchitem));

            }else{
                String value = itemIdsearchbar.getText();
                value = "'"+value+"%'";
                searchitem= bo.searchItem(value);
                itemtable.setItems(FXCollections.observableArrayList(searchitem));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSelectItem(MouseEvent event) {
        CustomDTO customDTO = itemtable.getSelectionModel().selectedItemProperty().get();
        itemidtxt.setText(customDTO.getItemId());
        itemnametxt.setText(customDTO.getItemName());
        itemcolortxt.setText(customDTO.getColorName());
        itemmesuretxt.setText(customDTO.getMesure());
        hoqlb.setText(String.valueOf(customDTO.getQty()));

        callCompatibleBadges(customDTO.getItemId());


    }

    private void callCompatibleBadges(String value){
        ArrayList<CustomDTO> badges = null;
        try {

            badges = bo.getCompatibleBadges(value);
            System.out.println(badges);
            badgetable.setItems(FXCollections.observableArrayList(badges));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSelectBadge(MouseEvent event) {
        CustomDTO selectbadge = badgetable.getSelectionModel().selectedItemProperty().get();
        badgeIdtxt.setText(selectbadge.getBadgeId());
        unitpricetxt.setText(String.valueOf(selectbadge.getSalePrice()));
        qtytxt.setEditable(true);
    }

    @FXML
    void enterSaleQty(KeyEvent event) {
        if(qtytxt.getText().isEmpty()){
            amountlb.setText(String.valueOf(0.0));
        }else {
            int qty = Integer.parseInt(qtytxt.getText());
            double unitprice = Double.parseDouble(unitpricetxt.getText());
            double amount = unitprice * qty;
            amountlb.setText(String.valueOf(amount));
        }
    }


}

