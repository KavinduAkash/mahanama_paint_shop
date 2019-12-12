package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PaymentDAO;
import entity.PaymentEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT paymentId FROM payment ORDER BY paymentId DESC LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(PaymentEntity paymentEntity) throws Exception {
        String sql = "INSERT INTO payment VALUES(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,paymentEntity.getPaymentId(),paymentEntity.getOrderId(),paymentEntity.getDate(),paymentEntity.getTime(),paymentEntity.getNetTotal());
    }

    @Override
    public boolean update(PaymentEntity paymentEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String t) throws Exception {
        return false;
    }

    @Override
    public ArrayList<PaymentEntity> search(String t) throws Exception {
        return null;
    }

    @Override
    public ArrayList<PaymentEntity> getAll() throws Exception {
        return null;
    }
}
