package controller;

import bo.BOFactory;
import bo.custom.BrandBO;
import com.jfoenix.controls.JFXButton;
import dto.BrandDTO;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BrandManageController implements Initializable {

    @FXML
    private TextField brandnametxt;

    @FXML
    private TextArea desctxt;

    @FXML
    private TextField countrytxt;

    @FXML
    private TableView<BrandDTO> brandtable;

    @FXML
    private TextField brandsearchbar;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private JFXButton deletebtn;

    @FXML
    private Label brandIdlb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        brandtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brandId"));
        brandtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brandname"));
        brandtable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("country"));
        brandtable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("branddesc"));

        loadAllBrands();
        loadBrandID();
    }

    static BrandBO bo = (BrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRAND);


    //-----loadAll brands-------------------
    private void loadAllBrands() {
        try {

            ArrayList<BrandDTO> allbrands = BrandManageController.getAllBrands();
            brandtable.setItems(FXCollections.observableArrayList(allbrands));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //------loadAll-------
    private static ArrayList<BrandDTO> getAllBrands() throws Exception {
        return bo.getAllBrands();
    }


    //------delete brand--------------------------
    @FXML
    void deleteBrand(ActionEvent event) {
        String brandId = brandIdlb.getText();
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete color");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK) {
            deleteOKBrand(brandId);
        } else {
            loadBrandID();
            loadAllBrands();
        }

    }

    private void deleteOKBrand(String brandId) {
        try {

            boolean isDeleted = BrandManageController.deleteBrand(brandId);
            if (isDeleted){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete Brand");
                alert2.setHeaderText(null);
                alert2.setContentText("Deleted successfully!");
                alert2.showAndWait();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Delete Brand");
                alert3.setHeaderText(null);
                alert3.setContentText("Deleting fail!");
                alert3.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadBrandID();
        loadAllBrands();
    }

    //-----delete------
    private static boolean deleteBrand(String brandId) throws Exception {
        return bo.deleteBrand(brandId);
    }

    //-------save(add)------------------------------------
    @FXML
    void saveBrand(ActionEvent event) {
        String brandId = brandIdlb.getText();

        String brandname = brandnametxt.getText();
        if (Pattern.compile("^[A-z0-9]+$").matcher(brandname).matches()) {
            String country = countrytxt.getText();
            if (Pattern.compile("^[A-z]+$").matcher(brandname).matches()) {
                String branddesc = desctxt.getText();
                if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(branddesc).matches()) {
                    BrandDTO brand = new BrandDTO(brandId,brandname,country,branddesc);

                    try {

                        boolean isAdded = BrandManageController.addBrand(brand);
                        if (isAdded) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Add New Brand");
                            alert.setHeaderText(null);
                            alert.setContentText("Added successfully!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Add New Brand");
                            alert.setHeaderText(null);
                            alert.setContentText("Adding fail!");
                            alert.showAndWait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadAllBrands();
                    loadBrandID();
                }else{
                    countrytxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Brand Description");
                    alert.setHeaderText(null);
                    alert.setContentText("Brand description does not valid..");
                    alert.showAndWait();
                }
            }else{
                countrytxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Country Name");
                alert.setHeaderText(null);
                alert.setContentText("Country name does not valid..");
                alert.showAndWait();
            }
        }else{
            brandnametxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Brand Name");
            alert.setHeaderText(null);
            alert.setContentText("Brand name does not valid..");
            alert.showAndWait();
        }
    }
    //------add----------
    private static boolean addBrand(BrandDTO brand) throws Exception {
        return bo.addBrand(brand);
    }

    @FXML
    void searchBrand(KeyEvent event) {
        String value = brandsearchbar.getText();
        value = "'"+value+"%'";

        try {

            ArrayList<BrandDTO> brandvalue = BrandManageController.searchValue(value);
            brandtable.setItems(FXCollections.observableArrayList(brandvalue));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<BrandDTO> searchValue(String value) throws Exception {
        return bo.getSearchBrands(value);
    }

    //------update brand----------------------------------
    @FXML
    void udateBrand(ActionEvent event) {
        String brandId = brandIdlb.getText();

        String brandname = brandnametxt.getText();
        if (Pattern.compile("^[A-z0-9]+$").matcher(brandname).matches()) {
            String country = countrytxt.getText();
            if (Pattern.compile("^[A-z]+$").matcher(brandname).matches()) {
                String branddesc = desctxt.getText();
                if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(branddesc).matches()) {
                    BrandDTO brand = new BrandDTO(brandId, brandname, country, branddesc);
                    try {

                        boolean isUpdated = BrandManageController.updateBrand(brand);
                        if (isUpdated) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Update Brand");
                            alert.setHeaderText(null);
                            alert.setContentText("Updated successfully!");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Update Brand");
                            alert.setHeaderText(null);
                            alert.setContentText("Updating fail!");
                            alert.showAndWait();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadAllBrands();
                    loadBrandID();
                } else {
                    countrytxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Brand Description");
                    alert.setHeaderText(null);
                    alert.setContentText("Brand description does not valid..");
                    alert.showAndWait();
                }
            } else {
                countrytxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Country Name");
                alert.setHeaderText(null);
                alert.setContentText("Country name does not valid..");
                alert.showAndWait();
            }
        } else {
            brandnametxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Brand Name");
            alert.setHeaderText(null);
            alert.setContentText("Brand name does not valid..");
            alert.showAndWait();
        }
    }
    //-----update-----
    private static boolean updateBrand(BrandDTO brand) throws Exception {
        return bo.updateBrand(brand);
    }

    public void getSelectData(){
        BrandDTO selectItem = brandtable.getSelectionModel().selectedItemProperty().get();
        brandIdlb.setText(selectItem.getBrandId());
        brandnametxt.setText(selectItem.getBrandname());
        countrytxt.setText(selectItem.getCountry());
        desctxt.setText(selectItem.getBranddesc());
    }

    private void loadBrandID() {
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.BRAND);
            brandIdlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        brandnametxt.setText("");
        countrytxt.setText("");
        desctxt.setText("");
    }
}
