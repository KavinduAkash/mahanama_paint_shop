package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ReturnDAO;
import entity.ReturnEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReturnDAOImpl implements ReturnDAO {
    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT cusreturnId FROM customerreturn  order by cusreturnId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(ReturnEntity returnEntity) throws Exception {
        String sql = "INSERT INTO customerreturn VALUES(?,?,?,?,?,?,?,?,?)";
        System.out.println("ok2");
        return CrudUtil.executeUpdate(sql,returnEntity.getCusreturnId(),returnEntity.getReturndate(),returnEntity.getOrderId(),returnEntity.getItemID(),returnEntity.getBadgeId(),returnEntity.getReturnqty(),returnEntity.getReturnamount(),returnEntity.getReason(),returnEntity.getReturnMethod());
    }

    @Override
    public boolean update(ReturnEntity returnEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String t) throws Exception {
        return false;
    }

    @Override
    public ArrayList<ReturnEntity> search(String t) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ReturnEntity> getAll() throws Exception {
        String sql = "SELECT*FROM customerreturn";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ReturnEntity> allreturns = new ArrayList<>();
        while(rst.next()){
            allreturns.add(new ReturnEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(6),rst.getDouble(7),rst.getString(8),rst.getString(9)));
        }
        return allreturns;
    }

    @Override
    public int getShopReturnCount() throws Exception {
        String sql = "SELECT COUNT(cusreturnId) FROM customerreturn WHERE returnMethod='Return to Shop'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int count = 0;
        while(rst.next()){
            count = rst.getInt(1);
        }
        return count;
    }

    @Override
    public int getStockHallReturnCount() throws Exception {
        String sql = "SELECT COUNT(cusreturnId) FROM customerreturn WHERE returnMethod='Return to Stock Hall'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int count = 0;
        while(rst.next()){
            count = rst.getInt(1);
        }
        return count;
    }
}
