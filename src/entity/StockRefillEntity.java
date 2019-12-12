package entity;

public class StockRefillEntity {
    private String addDate;
    private String stockId;
    private String itemId;
    private String badgeId;
    private int qty;

    public StockRefillEntity(String addDate, String stockId, String itemId, String badgeId, int qty) {
        this.addDate = addDate;
        this.stockId = stockId;
        this.itemId = itemId;
        this.badgeId = badgeId;
        this.qty = qty;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
