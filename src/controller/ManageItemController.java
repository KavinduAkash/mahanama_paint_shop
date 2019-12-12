package controller;

import bo.BOFactory;
import bo.custom.ManageItemBO;
import com.jfoenix.controls.JFXButton;
import dao.custom.ItemDAO;
import dto.*;
import entity.CustomEntity;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageItemController implements Initializable {

    @FXML
    private TableView<CatDTO> cattable;

    @FXML
    private TableView<BrandDTO> brandtable;

    @FXML
    private TableView<ColorDTO> colortable;

    @FXML
    private TextField itemnmetxt;

    @FXML
    private TextArea itemdesctxt;

    @FXML
    private TextField catIdtxt;

    @FXML
    private TextField brandIdtxt;

    @FXML
    private Label itemIdlb;

    @FXML
    private TextField colortxt;

    @FXML
    private TextField catsearchbar;

    @FXML
    private TextField brandsearchbar;

    @FXML
    private TextField colorsearchbar;

    @FXML
    private Label whatcatlb;

    @FXML
    private Label whatbrandlb;

    @FXML
    private Label whatcolorlb;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private JFXButton deletebtn;

    @FXML
    private TextField mesuretxt;

    @FXML
    private TableView<CustomDTO> itemtable;

    @FXML
    private ImageView resetbtn;

    @FXML
    private TextField itemIdsearchbar;

    @FXML
    private TextField itemNamesearchbar;

    @FXML
    private TextField itemColorsearchbar;

    @FXML
    private JFXButton Lbtn;

    @FXML
    private JFXButton mlbtn;

    @FXML
    private JFXButton kgbtn;

    @FXML
    private JFXButton gbtn;





    static ManageItemBO bo = (ManageItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cattable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("catId"));
        cattable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("catname"));

        brandtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brandId"));
        brandtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brandname"));

        colortable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("colorId"));
        colortable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("colorname"));

        //------------------------------------------------------------------------------------------
        itemtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("catName"));
        itemtable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brandName"));
        itemtable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemtable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemtable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("colorName"));
        itemtable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mesure"));
        itemtable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadItemId();
        loadAllItem();
        loadallcatogary();
        loadallBrand();
        loadallColor();
    }
