package controller;

import bo.BOFactory;
import bo.custom.BadgeBO;
import bo.custom.StockRefillBO;
import com.jfoenix.controls.JFXButton;
import com.sun.istack.internal.NotNull;
import dto.BadgeDTO;
import dto.CustomDTO;
import dto.StockRefillDTO;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class StockRefillingUIController implements Initializable {

    @FXML
    private TableView<CustomDTO> itemtable;

    @FXML
    private TextField itemsearchbar;

    @FXML
    private TableView<BadgeDTO> badgetable;

    @FXML
    private TextField itemidtxt;

    @FXML
    private TextField itemnametxt;

    @FXML
    private TextField colornametxt;

    @FXML
    private TextField mesureidtxt;

    @FXML
    private TextField badgeidtxt;

    @FXML
    private TextField expdatetxt;

    @FXML
    private TextField salepricetxt;

    @FXML
    private TextField buypricetxt;

    @FXML
    private JFXButton addbtn;

    @FXML
    private TextField qtytxt;

    @FXML
    private Label stockidlb;

    @FXML
    private TableView<CustomDTO> stocktable;

    @FXML
    private TextField itemIdsearchbar;

    @FXML
    private TextField itemNamesearchbar;

    @FXML
    private TextField itemColorsearchbar;

    @FXML
    private TextField badgeIdsearchbar;

    @FXML
    private DatePicker expdatesearchpicbar;

    @FXML
    private TextField salepricesearchbar;

    @FXML
    private TextField buypricesearchbar;

    @FXML
    private DatePicker searchadddatedatepicker;

    @FXML
    private TextField stocksearchbar;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private Label stockadddatelb;

    @FXML
    private JFXButton deletebtn;


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

        badgetable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("badgeid"));
        badgetable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("buyprice"));
        badgetable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("expdate"));
        badgetable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("saleprice"));

        stocktable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("addDate"));
        stocktable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("stockId"));
        stocktable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        stocktable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        stocktable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        stocktable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("expDate"));
        stocktable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        stocktable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        stocktable.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("colorName"));
        stocktable.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("mesure"));
        stocktable.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("stockqty"));

        loadAllItems();
    loadAllBadge();
    loadStockId();
    loadAllStock();
}



    static StockRefillBO bo = (StockRefillBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCKREFILL);
    static BadgeBO badgebo = (BadgeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BADGE);


    private void loadAllStock() {
        ArrayList<CustomDTO> allstock = null;
        try {

            allstock = bo.getAllStock();
            stocktable.setItems(FXCollections.observableArrayList(allstock));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //-------add(save)stock---------------------------------------------------------------
    @FXML
    void addStock(ActionEvent event) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);

            String stockid = stockidlb.getText();

            String itemid = itemidtxt.getText();
            if (Pattern.compile("^(ITEM)[0-9.]+$").matcher(itemid).matches()) {
                String badgeid = badgeidtxt.getText();
                if (Pattern.compile("^(BADGE)[0-9]+$").matcher(badgeid).matches()) {
                    String stockqty = qtytxt.getText();
                    if (Pattern.compile("^[0-9]+$").matcher(stockqty).matches()) {
                        try {
                            StockRefillDTO newstock = new StockRefillDTO(date, stockid, itemid, badgeid, Integer.parseInt(stockqty));
                            boolean isAdded = StockRefillingUIController.addNewStock(newstock);
                            if (isAdded) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Add New Stock");
                                alert.setHeaderText(null);
                                alert.setContentText("Added successfully!");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Add New Stock");
                                alert.setHeaderText(null);
                                alert.setContentText("Adding fail!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        loadStockId();
                        loadAllStock();

                    } else {
                        qtytxt.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incompatible Qty");
                        alert.setHeaderText(null);
                        alert.setContentText("Qty is not valid..");
                        alert.showAndWait();
                    }
                } else {
                    badgeidtxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Badge ID");
                    alert.setHeaderText(null);
                    alert.setContentText("Badge ID is not valid..");
                    alert.showAndWait();
                }
            } else {
                itemidtxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Item ID");
                alert.setHeaderText(null);
                alert.setContentText("Item ID does not valid..");
                alert.showAndWait();
            }
        loadAllItems();
        loadAllBadge();

    }
    //----add-----
    private static boolean addNewStock(StockRefillDTO newstock) throws Exception {
        System.out.println("stockadd 4");
        return bo.addStock(newstock);
    }

    //------update stock---------------------------------------------------------------------------------
    @FXML
    void updateStockAction(ActionEvent event) {

        String date = stockadddatelb.getText();

        String stockid = stockidlb.getText();

        String itemid = itemidtxt.getText();
        if (Pattern.compile("^(ITEM)[0-9.]+$").matcher(itemid).matches()) {
            String badgeid = badgeidtxt.getText();
            if (Pattern.compile("^(BADGE)[0-9]+$").matcher(badgeid).matches()) {
                String stockqty = qtytxt.getText();
                if (Pattern.compile("^[0-9]+$").matcher(stockqty).matches()) {
                    try {
                        StockRefillDTO stock = new StockRefillDTO(date, stockid, itemid, badgeid, Integer.parseInt(stockqty));
                        boolean isAdded = StockRefillingUIController.updateStock(stock);
                        if (isAdded) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Update Stock");
                            alert.setHeaderText(null);
                            alert.setContentText("Updated successfully!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Update Stock");
                            alert.setHeaderText(null);
                            alert.setContentText("Updating fail!");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadStockId();
                    loadAllStock();

                } else {
                    qtytxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Qty");
                    alert.setHeaderText(null);
                    alert.setContentText("Qty is not valid..");
                    alert.showAndWait();
                }
            } else {
                badgeidtxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Badge ID");
                alert.setHeaderText(null);
                alert.setContentText("Badge ID is not valid..");
                alert.showAndWait();
            }
        } else {
            itemidtxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Item ID");
            alert.setHeaderText(null);
            alert.setContentText("Item ID does not valid..");
            alert.showAndWait();
        }
        loadAllItems();
        loadAllBadge();
        loadStockId();
        loadAllStock();

    }
    //-----update-----
    private static boolean updateStock(StockRefillDTO stock) throws Exception {
        return bo.updateStock(stock);
    }
    //------delete stock---------------------------------------------------
    @FXML
    void deleteStockAction(ActionEvent event) {
        String stockid = stockidlb.getText();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete color");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKStock(stockid);
        } else {

        }

        loadAllItems();
        loadAllBadge();
        loadStockId();
        loadAllStock();

    }
    private void deleteOKStock(String stockid) {
        try {

            boolean isDeleted = StockRefillingUIController.deleteStock(stockid);
            if(isDeleted){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Stock");
                alert.setHeaderText(null);
                alert.setContentText("Deleted successfully!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete Stock");
                alert.setHeaderText(null);
                alert.setContentText("Deleting fail!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteStock(String stockid) throws Exception {
        return bo.deleteStock(stockid);
    }



    private void loadAllItems() {
        ArrayList<CustomDTO> allitems = null;
        try {
            allitems = bo.getAllItem();
            itemtable.setItems(FXCollections.observableArrayList(allitems));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void loadAllBadge() {
        ArrayList<BadgeDTO> allbadges = null;
        try {
            allbadges = bo.getAllBadge();
            badgetable.setItems(FXCollections.observableArrayList(allbadges));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void getSelectItem(MouseEvent event) {
        CustomDTO cus = itemtable.getSelectionModel().selectedItemProperty().get();
        String itemid = cus.getItemId();
        String itemname = cus.getItemName();
        String colorname = cus.getColorName();
        String mesure = cus.getMesure();

        itemidtxt.setText(itemid);
        itemnametxt.setText(itemname);
        colornametxt.setText(colorname);
        mesureidtxt.setText(mesure);
    }

    @FXML
    void getSelectBadge(MouseEvent event) {

        BadgeDTO badge = badgetable.getSelectionModel().selectedItemProperty().get();
        badgeidtxt.setText(badge.getBadgeid());
        buypricetxt.setText(String.valueOf(badge.getBuyprice()));
        expdatetxt.setText(badge.getExpdate());
        salepricetxt.setText(String.valueOf(badge.getSaleprice()));
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


    private void loadStockId(){
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.STOCK);
            stockidlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        itemidtxt.setText("");
        itemnametxt.setText("");
        colornametxt.setText("");
        mesureidtxt.setText("");
        badgeidtxt.setText("");
        expdatetxt.setText("");
        buypricetxt.setText("");
        salepricetxt.setText("");
        qtytxt.setText("");
    }

    @FXML
    void searchStockAction(ActionEvent event) {
        String adddate = String.valueOf(searchadddatedatepicker.getValue());
        if(adddate.equals("null")){
            adddate = "'%'";
        }else{
            adddate = "'"+adddate+"%'";
        }

        String anyid = stocksearchbar.getText();
        anyid = "'"+anyid+"%'";

        ArrayList<CustomDTO> allSearchStock = null;
        try {

            allSearchStock = bo.getSearchStock(adddate,anyid);
            stocktable.setItems(FXCollections.observableArrayList(allSearchStock));//000ioiiopiopiopuioy

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void searchStockAction2(KeyEvent event) {
        String adddate = String.valueOf(searchadddatedatepicker.getValue());
        System.out.println("add date 1 : "+adddate);
        if(adddate.equals("null")){
            adddate = "'%'";
        }else{
            adddate = "'"+adddate+"%'";
        }
        System.out.println("add date : "+adddate);

        String anyid = stocksearchbar.getText();
        anyid = "'"+anyid+"%'";

        ArrayList<CustomDTO> allSearchStock = null;
        try {

            allSearchStock = bo.getSearchStock(adddate,anyid);
            stocktable.setItems(FXCollections.observableArrayList(allSearchStock));//000ioiiopiopiopuioy

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //---------------------------------------------------------------------------------------------------------
    @FXML
    void searchBadge(KeyEvent event) {
        System.out.println("come into badge search1");
        if(badgeIdsearchbar.getText().isEmpty()&buypricesearchbar.getText().isEmpty()&null==String.valueOf(expdatesearchpicbar.getValue())&salepricesearchbar.getText().isEmpty()){
            loadAllBadge();
        }
        try {
            if(badgeIdsearchbar.getText().isEmpty()){
                String buyprice = buypricesearchbar.getText();
                buyprice = "'"+buyprice+"%'";
                System.out.println("buy like"+buyprice);
                String saleprice = salepricesearchbar.getText();
                saleprice = "'"+saleprice+"%'";
                System.out.println("sale like"+saleprice);
                String expdate= String.valueOf(expdatesearchpicbar.getValue());

                if(expdate.equals("null")){
                    expdate = "'%'";
                }else{
                    expdate = "'"+expdate+"%'";
                }


                System.out.println("expdate :"+expdate);
                System.out.println("come into badge search2");
                ArrayList<BadgeDTO> searchbadge =badgebo.getSearchBadge(buyprice,expdate,saleprice);
                System.out.println("come into badge search3");
                System.out.println(searchbadge);
                for (BadgeDTO bd : searchbadge) {
                    System.out.println(bd.getBadgeid());
                }
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

            }else{
                String badgeId = badgeIdsearchbar.getText();
                badgeId = "'"+badgeId+"%'";

                ArrayList<BadgeDTO> searchbadge = badgebo.getSearchBadge(badgeId);
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchByExpDateAction(ActionEvent event) {
        if(badgeIdsearchbar.getText().isEmpty()&buypricesearchbar.getText().isEmpty()&null==String.valueOf(expdatesearchpicbar.getValue())&salepricesearchbar.getText().isEmpty()){
            loadAllBadge();
        }
        try {
            if(badgeIdsearchbar.getText().isEmpty()){
                String buyprice = buypricesearchbar.getText();
                buyprice = "'"+buyprice+"%'";
                System.out.println("buy like"+buyprice);
                String saleprice = salepricesearchbar.getText();
                saleprice = "'"+saleprice+"%'";
                System.out.println("sale like"+saleprice);
                String expdate= String.valueOf(expdatesearchpicbar.getValue());

                if(expdate.equals("null")){
                    expdate = "'%'";
                }else{
                    expdate = "'"+expdate+"%'";
                }


                System.out.println("expdate :"+expdate);
                System.out.println("come into badge search2");
                ArrayList<BadgeDTO> searchbadge =badgebo.getSearchBadge(buyprice,expdate,saleprice);
                System.out.println("come into badge search3");
                System.out.println(searchbadge);
                for (BadgeDTO bd : searchbadge) {
                    System.out.println(bd.getBadgeid());
                }
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

            }else{
                String badgeId = badgeIdsearchbar.getText();
                badgeId = "'"+badgeId+"%'";

                ArrayList<BadgeDTO> searchbadge = badgebo.getSearchBadge(badgeId);
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getSelectStock(MouseEvent event) {
        CustomDTO customDTO = stocktable.getSelectionModel().selectedItemProperty().get();
        stockadddatelb.setText(customDTO.getAddDate());
        stockidlb.setText(customDTO.getStockId());
        itemidtxt.setText(customDTO.getItemId());
        itemnametxt.setText(customDTO.getItemName());
        colornametxt.setText(customDTO.getColorName());
        mesureidtxt.setText(customDTO.getMesure());
        badgeidtxt.setText(customDTO.getBadgeId());
        expdatetxt.setText(customDTO.getExpDate());
        buypricetxt.setText(String.valueOf(customDTO.getBuyPrice()));
        salepricetxt.setText(String.valueOf(customDTO.getSalePrice()));
        qtytxt.setText(String.valueOf(customDTO.getQty()));
    }
}
