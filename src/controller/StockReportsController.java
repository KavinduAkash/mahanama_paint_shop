package controller;

import bo.BOFactory;
import bo.custom.StockRefillBO;
import db.DBConnection;
import dto.CustomDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockReportsController implements Initializable {
    @FXML
    private TableView<CustomDTO> stocktable;

    @FXML
    private ImageView printbtn;

    static StockRefillBO bo = (bo.custom.StockRefillBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCKREFILL);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stocktable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("addDate"));
        stocktable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("stockId"));
        stocktable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        stocktable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        stocktable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        stocktable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("expDate"));
        stocktable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        stocktable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("stockqty"));

        loadAllStock();
    }

    private void loadAllStock() {
        ArrayList<CustomDTO> allstock = null;
        try {

            allstock = bo.getAllStock();
            stocktable.setItems(FXCollections.observableArrayList(allstock));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void getSelectStock(MouseEvent event) {

    }

    @FXML
    void printReportAction(MouseEvent event) {

        Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            InputStream is = this.getClass().getResourceAsStream("../reports/StockReport.jasper");
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
    }
}