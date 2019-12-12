package entity;

public class ColorEntity {
    private String colorId;
    private String colorName;

    public ColorEntity(String colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
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
}
