package controller;

import bo.BOFactory;
import bo.custom.PaymentBO;
import bo.custom.PlaceOrderBO;
import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import dto.OrderDTO;
import dto.PaymentDTO;
import dto.PlaceOrderDTO;
import generator.idgenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.PlaceorderController.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.table.TableModel;


public class SalepaymentpaneController implements Initializable {
    @FXML
    private Label paymentidlb;

    @FXML
    private Label orderidlb;

    @FXML
    private Label nettotlb;

    @FXML
    private Label balancelb;

    private String orderId;

    private double nettot;

    @FXML
    private TableView<OrderDTO> ordertbl;

    @FXML
    private JFXButton loadskipbtn;

    @FXML
    private JFXButton loadallordersbtn;

    @FXML
    private TextField cashtxt;

    @FXML
    private JFXButton paymentconformbtn;

    @FXML
    private JFXButton orderhandlebtn;

    public static Stage orderhandlestage;

    static PlaceOrderBO bo = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    static PaymentBO paybo = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ordertbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        ordertbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderdate"));
        ordertbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("totalprice"));
        ordertbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("state"));

        loadPaymentId();
        loadAllorders();
    }

    private void loadAllorders() {
        try {

            ArrayList<OrderDTO> allorders = bo.getAllOrders();
            ordertbl.setItems(FXCollections.observableArrayList(allorders));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadSkipPayments(ActionEvent event) {
        ArrayList<OrderDTO> allorders = null;
        try {
            allorders = bo.getSkipOrders();
            ordertbl.setItems(FXCollections.observableArrayList(allorders));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getValueFunction(String id,double nettot){
        orderidlb.setText(id);
        nettotlb.setText(String.valueOf(nettot));
    }


    @FXML
    void loadallorderAction(ActionEvent event) {
        loadAllorders();
    }

    @FXML
    void getSelectedOrder(MouseEvent event) {
        OrderDTO selectvalues = ordertbl.getSelectionModel().selectedItemProperty().get();
        orderidlb.setText(selectvalues.getOrderId());
        nettotlb.setText(String.valueOf(selectvalues.getTotalprice()));
    }


    private void loadPaymentId() {
        String id = null;
        try {

            id = idgenerator.generateID(idgenerator.IDTypes.PAYMENT);
            paymentidlb.setText(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void getCashReleseAction(KeyEvent event) {
            double nettot = Double.valueOf(nettotlb.getText());
            double cash = Double.valueOf(cashtxt.getText());
            if (Pattern.compile("^[0-9.]+$").matcher(String.valueOf(cash)).matches()) {
                double balance = cash - nettot;
                balancelb.setText(String.valueOf(balance));
            }else{
                cashtxt.requestFocus();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incompatible casha mount");
                alert.setHeaderText(null);
                alert.setContentText("Cash amount does not valid..");
                alert.showAndWait();
            }
        }

    @FXML
    void conformPaymentAction(ActionEvent event) {
        String paymentId = paymentidlb.getText();
        String orderId = orderidlb.getText();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now1 = LocalDateTime.now();
        String date = dtf1.format(now1);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now2 = LocalDateTime.now();
        String time = dtf2.format(now2);
        double nettotal = Double.parseDouble(nettotlb.getText());

        PaymentDTO paymentdto = new PaymentDTO(paymentId,orderId,date,time,nettotal);

        try {

            boolean isAdded = paybo.addConformPayment(paymentdto);
            if (isAdded) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Make Payment");
                alert.setHeaderText(null);
                alert.setContentText("Payment is completed!");
                alert.showAndWait();
                //JREmptyDataSource jrEmptyDataSource = new JREmptyDataSource();
                Connection connection = DBConnection.getInstance().getConnection();
                InputStream is = this.getClass().getResourceAsStream("../reports/InvoiceReport.jasper");
                HashMap map = new HashMap();
                map.put("POrderId", orderidlb.getText());
                map.put("NetTotal",Double.parseDouble(nettotlb.getText()));
                map.put("cash",Double.parseDouble(cashtxt.getText()));
                map.put("balance", Double.parseDouble(balancelb.getText()));
                JasperPrint print = JasperFillManager.fillReport(is, map,connection);
                JasperViewer.viewReport(print,false);
                JasperPrintManager.printReport(print,false);


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Make Payment");
                alert.setHeaderText(null);
                alert.setContentText("Make Payment is Fail!");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        loadPaymentId();
        loadAllorders();
        dataclear();
    }

    private void dataclear() {
        orderidlb.setText("");
        nettotlb.setText("");
        cashtxt.setText("");
        balancelb.setText("");
    }

    @FXML
    void orderhanbleAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(this.getClass().getResource("../view/Verification.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            orderhandlestage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
