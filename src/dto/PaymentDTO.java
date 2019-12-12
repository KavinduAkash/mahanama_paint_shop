package dto;

public class PaymentDTO {

    private String paymentId;
    private String orderId;
    private String date;
    private String time;
    private double netTotal;

    public PaymentDTO(String paymentId, String orderId, String date, String time, double netTotal) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.netTotal = netTotal;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }
}
