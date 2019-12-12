package dto;

import java.util.ArrayList;

public class PlaceOrderDTO {

    private String orderId;
    private String orderdate;
    private double totalprice;
    private String state;
    ArrayList<OrderDetailDTO> orderdetail = new ArrayList<>();

    public PlaceOrderDTO(String orderId, String orderdate, double totalprice, String state, ArrayList<OrderDetailDTO> orderdetail) {
        this.orderId = orderId;
        this.orderdate = orderdate;
        this.totalprice = totalprice;
        this.state = state;
        this.orderdetail = orderdetail;
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

    public ArrayList<OrderDetailDTO> getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(ArrayList<OrderDetailDTO> orderdetail) {
        this.orderdetail = orderdetail;
    }
}
