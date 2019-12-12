package entity;

public class ItemEntity {
    private String itemId;
    private String catId;
    private String brandId;
    private String itemName;
    private String itemDescription;
    private String colorId;
    private String mesure;
    private int qty;

    public ItemEntity(String itemId, String catId, String brandId, String itemName, String itemDescription, String colorId, String mesure, int qty) {
        this.itemId = itemId;
        this.catId = catId;
        this.brandId = brandId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.colorId = colorId;
        this.mesure = mesure;
        this.qty = qty;
    }

    public ItemEntity(String itemId, int qty){
        this.itemId = itemId;
        this.qty = qty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
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

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
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
