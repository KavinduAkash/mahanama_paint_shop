package dto;

public class BadgeDTO {
    private String badgeid;
    private double buyprice;
    private String expdate;
    private double saleprice;

    public BadgeDTO(String badgeid, double buyprice, String expdate, double saleprice) {
        this.badgeid = badgeid;
        this.buyprice = buyprice;
        this.expdate = expdate;
        this.saleprice = saleprice;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }
}
