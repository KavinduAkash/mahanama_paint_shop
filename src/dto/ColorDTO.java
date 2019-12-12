package dto;

public class ColorDTO {
    private String colorId;
    private String colorname;

    public ColorDTO(String colorId, String colorname) {
        this.colorId = colorId;
        this.colorname = colorname;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }
}
