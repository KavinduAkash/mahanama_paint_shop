package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.StockRefillDAO;
import entity.*;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StockRefillDAOImpl implements StockRefillDAO {


    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT stockId FROM stock order by stockId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(StockRefillEntity stockRefillEntity) throws Exception {
       String sql = "INSERT INTO stock VALUES(?,?,?,?,?)";
       return CrudUtil.executeUpdate(sql,stockRefillEntity.getAddDate(),stockRefillEntity.getStockId(),stockRefillEntity.getItemId(),stockRefillEntity.getBadgeId(),stockRefillEntity.getQty());
    }

    @Override
    public boolean update(StockRefillEntity stockRefillEntity) throws Exception {
        String sql = "UPDATE stock SET itemId = ?,badgeId = ?,qty = ? WHERE stockId = ?";
        return CrudUtil.executeUpdate(sql,stockRefillEntity.getItemId(),stockRefillEntity.getBadgeId(),stockRefillEntity.getQty(),stockRefillEntity.getStockId());
    }

    @Override
    public boolean delete(String t) throws Exception {
        String sql = "DELETE FROM stock WHERE stockId = ?";
        return CrudUtil.executeUpdate(sql,t);
    }

    @Override
    public ArrayList<StockRefillEntity> search(String t) throws Exception {
        return null;
    }

    @Override
    public ArrayList<StockRefillEntity> getAll() throws Exception {
        return null;
    }

    //--------------------------------------------------------------
    @Override
    public ArrayList<BadgeEntity> getAllBadges() throws Exception {
        String sql = "SELECT * FROM badge";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BadgeEntity> badges = new ArrayList<>();
        while(rst.next()){
            badges.add(new BadgeEntity(rst.getString(1),rst.getDouble(2),rst.getString(3),rst.getDouble(4)));
        }
        return badges;
    }

}