//-----------------------------------correct-----------------------------------------------------------------------------------------------
    private void loadAllItem() {
        try {
            ArrayList<CustomDTO> all = bo.getAllItem();
            itemtable.setItems(FXCollections.observableArrayList(all));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //--------------------------------------------------------------------------------------

    //----------save(add)-------------------------------------------------------------------
    @FXML
    void addItem(ActionEvent event) {
        String itemid = itemIdlb.getText();

        String catid = catIdtxt.getText();
        if (Pattern.compile("^(CAT)[0-9]{3,}$").matcher(catid).matches()) {
            String brandid = brandIdtxt.getText();
            if (Pattern.compile("^(BRAN)[0-9]{3,}$").matcher(brandid).matches()) {
                String itemname = itemnmetxt.getText();
                if (Pattern.compile("^[A-z0-9]+$").matcher(itemname).matches()) {
                    String itemdesc = itemdesctxt.getText();
                    if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(itemdesc).matches()) {
                        String colorid = colortxt.getText();
                        if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(itemdesc).matches()) {
                            String mesure = mesuretxt.getText();
                            System.out.println("it is here");
                            if (Pattern.compile("^(?=.*\\d)(?=.*[1-9]).{1,10}(L|ml|kg|g)$").matcher(mesure).matches()) {
                                int handonqty = 0;
                                ItemDTO itemdto = new ItemDTO(itemid,catid,brandid,itemname,itemdesc,colorid,mesure,handonqty);

                                try {

                                    boolean isAdded = ManageItemController.addItem(itemdto);

                                    if(isAdded){
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Add New Item");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Added successfully!");
                                        alert.showAndWait();
                                    }else{
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Add New Item");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Adding fail!");
                                        alert.showAndWait();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                loadAllItem();
                                loadItemId();
                            }else{
                                mesuretxt.requestFocus();
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Incompatible mesure value");
                                alert.setHeaderText(null);
                                alert.setContentText("Mesure value does not valid..");
                                alert.showAndWait();
                            }
                        } else {
                            itemdesctxt.requestFocus();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Incompatible Item Description");
                            alert.setHeaderText(null);
                            alert.setContentText("Item Description does not valid..");
                            alert.showAndWait();
                        }
                    } else {
                        itemdesctxt.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incompatible Item Description");
                        alert.setHeaderText(null);
                        alert.setContentText("Item Description does not valid..");
                        alert.showAndWait();
                    }
                } else {
                    itemnmetxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Item Name");
                    alert.setHeaderText(null);
                    alert.setContentText("Item name does not valid..");
                    alert.showAndWait();
                }
            }else {
                brandIdtxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible brand ID");
                alert.setHeaderText(null);
                alert.setContentText("Brand ID does not valid..");
                alert.showAndWait();
            }
        }else{
            catIdtxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Catogary ID");
            alert.setHeaderText(null);
            alert.setContentText("Catogary ID does not valid..");
            alert.showAndWait();
        }
    }

    //------add-------
    private static boolean addItem(ItemDTO itemdto) throws Exception {
      return bo.addItem(itemdto);
    }

    //-------delete item------------------------------------------------------------------------------
    @FXML
    void deleteItem(ActionEvent event) {
        String itemid = itemIdlb.getText();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete Item");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKItem(itemid);
        } else {
            loadItemId();
            loadAllItem();
        }

    }
    private void deleteOKItem(String itemid){
        try {

            boolean isDeleted = ManageItemController.deleteItem(itemid);
            if(isDeleted){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete Item");
                alert2.setHeaderText(null);
                alert2.setContentText("Deleted successfully!");
                alert2.showAndWait();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Delete Item");
                alert3.setHeaderText(null);
                alert3.setContentText("Deleting fail!");
                alert3.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadItemId();
        loadAllItem();
    }

    //-----delete------
    private static boolean deleteItem(String itemid) throws Exception {
        return bo.deleteItem(itemid);
    }

    //---------update item---------------------------------------------------------------------------
    @FXML
    void updateItem(ActionEvent event) {

        String itemid = itemIdlb.getText();

        String catid = catIdtxt.getText();
        if (Pattern.compile("^(CAT)[0-9]{3,}$").matcher(catid).matches()) {
            String brandid = brandIdtxt.getText();
            if (Pattern.compile("^(BRAN)[0-9]{3,}$").matcher(brandid).matches()) {
                String itemname = itemnmetxt.getText();
                if (Pattern.compile("^[A-z0-9]{1,200}$").matcher(itemname).matches()) {
                    String itemdesc = itemdesctxt.getText();
                    if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(itemdesc).matches()) {
                        String colorid = colortxt.getText();
                        if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(itemdesc).matches()) {
                            String mesure = mesuretxt.getText();
                            if (Pattern.compile("^(?=.*\\d)(?=.*[1-9]).{1,10}(L|ml|kg|g)$").matcher(mesure).matches()) {
                                int handonqty = 0;

                                    ItemDTO itemdto = new ItemDTO(itemid,catid,brandid,itemname,itemdesc,colorid,mesure,handonqty);

                                    try {

                                        boolean isUpdated = ManageItemController.updateItem(itemdto);
                                        if (isUpdated) {
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Update Item");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Updated successfully!");
                                            alert.showAndWait();
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR);
                                            alert.setTitle("Update Item");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Updating fail!");
                                            alert.showAndWait();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    loadItemId();
                                    loadAllItem();
                            }else{
                                mesuretxt.requestFocus();
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Incompatible mesure value");
                                alert.setHeaderText(null);
                                alert.setContentText("Mesure value does not valid..");
                                alert.showAndWait();
                            }
                        } else {
                            itemdesctxt.requestFocus();
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Incompatible Item Description");
                            alert.setHeaderText(null);
                            alert.setContentText("Item Description does not valid..");
                            alert.showAndWait();
                        }
                    } else {
                        itemdesctxt.requestFocus();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Incompatible Item Description");
                        alert.setHeaderText(null);
                        alert.setContentText("Item Description does not valid..");
                        alert.showAndWait();
                    }
                } else {
                    itemnmetxt.requestFocus();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Incompatible Item Name");
                    alert.setHeaderText(null);
                    alert.setContentText("Item name does not valid..");
                    alert.showAndWait();
                }
            }else {
                brandIdtxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible brand ID");
                alert.setHeaderText(null);
                alert.setContentText("Brand ID does not valid..");
                alert.showAndWait();
            }
        }else{
            catIdtxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Catogary ID");
            alert.setHeaderText(null);
            alert.setContentText("Catogary ID does not valid..");
            alert.showAndWait();
        }

    }

    //-----update-------
    private static boolean updateItem(ItemDTO itemdto) throws Exception {
        return bo.updateItem(itemdto);
    }

    @FXML
    void selectItem(MouseEvent event) {
        CustomDTO customDTO = itemtable.getSelectionModel().selectedItemProperty().get();
        CustomDTO select = null;
        try {

            select = bo.getselectItem(customDTO.getItemId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        itemIdlb.setText(select.getItemId());
        catIdtxt.setText(select.getCatId());
        whatcatlb.setText(select.getCatName());
        System.out.println(select.getCatName());
        brandIdtxt.setText(select.getBrandId());
        whatbrandlb.setText(select.getBrandName());
        itemnmetxt.setText(select.getItemName());
        itemdesctxt.setText(select.getItemDescription());
        colortxt.setText(select.getColorId());
        whatcolorlb.setText(select.getColorName());
        mesuretxt.setText(select.getMesure());
    }
    //---------------------------------------------------------------------------------------
    @FXML
    void searchCat(KeyEvent event) {
        String searchcat = catsearchbar.getText();
        searchcat = "'"+searchcat+"%'";
        try {

            ArrayList<CatDTO> search = bo.getSearchCat(searchcat);
            cattable.setItems(FXCollections.observableArrayList(search));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchBrand(KeyEvent event) {
        String searchbrand = brandsearchbar.getText();
        searchbrand = "'"+searchbrand+"%'";
        try {

            ArrayList<BrandDTO> search = bo.getSearchBrand(searchbrand);
            brandtable.setItems(FXCollections.observableArrayList(search));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchColor(KeyEvent event) {
        String searchcolor = colorsearchbar.getText();
        searchcolor = "'"+searchcolor+"%'";
        try {

            ArrayList<ColorDTO> search = bo.getSearchColor(searchcolor);
            colortable.setItems(FXCollections.observableArrayList(search));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadallcatogary(){
        ArrayList<CatDTO> allcat = null;
        try {
            allcat = bo.getAllCat();
            cattable.setItems(FXCollections.observableArrayList(allcat));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadallBrand(){
        ArrayList<BrandDTO> allbrand = null;
        try {
            allbrand = bo.getAllBrand();
            brandtable.setItems(FXCollections.observableArrayList(allbrand));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadallColor(){
        ArrayList<ColorDTO> allcolor = null;
        try {
            allcolor = bo.getAllColor();
            colortable.setItems(FXCollections.observableArrayList(allcolor));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void selectCat(MouseEvent event) {
        CatDTO selectItem = cattable.getSelectionModel().selectedItemProperty().get();
        catIdtxt.setText(selectItem.getCatId());
        whatcatlb.setText(selectItem.getCatname());
    }

    @FXML
    void selectBrand(MouseEvent event) {
        BrandDTO selectItem = brandtable.getSelectionModel().selectedItemProperty().get();
        brandIdtxt.setText(selectItem.getBrandId());
        whatbrandlb.setText(selectItem.getBrandname());
    }

    @FXML
    void selectColor(MouseEvent event) {
        ColorDTO selectItem = colortable.getSelectionModel().selectedItemProperty().get();
        colortxt.setText(selectItem.getColorId());
        whatcolorlb.setText(selectItem.getColorname());
    }



    private void loadItemId(){
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.ITEM);
            itemIdlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        brandIdtxt.setText("");
        catIdtxt.setText("");
        itemnmetxt.setText("");
        itemdesctxt.setText("");
        colortxt.setText("");
        whatcatlb.setText("");
        whatbrandlb.setText("");
        whatcolorlb.setText("");
        mesuretxt.setText("");
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
    void LbtnAction(MouseEvent event) {
        mesuretxt.setText(mesureChop(mesuretxt.getText(),"L"));
    }
    @FXML
    void gBtnAction(MouseEvent event) {
        mesuretxt.setText(mesureChop(mesuretxt.getText(),"g"));
    }
    @FXML
    void kgBtnAction(MouseEvent event) {
        mesuretxt.setText(mesureChop(mesuretxt.getText(),"kg"));
    }
    @FXML
    void mlBtnAction(MouseEvent event) {
        mesuretxt.setText(mesureChop(mesuretxt.getText(),"ml"));
    }
    private String mesureChop(String mesure,String type){
        String id = mesure+"x";
        if (null != id) {
            String[] part = id.split("(?<=\\d)(?=\\D)");
            System.out.println("okok");
            System.out.println(part[0]);
            /*if(null!=part[0]){
                return "0" + type;
            }*/
            System.out.println("New Id : ok");
            return part[0]+type;
        }else {
           return "0" + type;
        }
        //return null;
    }

    @FXML
    void resetFields(MouseEvent event) {
        loadItemId();
    }


}
