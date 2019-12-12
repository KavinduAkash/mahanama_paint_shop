package controller;

import bo.BOFactory;
import bo.custom.ColorBO;
import com.jfoenix.controls.JFXButton;
import dto.ColorDTO;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ColorsController implements Initializable {

    @FXML
    private TextField colornametxt;

    @FXML
    private TableView<ColorDTO> colortable;

    @FXML
    private JFXButton colorsavebtn;

    @FXML
    private JFXButton colorupdatebtn;

    @FXML
    private JFXButton colordeletebtn;

    @FXML
    private Label colorIdlb;

    @FXML
    private TextField colorsearchbar;

    @FXML
    private JFXButton reloadbtn;



    static ColorBO bo = (ColorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COLOR);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colortable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("colorId"));
        colortable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("colorname"));

        loadColorID();
        loadAllColors();
    }



    private void loadAllColors(){

        try {
            ArrayList<ColorDTO> cololist= null;
            cololist = ColorsController.getAllColors();
            colortable.setItems(FXCollections.observableArrayList(cololist));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static ArrayList<ColorDTO> getAllColors() throws Exception {
        return bo.getAllColors();
    }

  //-----------------------delete Color-----------------------
    @FXML
    void deleteColorbtnAction(ActionEvent event) {
        String colorId = colorIdlb.getText();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation for delete color");
        alert1.setHeaderText(null);
        alert1.setContentText("Are you ok with this deleting?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteOKColor(colorId);
        } else {
            loadColorID();
            loadAllColors();
        }

    }
    private void deleteOKColor(String colorId){
        try {
            boolean isDelete = ColorsController.deleteColor(colorId);
            if (isDelete){
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Delete Color");
                alert2.setHeaderText(null);
                alert2.setContentText("Deleted successfully!");
                alert2.showAndWait();
            }else{
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Delete Color");
                alert3.setHeaderText(null);
                alert3.setContentText("Deleting fail!");
                alert3.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadColorID();
        loadAllColors();

    }
    //------delete--------
    private static boolean deleteColor(String colorId) throws Exception {
        return bo.deleteColor(colorId);
    }

    //-----------------------add(Save) Color-----------------------
    @FXML
    void saveColorbtnAction(ActionEvent event) {

            String colorId = colorIdlb.getText();

            String colorname = colornametxt.getText();
            if (Pattern.compile("^[A-z0-9]{1,100}$").matcher(colorname).matches()) {

                ColorDTO colordto = new ColorDTO(colorId, colorname);

                try {

                    boolean isAdded = ColorsController.addColor(colordto);
                    if (isAdded) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Add New Color");
                        alert.setHeaderText(null);
                        alert.setContentText("Added successfully!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Add New Color");
                        alert.setHeaderText(null);
                        alert.setContentText("Adding fail!");
                        alert.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                loadColorID();
                loadAllColors();
            } else {
                colornametxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible Color Name");
                alert.setHeaderText(null);
                alert.setContentText("Color name does not valid..");
                alert.showAndWait();
            }

    }
    //------->add--------
    private static boolean addColor(ColorDTO colordto) throws Exception {
        return bo.addColor(colordto);
    }

    //-----------------------update(update) Color-----------------------
    @FXML
    void updateColorbtnAction(ActionEvent event) {
        String colorId = colorIdlb.getText();
        String colorname = colornametxt.getText();
        if (Pattern.compile("^[A-z0-9]{1,100}$").matcher(colorname).matches()) {
            ColorDTO colordto = new ColorDTO(colorId, colorname);

            try {

                boolean isUpdated = ColorsController.updateColor(colordto);
                if (isUpdated) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Color");
                    alert.setHeaderText(null);
                    alert.setContentText("Updated successfully!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Update Color");
                    alert.setHeaderText(null);
                    alert.setContentText("Updating fail!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            colornametxt.requestFocus();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incompatible Color Name");
            alert.setHeaderText(null);
            alert.setContentText("Color name does not valid..");
            alert.showAndWait();
        }
        loadColorID();
        loadAllColors();
    }
    //------->update--------
    private static boolean updateColor(ColorDTO colordto) throws Exception {
       return bo.updateColor(colordto);

    }

    @FXML
    void getSearchValue(KeyEvent event) {
        String searchValue = colorsearchbar.getText();
        searchValue = "'"+searchValue+"%'";
        try {
            System.out.println("Color search problem 1");
            ArrayList<ColorDTO> scolor = ColorsController.searchColorValue(searchValue);
            System.out.println("Color search problem 2");
            colortable.setItems(FXCollections.observableArrayList(scolor));
            System.out.println("Color search problem 3");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static ArrayList<ColorDTO> searchColorValue(String searchValue) throws Exception {
        return bo.searchColor(searchValue);
    }

    private void loadColorID() {
        try {

            String id = idgenerator.generateID(idgenerator.IDTypes.COLOR);
            colorIdlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        colornametxt.setText("");
        loadAllColors();

    }

    @FXML
    void getRecordValue(MouseEvent event) {
        ColorDTO colorrecordvalues = colortable.getSelectionModel().selectedItemProperty().get();
        colorIdlb.setText(colorrecordvalues.getColorId());
        colornametxt.setText(colorrecordvalues.getColorname());
    }

    @FXML
    void reloadColorTable(ActionEvent event) {
        loadAllColors();
        colorsearchbar.setText("");
    }



}
