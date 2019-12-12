package controller;

import bo.BOFactory;
import bo.custom.CatBO;
import com.jfoenix.controls.JFXButton;
import dto.CatDTO;
import dto.ColorDTO;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CatogaryController implements Initializable {

    @FXML
    private TextField catnametxt;

    @FXML
    private TextArea catdesctxt;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private JFXButton deletebtn;

    @FXML
    private TableView<CatDTO> cattable;

    @FXML
    private TextField catsearchbar;

    @FXML
    private Label catIdlb;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cattable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("catId"));
        cattable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("catname"));
        cattable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("catdesc"));


        loadCatID();
        loadAllCatogaries();
    }

    static CatBO bo = (CatBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATOGARY);

    //---------add(save)------------
    @FXML
    void addCat(ActionEvent event) {
        String catId = catIdlb.getText();

        String catname = catnametxt.getText();
        if (Pattern.compile("^[A-z0-9-]{1,100}$").matcher(catname).matches()) {

            String catdesc = catdesctxt.getText();
            if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(catname).matches()) {

                CatDTO catdto = new CatDTO(catId,catname,catdesc);
                try {

                    boolean isAdded = CatogaryController.addCat(catdto);
                    if(isAdded){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Add new Catogary");
                        alert.setHeaderText(null);
                        alert.setContentText("Added successfully!");
                        alert.showAndWait();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Add new Catogary");
                        alert.setHeaderText(null);
                        alert.setContentText("Adding Fail!");
                        alert.showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                 }
            }else{
                catdesctxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Catogary Description");
                alert.setHeaderText(null);
                alert.setContentText("Catogary Description does not valid..");
                alert.showAndWait();
            }

        }else{
            catnametxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Catogary Name");
            alert.setHeaderText(null);
            alert.setContentText("Catogary name does not valid..");
            alert.showAndWait();
        }
        loadCatID();
        loadAllCatogaries();
    }
    //----add------
    private static boolean addCat(CatDTO catdto) throws Exception {
        return bo.addCatgory(catdto);
    }

    //------------delete catogary--------------------
    @FXML
    void deleteCat(ActionEvent event) {
        String catId = catIdlb.getText();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete color");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKCat(catId);
        } else {
            loadCatID();
            loadAllCatogaries();
        }
        loadCatID();
        loadAllCatogaries();
    }

    private void deleteOKCat(String id){
        try {

            boolean isDeleted = CatogaryController.deleteCat(id);
            if(isDeleted){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete Catogary");
                alert2.setHeaderText(null);
                alert2.setContentText("Deleted successfully!");
                alert2.showAndWait();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Delete Catogary");
                alert3.setHeaderText(null);
                alert3.setContentText("Deleting fail!");
                alert3.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //--------delete--------
    private static boolean deleteCat(String catId) throws Exception {
        return bo.deleteCatogary(catId);
    }

    @FXML
    void getCatValues(MouseEvent event) {
        CatDTO catrecordvalues = cattable.getSelectionModel().selectedItemProperty().get();
        catIdlb.setText(catrecordvalues.getCatId());
        catnametxt.setText(catrecordvalues.getCatname());
        catdesctxt.setText(catrecordvalues.getCatdesc());
    }

    @FXML
    void getSearchValue(KeyEvent event) {
        String value = catsearchbar.getText();
        value = "'"+value+"%'";
        try {
            System.out.println("Cat search problem 1");
            ArrayList<CatDTO> searchValues = CatogaryController.getSearchValue(value);
            System.out.println("Cat search problem 2");
            cattable.setItems(FXCollections.observableArrayList(searchValues));
            System.out.println("Cat search problem 3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<CatDTO> getSearchValue(String value) throws Exception {
        return bo.getSearchValues(value);
    }

    //-----------update catogary-------------
    @FXML
    void updateCat(ActionEvent event) {

        String catId = catIdlb.getText();

        String catname = catnametxt.getText();
        if (Pattern.compile("^[A-z0-9-]{1,100}$").matcher(catname).matches()) {

            String catdesc = catdesctxt.getText();
            if (Pattern.compile("^[A-z0-9.-]{1,500}$").matcher(catname).matches()) {

                CatDTO catdto = new CatDTO(catId,catname,catdesc);
                try {

                    boolean isUpdated = CatogaryController.updateCat(catdto);
                    if (isUpdated) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Update Catogary");
                        alert.setHeaderText(null);
                        alert.setContentText("Updated successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Update Catogary");
                        alert.setHeaderText(null);
                        alert.setContentText("Updating fail!");
                        alert.showAndWait();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                catdesctxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Catogary Description");
                alert.setHeaderText(null);
                alert.setContentText("Catogary Description does not valid..");
                alert.showAndWait();
            }

        }else{
            catnametxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Catogary Name");
            alert.setHeaderText(null);
            alert.setContentText("Catogary name does not valid..");
            alert.showAndWait();
        }
        loadCatID();
        loadAllCatogaries();

    }
    //-----update-----
    private static boolean updateCat(CatDTO catdto) throws Exception {
        return bo.updateCatogary(catdto);
    }

    private void loadCatID() {
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.CATOGARY);
            catIdlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        catnametxt.setText("");
        catdesctxt.setText("");
    }

    //---------load Catogaries------------
    private void loadAllCatogaries(){
        try {

            ArrayList<CatDTO>allValues = CatogaryController.getAllValues();
            cattable.setItems(FXCollections.observableArrayList(allValues));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //------load-------
    private static ArrayList<CatDTO> getAllValues() throws Exception {
        return bo.getAllCatogary();
    }
}
