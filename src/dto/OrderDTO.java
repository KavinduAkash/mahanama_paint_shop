package dto;

public class OrderDTO {
    private String orderId;
    private String orderdate;
    private double totalprice;
    private String state;

    public OrderDTO(String orderId, String orderdate, double totalprice, String state) {
        this.orderId = orderId;
        this.orderdate = orderdate;
        this.totalprice = totalprice;
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
