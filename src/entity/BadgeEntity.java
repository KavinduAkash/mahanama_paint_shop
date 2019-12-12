package entity;

public class BadgeEntity {
    private String badgeId;
    private double buyPrice;
    private String expDate;
    private double salePrice;

    public BadgeEntity(String badgeId, double buyPrice, String expDate, double salePrice) {
        this.badgeId = badgeId;
        this.buyPrice = buyPrice;
        this.expDate = expDate;
        this.salePrice = salePrice;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
