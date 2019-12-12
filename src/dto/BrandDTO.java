package dto;

public class BrandDTO {
    private String brandId;
    private String brandname;
    private String country;
    private String branddesc;

    public BrandDTO(String brandId, String brandname, String country, String branddesc) {
        this.brandId = brandId;
        this.brandname = brandname;
        this.country = country;
        this.branddesc = branddesc;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBranddesc() {
        return branddesc;
    }

    public void setBranddesc(String branddesc) {
        this.branddesc = branddesc;
    }
}
