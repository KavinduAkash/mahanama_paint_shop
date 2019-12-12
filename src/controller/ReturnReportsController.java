package controller;

import bo.BOFactory;
import bo.custom.ReturnBO;
import db.DBConnection;
import dto.ReturnDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReturnReportsController implements Initializable {


    @FXML
    private TableView<ReturnDTO> returntable;

    @FXML
    private ImageView printbtn;

    static ReturnBO bo = (ReturnBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        returntable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusreturnId"));
        returntable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        returntable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        returntable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("badgeId"));
        returntable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnqty"));
        returntable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("returnamount"));
        returntable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("returndate"));
        returntable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("returnMethod"));

        loadAllReturns();
    }
    private void loadAllReturns() {
        try {

            ArrayList<ReturnDTO> all = bo.getAllReturns();
            returntable.setItems(FXCollections.observableArrayList(all));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        InputStream is = this.getClass().getResourceAsStream("../reports/ReturnReport.jasper");
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



