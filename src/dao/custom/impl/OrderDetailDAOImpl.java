package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import entity.OrderDetailEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public String getLastId() throws Exception {
        return null;
    }

    @Override
    public boolean add(OrderDetailEntity orderDetailEntity) throws Exception {
        String sql = "INSERT INTO orderdetail VALUES(?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,orderDetailEntity.getOrderId(),Integer.valueOf(orderDetailEntity.getIndex()),orderDetailEntity.getItemId(),orderDetailEntity.getBadgeId(),Integer.valueOf(orderDetailEntity.getQty()),Double.valueOf(orderDetailEntity.getAmount()));
    }

    @Override
    public boolean update(OrderDetailEntity orderDetailEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String t) throws Exception {
        return false;
    }

    @Override
    public ArrayList<OrderDetailEntity> search(String t) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderDetailEntity> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderDetailEntity>getAllCompatibleOrderDetils(String id) throws Exception {
        String sql = "SELECT*FROM orderdetail WHERE orderId = '"+id+"'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<OrderDetailEntity> all = new ArrayList<>();
        while(rst.next()){
            all.add(new OrderDetailEntity(rst.getString(1),rst.getInt(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getDouble(6)));
        }
        return all;
    }
}
