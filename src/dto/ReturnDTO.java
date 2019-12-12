package dto;

import java.util.ArrayList;

public class ReturnDTO {
    private String cusreturnId;
    private String returndate;
    private String orderId;
    private String itemID;
    private String badgeId;
    private int returnqty;
    private double returnamount;
    private String reason;
    private String returnMethod;
    private StockRefillDTO stock;

    public ReturnDTO(String cusreturnId, String returndate, String orderId, String itemID, String badgeId, int returnqty, double returnamount, String reason, String returnMethod) {
        this.cusreturnId = cusreturnId;
        this.returndate = returndate;
        this.orderId = orderId;
        this.itemID = itemID;
        this.badgeId = badgeId;
        this.returnqty = returnqty;
        this.returnamount = returnamount;
        this.reason = reason;
        this.returnMethod = returnMethod;
    }

    public ReturnDTO(String cusreturnId, String returndate, String orderId, String itemID, String badgeId, int returnqty, double returnamount, String reason, String returnMethod, StockRefillDTO stock) {
        this.cusreturnId = cusreturnId;
        this.returndate = returndate;
        this.orderId = orderId;
        this.itemID = itemID;
        this.badgeId = badgeId;
        this.returnqty = returnqty;
        this.returnamount = returnamount;
        this.reason = reason;
        this.returnMethod = returnMethod;
        this.stock = stock;
    }

    public StockRefillDTO getStock() {
        return stock;
    }

    public void setStock(StockRefillDTO stock) {
        this.stock = stock;
    }

    public String getCusreturnId() {
        return cusreturnId;
    }

    public void setCusreturnId(String cusreturnId) {
        this.cusreturnId = cusreturnId;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public int getReturnqty() {
        return returnqty;
    }

    public void setReturnqty(int returnqty) {
        this.returnqty = returnqty;
    }

    public double getReturnamount() {
        return returnamount;
    }

    public void setReturnamount(double returnamount) {
        this.returnamount = returnamount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReturnMethod() {
        return returnMethod;
    }

    public void setReturnMethod(String returnMethod) {
        this.returnMethod = returnMethod;
    }
}
