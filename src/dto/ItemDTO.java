package dto;

public class ItemDTO {
    private String itemId;
    private String catId;
    private String brandId;
    private String itemname;
    private String itemdesc;
    private String colorId;
    private String mesure;
    private int qty;

    public ItemDTO(String itemId, String catId, String brandId, String itemname, String itemdesc, String colorId, String mesure, int qty) {
        this.itemId = itemId;
        this.catId = catId;
        this.brandId = brandId;
        this.itemname = itemname;
        this.itemdesc = itemdesc;
        this.colorId = colorId;
        this.mesure = mesure;
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

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
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
