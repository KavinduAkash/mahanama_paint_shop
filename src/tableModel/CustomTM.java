package tableModel;

public class CustomTM {
    private int index;
    private String itemId;
    private String itemName;
    private String color;
    private String mesure;
    private String badgeId;
    private double unitPrice;
    private int qty;
    private double amount;

    public CustomTM(int index,String itemId, String itemName, String color, String mesure, String badgeId, double unitPrice, int qty, double amount) {
        this.index = index;
        this.itemId = itemId;
        this.itemName = itemName;
        this.color = color;
        this.mesure = mesure;
        this.badgeId = badgeId;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.amount = amount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMesure() {
        return mesure;
    }

    public void setMesure(String mesure) {
        this.mesure = mesure;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
