package bo.custom.impl;

import bo.custom.PaymentBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.PaymentDAO;
import db.DBConnection;
import dto.OrderDTO;
import dto.PaymentDTO;
import entity.OrderEntity;
import entity.PaymentEntity;

import java.sql.Connection;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO dao = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderDAO oderdao = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }

    @Override
    public boolean addConformPayment(PaymentDTO payment) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        PaymentEntity paymententity = new PaymentEntity(payment.getPaymentId(),payment.getOrderId(),payment.getDate(),payment.getTime(),payment.getNetTotal());
        boolean isAdded = dao.add(paymententity);
        if(isAdded){
            OrderEntity orderentity = new OrderEntity(payment.getOrderId(),"Completed");
            isAdded = oderdao.setState(orderentity);
            if (!isAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("stockadd 10");
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("stockadd 11");
            return true;
        }else{
            connection.rollback();
            connection.setAutoCommit(true);
            System.out.println("stockadd 12");
            return false;
        }

    }
}

