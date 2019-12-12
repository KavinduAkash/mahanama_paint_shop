package controller;

import bo.BOFactory;
import bo.custom.ManageItemBO;
import db.DBConnection;
import dto.CustomDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;


import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;


public class ItemReportsController implements Initializable {


    @FXML
    private AnchorPane itemlistbtn;

    @FXML
    private AnchorPane mostmoveitembtn;

    @FXML
    private AnchorPane lessmoveitembtn;

    @FXML
    private TableView<CustomDTO>itemtable;

    @FXML
    private ImageView printbtn;

    @FXML
    private Label topiclb;

    private int wht = 0;

    static ManageItemBO bo = (ManageItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);

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
        getTiemsList();
    }


    @FXML

    void getItemListAction(MouseEvent event) {
        getTiemsList();
    }

    private void getTiemsList(){
        topiclb.setText("ITEM LIST");
        wht = 1;
        try {
            ArrayList<CustomDTO> all = bo.getAllItem();
            itemtable.setItems(FXCollections.observableArrayList(all));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getLessMoveItemAction(MouseEvent event) {
        topiclb.setText("LESS MOVEABLE ITEM LIST");
        wht = 3;
        try {
            ArrayList<CustomDTO> less = bo.lessMovableItems();
            itemtable.setItems(FXCollections.observableArrayList(less));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getMostmoveItemAction(MouseEvent event) {
        topiclb.setText("MOST MOVEABLE ITEM LIST");
        wht = 2;
        try {
            ArrayList<CustomDTO> most = bo.mostMovableItems();
            itemtable.setItems(FXCollections.observableArrayList(most));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void printReportAction(MouseEvent event) {
        Connection connection = null;
        if(wht == 1){
           JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();
           try {
               connection = DBConnection.getInstance().getConnection();
           } catch (SQLException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
           InputStream is = this.getClass().getResourceAsStream("../reports/ItemListReport.jasper");
           JasperPrint print = null;
           try {
               print = JasperFillManager.fillReport(is, null,connection);
           } catch (JRException e) {
               e.printStackTrace();
           }
           JasperViewer.viewReport(print,false);
           try {
               JasperPrintManager.printReport(print,true);
           } catch (JRException e) {
               e.printStackTrace();
           }

       }else if(wht == 2){
            JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();
            try {
                connection = DBConnection.getInstance().getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            InputStream is = this.getClass().getResourceAsStream("../reports/MostMoveItems.jasper");
            JasperPrint print = null;
            try {
                print = JasperFillManager.fillReport(is, null,connection);
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperViewer.viewReport(print);
            try {
                JasperPrintManager.printReport(print,true);
            } catch (JRException e) {
                e.printStackTrace();
            }

        }else {
            JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();
            try {
                connection = DBConnection.getInstance().getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            InputStream is = this.getClass().getResourceAsStream("../reports/LessMoveItems.jasper");
            JasperPrint print = null;
            try {
                print = JasperFillManager.fillReport(is, null,connection);
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperViewer.viewReport(print);
            try {
                JasperPrintManager.printReport(print,true);
            } catch (JRException e) {
                e.printStackTrace();
            }

        }
        }
    }



