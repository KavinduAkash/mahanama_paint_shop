package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BadgeDAO;
import entity.BadgeEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BadgeDAOImpl implements BadgeDAO{
    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT badgeId FROM badge order by badgeId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(BadgeEntity badgeEntity) throws Exception {
        System.out.println("p5");
        String sql = "INSERT INTO badge VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,badgeEntity.getBadgeId(),badgeEntity.getBuyPrice(),badgeEntity.getExpDate(),badgeEntity.getSalePrice());
    }

    @Override
    public boolean update(BadgeEntity badgeEntity) throws Exception {
        String sql = "UPDATE badge SET buyPrice = ?,expDate = ?,salePrice = ? WHERE badgeId = ?";
        return CrudUtil.executeUpdate(sql,badgeEntity.getBuyPrice(),badgeEntity.getExpDate(),badgeEntity.getSalePrice(),badgeEntity.getBadgeId());
    }

    @Override
    public boolean delete(String t) throws Exception {
        String sql = "DELETE FROM badge WHERE badgeId = ?";
        return CrudUtil.executeUpdate(sql,t);
    }

    @Override
    public ArrayList<BadgeEntity> search(String t) throws Exception {
        String sql = "SELECT * FROM badge WHERE badgeId Like "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BadgeEntity> badges = new ArrayList<>();
        while(rst.next()){
            badges.add(new BadgeEntity(rst.getString(1),rst.getDouble(2),rst.getString(3),rst.getDouble(4)));
        }
        return badges;
    }

    @Override
    public ArrayList<BadgeEntity> getAll() throws Exception {
        String sql = "SELECT * FROM badge";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BadgeEntity> badges = new ArrayList<>();
        while(rst.next()){
            badges.add(new BadgeEntity(rst.getString(1),rst.getDouble(2),rst.getString(3),rst.getDouble(4)));
        }
        return badges;
    }

    @Override
    public ArrayList<BadgeEntity> search(String t1, String t2, String t3) throws Exception {
        String sql = "SELECT * FROM badge WHERE buyPrice Like "+t1+" AND expDate LIKE "+t2+" AND salePrice LIKE "+t3+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BadgeEntity> badges = new ArrayList<>();
        while(rst.next()){
            badges.add(new BadgeEntity(rst.getString(1),rst.getDouble(2),rst.getString(3),rst.getDouble(4)));
        }
        System.out.println("In dao : "+badges);
        return badges;
    }

    @Override
    public ArrayList<BadgeEntity> isBadgeAvailable(String id) throws Exception {
        String sql = "SELECT * FROM badge WHERE badgeId ='"+id+"'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BadgeEntity> i = new ArrayList<>();
        while(rst.next()){
            System.out.println(rst.getString(1));
            i.add(new BadgeEntity(rst.getString(1),rst.getDouble(2),rst.getString(3),rst.getDouble(4)));
        }
        return i;
    }

    @Override
    public int expBadgeCount() throws Exception {
        String sql = " SELECT COUNT(badgeId) FROM badge WHERE CURDATE()>expDate";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int count = 0;
        while(rst.next()){
            count = rst.getInt(1);
        }
        return count;
    }


}
