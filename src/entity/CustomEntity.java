package entity;

//import com.sun.org.apache.xpath.internal.operations.String;

public class CustomEntity {
    //--------color-------------------
    private String colorId;
    private String colorName;

    //--------catogary----------------
    private String catId;
    private String catName;
    private String catDesc;

    //-------brand--------------------
    private String brandId;
    private String brandName;
    private String country;
    private String description;

    //------badge---------------------
    private String badgeId;
    private double buyPrice;
    private String expDate;
    private double salePrice;

    //------item----------------------
    private String itemId;
    //private String catId;
    //private String brandId;
    private String itemName;
    private String itemDescription;
    //private String colorId;
    private String mesure;
    private int qty;

    //------stock----------------------
    private String addDate;
    private String stockId;
    private int stockqty;

    public CustomEntity(String itemId, String brandName, String catName, String itemName, String itemDescription, String  colorName, String mesure, int qty) {
        this.itemId = itemId;
        this.brandName = brandName;
        this.catName = catName;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.colorName = colorName;
        this.mesure = mesure;
        this.qty = qty;
    }

    public CustomEntity(String itemId,String catId,String catName,String brandId,String brandName, String itemName, String itemDescription,String colorId,String  colorName, String mesure, int qty) {
        this.itemId = itemId;
        this.brandId = brandId;
        this.brandName = brandName;
        this.catId = catId;
        this.catName = catName;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.colorId = colorId;
        this.colorName = colorName;
        this.mesure = mesure;
        this.qty = qty;
    }
    public CustomEntity(String addDate, String stockId,String badgeId,double buyPrice,double salePrice,String expDate,String itemId,String itemName,String colorName,String mesure,int stockqty){
        this.addDate = addDate;
        this.stockId = stockId;
        this.badgeId = badgeId;
        this.buyPrice = buyPrice;
        this.salePrice = salePrice;
        this.expDate = expDate;
        this.itemId = itemId;
        this.itemName = itemName;
        this.colorName = colorName;
        this.mesure = mesure;
        this.stockqty = stockqty;
    }

    public CustomEntity(String badgeId, String expDate, double salePrice, int stockqty) {
        this.badgeId = badgeId;
        this.expDate = expDate;
        this.salePrice = salePrice;
        this.stockqty = stockqty;
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

    public int getStockqty() {
        return stockqty;
    }

    public void setStockqty(int stockqty) {
        this.stockqty = stockqty;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getMesure() {
        return mesure;
    }

    public void setMesure(String mesure) {
        this.mesure = mesure;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
