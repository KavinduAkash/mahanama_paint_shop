package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.OrderEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT orderId FROM customerOrder order by orderId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(OrderEntity orderEntity) throws Exception {
        System.out.println("come 2");
        String sql = "INSERT INTO customerOrder VALUES(?,?,?,?)";
        System.out.println("come 3");
        return CrudUtil.executeUpdate(sql,orderEntity.getOrderId(),orderEntity.getOrderdate(),orderEntity.getTotalprice(),orderEntity.getState());
    }

    @Override
    public boolean update(OrderEntity orderEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String t) throws Exception {
        String sql = "DELETE FROM customerOrder WHERE orderId = ?";
        return CrudUtil.executeUpdate(sql,t);
    }

    @Override
    public ArrayList<OrderEntity> search(String t) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderEntity> getAll() throws Exception {
        String sql = "SELECT * FROM customerOrder";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<OrderEntity> all = new ArrayList<>();
        while(rst.next()){
            all.add(new OrderEntity(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getString(4)));
        }
        return all;
    }

    @Override
    public ArrayList<OrderEntity> getAllSkipPayments() throws Exception {
        String sql = "SELECT * FROM customerOrder WHERE state = 'skiped'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<OrderEntity> all = new ArrayList<>();
        while(rst.next()){
            all.add(new OrderEntity(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getString(4)));
        }
        return all;
    }

    @Override
    public boolean setState(OrderEntity orderEntity) throws Exception {
        String sql = "UPDATE customerOrder SET state = ? WHERE orderId = ?";
        return CrudUtil.executeUpdate(sql,orderEntity.getState(),orderEntity.getOrderId());
    }

    @Override
    public ArrayList<String> isOrderIdAvailable(String id) throws Exception {
        String sql = "SELECT orderId,date FROM customerOrder WHERE orderId ='"+id+"'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<String> i = new ArrayList<>();
        while(rst.next()){
            i.add(rst.getString(1));
            i.add(rst.getString(2));
        }
        return i;
    }

    @Override
    public int completeOrderCount() throws Exception {
        String sql = "SELECT count(orderId) FROM customerorder WHERE date = CURDATE() AND state = 'Completed'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int count=0;
        while(rst.next()){
            count = rst.getInt(1);
        }
        return count;
    }

    @Override
    public int getSkipOrderCount() throws Exception {
        String sql = "SELECT count(orderId) FROM customerorder WHERE date = CURDATE() AND state = 'Skiped'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int count=0;
        while(rst.next()){
            count = rst.getInt(1);
        }
        return count;
    }
}
