package bo.custom;

import bo.SuperBO;
import dto.PaymentDTO;

public interface PaymentBO extends SuperBO {
    public String getLastId()throws Exception;
    public boolean addConformPayment(PaymentDTO payment)throws Exception;
}
