package controller;

import bo.BOFactory;
import bo.custom.BadgeBO;
import com.jfoenix.controls.JFXButton;
import dto.BadgeDTO;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BadgeController implements Initializable {

    @FXML
    private TextField badgeIdsearchbar;

    @FXML
    private TextField badgebuypricetxt;

    @FXML
    private TextField badgesalepricetxt;

    @FXML
    private TableView<BadgeDTO> badgetable;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private JFXButton deletebtn;

    @FXML
    private TextField buypricesearchbar;

    @FXML
    private DatePicker expdatetxt;

    @FXML
    private Label badgeidlb;

    @FXML
    private TextField salepricesearchbar;

    @FXML
    private DatePicker expdatesearchpicbar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        badgetable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("badgeid"));
        badgetable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("buyprice"));
        badgetable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("expdate"));
        badgetable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("saleprice"));

        loadAllBadge();
        loadBadgesId();
    }


    static BadgeBO bo = (BadgeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BADGE);

    //----------save(add) badge-------------------------------------------------
    @FXML
    void addBadge(ActionEvent event) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());

        String badgeid = badgeidlb.getText();
        double buyprice = Double.parseDouble(badgebuypricetxt.getText());
        if (Pattern.compile("^[0-9.]+$").matcher(String.valueOf(buyprice)).matches()) {
            String expdate = String.valueOf(expdatetxt.getValue());
            LocalDate expirationDate = LocalDate.parse(expdate);
            if (today.isBefore(expirationDate)) {
            double saleprice = Double.parseDouble(badgesalepricetxt.getText());
                if (Pattern.compile("^[0-9.]+$").matcher(String.valueOf(saleprice)).matches()) {
                    BadgeDTO badgeDTO = new BadgeDTO(badgeid, buyprice, expdate, saleprice);

                    try {

                        boolean isAdded = BadgeController.addBadge(badgeDTO);
                        if(isAdded){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Add new Badge");
                            alert.setHeaderText(null);
                            alert.setContentText("Added successfully!");
                            alert.showAndWait();
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Add new Badge");
                            alert.setHeaderText(null);
                            alert.setContentText("Adding Fail!");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadAllBadge();
                    loadBadgesId();
                }else{
                    badgebuypricetxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Salling Price");
                    alert.setHeaderText(null);
                    alert.setContentText("Salling price is not valid..");
                    alert.showAndWait();
                }
            }else{
                badgebuypricetxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Expire Date");
                alert.setHeaderText(null);
                alert.setContentText("Expire date is not valid..");
                alert.showAndWait();
            }

        }else{
            badgebuypricetxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Buying Price");
            alert.setHeaderText(null);
            alert.setContentText("Buying price is not valid..");
            alert.showAndWait();
        }
    }
    //-------add---------
    private static boolean addBadge(BadgeDTO badgeDTO) throws Exception {
        System.out.println("p3");
        return bo.addBadge(badgeDTO);
    }

    //----------delete badge------------------------------------------------
    @FXML
    void deleteBadge(ActionEvent event) {
        String badgeid = badgeidlb.getText();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete Badge");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKBadge(badgeid);
        } else {
            loadBadgesId();
            loadAllBadge();
        }
    }

    private void deleteOKBadge(String badgeid) {
        try {

            boolean isDeleted = BadgeController.deleteBadge(badgeid);
            if (isDeleted){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete Badge");
                alert2.setHeaderText(null);
                alert2.setContentText("Deleted successfully!");
                alert2.showAndWait();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Delete Badge");
                alert3.setHeaderText(null);
                alert3.setContentText("Deleting fail!");
                alert3.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadAllBadge();
        loadBadgesId();
    }

    //------delete--------
    private static boolean deleteBadge(String badgeid) throws Exception {
        return bo.deleteBadge(badgeid);
    }

    @FXML
    void savebadge(MouseEvent event) {

    }

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
                ArrayList<BadgeDTO> searchbadge =bo.getSearchBadge(buyprice,expdate,saleprice);
            System.out.println("come into badge search3");
            System.out.println(searchbadge);
            for (BadgeDTO bd : searchbadge) {
                System.out.println(bd.getBadgeid());
            }
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

        }else{
            String badgeId = badgeIdsearchbar.getText();
            badgeId = "'"+badgeId+"%'";

                ArrayList<BadgeDTO> searchbadge = bo.getSearchBadge(badgeId);
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
                ArrayList<BadgeDTO> searchbadge =bo.getSearchBadge(buyprice,expdate,saleprice);
                System.out.println("come into badge search3");
                System.out.println(searchbadge);
                for (BadgeDTO bd : searchbadge) {
                    System.out.println(bd.getBadgeid());
                }
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

            }else{
                String badgeId = badgeIdsearchbar.getText();
                badgeId = "'"+badgeId+"%'";

                ArrayList<BadgeDTO> searchbadge = bo.getSearchBadge(badgeId);
                badgetable.setItems(FXCollections.observableArrayList(searchbadge));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------update badge------------------------------------------------
    @FXML
    void updateBadge(ActionEvent event) {
        String badgeid = badgeidlb.getText();
        double buyprice = Double.parseDouble(badgebuypricetxt.getText());
        if (Pattern.compile("^[0-9.]+$").matcher(String.valueOf(buyprice)).matches()) {
            String expdate = String.valueOf(expdatetxt.getValue());
            if (Pattern.compile("^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$").matcher(expdate).matches()) {
                double saleprice = Double.parseDouble(badgesalepricetxt.getText());
                if (Pattern.compile("^[0-9.]+$").matcher(String.valueOf(saleprice)).matches()) {
                    BadgeDTO badgeDTO = new BadgeDTO(badgeid, buyprice, expdate, saleprice);

                    try {

                        boolean isUpdated = BadgeController.updateBadge(badgeDTO);
                        if (isUpdated) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Update Badge");
                            alert.setHeaderText(null);
                            alert.setContentText("Updated successfully!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Update Badge");
                            alert.setHeaderText(null);
                            alert.setContentText("Updating fail!");
                            alert.showAndWait();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadAllBadge();
                    loadBadgesId();
                }else{
                    badgebuypricetxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Salling Price");
                    alert.setHeaderText(null);
                    alert.setContentText("Salling price is not valid..");
                    alert.showAndWait();
                }
            }else{
                badgebuypricetxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Expire Date");
                alert.setHeaderText(null);
                alert.setContentText("Expire date is not valid..");
                alert.showAndWait();
            }

        }else{
            badgebuypricetxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Buying Price");
            alert.setHeaderText(null);
            alert.setContentText("Buying price is not valid..");
            alert.showAndWait();
        }
    }
    //------update-------
    private static boolean updateBadge(BadgeDTO badgeDTO) throws Exception {
        return bo.updateBadge(badgeDTO);
    }


    private void loadAllBadge() {
        try {

            ArrayList<BadgeDTO> allbadges = bo.getAllbadge();
            badgetable.setItems(FXCollections.observableArrayList(allbadges));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void selectBadge(MouseEvent event) {
        BadgeDTO selectItem = badgetable.getSelectionModel().selectedItemProperty().get();
        badgeidlb.setText(selectItem.getBadgeid());
        badgebuypricetxt.setText(Double.toString(selectItem.getBuyprice()));
        expdatetxt.setValue(LocalDate.parse(selectItem.getExpdate()));
        badgesalepricetxt.setText(Double.toString(selectItem.getSaleprice()));
    }


    private void loadBadgesId(){
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.BADGE);
            badgeidlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        badgebuypricetxt.setText("");
        expdatetxt.setValue(LocalDate.of(2016,5,7));
        badgesalepricetxt.setText("");
    }
}