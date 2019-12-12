package entity;

public class BrandEntity {
    private String brandId;
    private String brandName;
    private String country;
    private String description;

    public BrandEntity(String brandId, String brandName, String country, String description) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.country = country;
        this.description = description;
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
}
