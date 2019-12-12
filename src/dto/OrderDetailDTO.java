package dto;

public class OrderDetailDTO {
    private String orderId;
    private int index;
    private String itemId;
    private String badgeId;
    private int qty;
    private double amount;

    public OrderDetailDTO(String orderId, int index, String itemId,String badgeId, int qty, double amount) {
        this.orderId = orderId;
        this.index = index;
        this.itemId = itemId;
        this.badgeId = badgeId;
        this.qty = qty;
        this.amount = amount;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
