package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.*;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{
    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT itemId FROM item  order by itemId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(ItemEntity itemEntity) throws Exception {
       String sql = "INSERT INTO item VALUES(?,?,?,?,?,?,?,?)";
       return CrudUtil.executeUpdate(sql, itemEntity.getItemId(), itemEntity.getCatId(), itemEntity.getBrandId(), itemEntity.getItemName(), itemEntity.getItemDescription(), itemEntity.getColorId(), itemEntity.getMesure(), itemEntity.getQty());
    }

    @Override
    public boolean update(ItemEntity itemEntity) throws Exception {
        String sql = "UPDATE item SET catId = ?,brandId = ?,itemName = ?,itemDescription = ?,colorId = ?,mesure = ?,handOnQty = ? WHERE itemId = ?";
        return CrudUtil.executeUpdate(sql, itemEntity.getCatId(), itemEntity.getBrandId(), itemEntity.getItemName(), itemEntity.getItemDescription(), itemEntity.getColorId(), itemEntity.getMesure(), itemEntity.getQty(),itemEntity.getItemId());
    }

    @Override
    public boolean delete(String t) throws Exception {
       String sql = "DELETE FROM item WHERE itemId = ?";
       return CrudUtil.executeUpdate(sql,t);
    }

    @Override
    public ArrayList<ItemEntity> search(String t) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ItemEntity> getAll() throws Exception {
        String sql = "SELECT*FROM item";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ItemEntity> all = new ArrayList<>();
        while(rst.next()){
            all.add(new ItemEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),Integer.parseInt(rst.getString(8))));
        }
        return all;
    }

    //-------other tables--------------------------------------
    @Override
    public ArrayList<CatEntity> allCat() throws Exception {
       String sql = "SELECT*FROM catogary";
       ResultSet rst = CrudUtil.executeQuery(sql);
       ArrayList<CatEntity> all = new ArrayList<>();
       while(rst.next()){
           all.add(new CatEntity(rst.getString(1),rst.getString(2),rst.getString(3)));
       }
       return all;
    }

    @Override
    public ArrayList<BrandEntity> allBrand() throws Exception {
        String sql = "SELECT*FROM brand";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BrandEntity> all = new ArrayList<>();
        while(rst.next()){
            all.add(new BrandEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return all;
    }

    @Override
    public ArrayList<ColorEntity> allColor() throws Exception {
        String sql = "SELECT*FROM color";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ColorEntity> all = new ArrayList<>();
        while(rst.next()){
            all.add(new ColorEntity(rst.getString(1),rst.getString(2)));
        }
        return all;
    }

    @Override
    public ArrayList<CatEntity> getSearchCat(String t) throws Exception {
        String sql = "SELECT*FROM catogary WHERE catId LIKE "+t+" OR catName LIKE "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CatEntity> scat = new ArrayList<>();
        while(rst.next()){
            scat.add(new CatEntity(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return scat;
    }

    @Override
    public ArrayList<BrandEntity> getSearchBrand(String t) throws Exception {
        String sql = "SELECT*FROM brand WHERE brandId LIKE "+t+" OR brandName LIKE "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BrandEntity> sb = new ArrayList<>();
        while(rst.next()){
            sb.add(new BrandEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return sb;
    }

    @Override
    public ArrayList<ColorEntity> getSearchColor(String t) throws Exception {
        String sql = "SELECT*FROM color WHERE colorId LIKE "+t+" OR colorName LIKE "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ColorEntity> cob = new ArrayList<>();
        while(rst.next()){
            cob.add(new ColorEntity(rst.getString(1),rst.getString(2)));
        }
        return cob;
    }

    @Override
    public boolean qtyUpdate(String t, int q) throws Exception {
        String sql = "UPDATE item SET handOnQty = handOnQty + ? WHERE itemId = ?";
        return CrudUtil.executeUpdate(sql,q,t);
    }

    @Override
    public String isItemAvailable(String id) throws Exception {
        String sql = "SELECT itemId FROM item WHERE itemId = '"+id+"'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        String i = null;
        while(rst.next()){
            i = rst.getString(1);
        }
        return i;
    }

    @Override
    public boolean addOrderDeleteItemQty(ItemEntity ie) throws Exception {
        String sql = "UPDATE item SET handOnQty = handOnQty+? WHERE itemId = ?";
        return CrudUtil.executeUpdate(sql,ie.getQty(),ie.getItemId());
    }

    @Override
    public boolean itemQtyChange(String itemId, int qty) throws Exception {
        String sql = "UPDATE item SET handOnQty = handOnQty-? WHERE itemId = ?";
        return CrudUtil.executeUpdate(sql,qty,itemId);
    }


}
