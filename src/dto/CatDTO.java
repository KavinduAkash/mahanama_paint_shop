package dto;

import javafx.scene.control.TextArea;

public class CatDTO {
    private String catId;
    private String catname;
    private String catdesc;

    public CatDTO(String catId, String catname, String catdesc) {
        this.catId = catId;
        this.catname = catname;
        this.catdesc = catdesc;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }
}
