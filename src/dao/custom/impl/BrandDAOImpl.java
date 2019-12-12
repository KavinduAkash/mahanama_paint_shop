package dao.custom.impl;

import dao.CrudDAO;
import dao.CrudUtil;
import dao.custom.BrandDAO;
import entity.BrandEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BrandDAOImpl implements BrandDAO {

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT brandId FROM brand order by brandId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(BrandEntity brandEntity) throws Exception {
        String sql = "INSERT INTO brand VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,brandEntity.getBrandId(),brandEntity.getBrandName(),brandEntity.getCountry(),brandEntity.getDescription());
}

    @Override
    public boolean update(BrandEntity brandEntity) throws Exception {
        String sql = "UPDATE brand SET brandName = ?,country = ?,description = ? WHERE brandId = ?";
        return CrudUtil.executeUpdate(sql,brandEntity.getBrandName(),brandEntity.getCountry(),brandEntity.getDescription(),brandEntity.getBrandId());
    }


    @Override
    public boolean delete(String t) throws Exception {
        String sql = "DELETE FROM brand WHERE brandId = ?";
        return CrudUtil.executeUpdate(sql,t);
    }

    @Override
    public ArrayList<BrandEntity> search(String t) throws Exception {
        String sql = "SELECT * FROM brand WHERE brandId LIKE "+t+" OR brandName LIKE "+t+" OR country LIKE "+t+" OR description LIKE "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BrandEntity> searchrsult = new ArrayList<>();
        while(rst.next()){
            searchrsult.add(new BrandEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return searchrsult;
    }

    @Override
    public ArrayList<BrandEntity> getAll() throws Exception {
        String sql = "SELECT * FROM brand";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<BrandEntity> brands = new ArrayList<>();
        while(rst.next()){
            brands.add(new BrandEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return brands;
    }
}
