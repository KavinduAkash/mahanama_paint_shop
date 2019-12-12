package controller;

import bo.BOFactory;
import bo.custom.PDashboardBO;
import bo.custom.PlaceOrderBO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class PDashboardController implements Initializable {
    @FXML
    private Label todaysaleslb;

    @FXML
    private Label monthlyprofitlb;

    @FXML
    private Label todayProfitlb;

    @FXML
    private Label expbadgecountlb;

    @FXML
    private Label skippaymentib;

    @FXML
    private Label completepaymentib1;

    @FXML
    private Label shopreturncountlb;

    @FXML
    private Label stockhallreturnlb;

    @FXML
    private Label expobjectlb;

    @FXML
    private Label txttime;

    @FXML
    private Label txtdate;

    static PlaceOrderBO orderbo = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    static PDashboardBO dashbo = (PDashboardBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DASH);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTodaySaleCount();
        loadMonthlyPofit();
        loadTodayProfit();
        loadExpBadgeCount();
        loadSkipPaymentCount();
        loadCompletePaymentCount();
        loadShopReturnCount();
        loadStockHallReturnCount();
        loadExpItemCount();
        loadDateAndTime();
    }

    private void loadDateAndTime() {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String datetxt = simpleDateFormat.format(date);
        txtdate.setText(datetxt);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                                txttime.setText(simpleDateFormat.format(time.getTime()));
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void loadExpItemCount() {
        int expobjectcount = 0;
        try {
            expobjectcount = dashbo.getExpObjectCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        expobjectlb.setText(String.valueOf(expobjectcount));
    }

    private void loadStockHallReturnCount() {
        int stockhallreturncount = 0;
        try {
            stockhallreturncount = dashbo.getStockHallReturnCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stockhallreturnlb.setText(String.valueOf(stockhallreturncount));
    }

    private void loadShopReturnCount() {
        int shopreturncount = 0;
        try {
            shopreturncount = dashbo.getShopReturnCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shopreturncountlb.setText(String.valueOf(shopreturncount));
    }

    private void loadCompletePaymentCount() {
        int completeCount=0;
        try {
            completeCount = dashbo.getCompletePaymentCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        completepaymentib1.setText(String.valueOf(completeCount));
    }

    private void loadSkipPaymentCount() {
        int skippedPayments=0;
        try {
            skippedPayments = dashbo.getSkipPaymentCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        skippaymentib.setText(String.valueOf(skippedPayments));
    }

    private void loadExpBadgeCount() {
        int expbadge=0;
        try {
            expbadge = dashbo.getExpDateCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        expbadgecountlb.setText(String.valueOf(expbadge));
    }

    private void loadTodayProfit() {
        double todayProfit = 0;
        try {
            todayProfit = dashbo.todayProfit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        todayProfitlb.setText(String.valueOf(todayProfit));
    }

    private void loadMonthlyPofit() {
        double manthProfit = 0;
        try {
            manthProfit = dashbo.thisMonthlyProfit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        monthlyprofitlb.setText(String.valueOf(manthProfit));
    }

    private void loadTodaySaleCount() {
        int saleCount = 0;
        try {
            saleCount = orderbo.getSaleCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        todaysaleslb.setText(String.valueOf(saleCount));
    }


}
