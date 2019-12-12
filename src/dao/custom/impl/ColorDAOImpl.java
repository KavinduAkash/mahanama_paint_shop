package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ColorDAO;
import entity.ColorEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ColorDAOImpl implements ColorDAO {

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT colorId FROM color  order by colorId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(ColorEntity colorEntity) throws Exception {
        String sql = "insert into color values(?,?)";
        return CrudUtil.executeUpdate(sql, colorEntity.getColorId(), colorEntity.getColorName());
    }

    @Override
    public boolean update(ColorEntity colorEntity) throws Exception {
        String sql = "UPDATE color SET colorName = ? WHERE colorId = ?";
        return CrudUtil.executeUpdate(sql, colorEntity.getColorName(), colorEntity.getColorId());
    }

    @Override
    public boolean delete(String ID) throws Exception {
        String sql = "DELETE FROM color WHERE colorId = ?";
        return CrudUtil.executeUpdate(sql,ID);
    }

    @Override
    public ArrayList<ColorEntity> search(String t) throws Exception {
        String sql = "SELECT * FROM color WHERE colorId LIKE "+t+" OR colorname LIKE "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ColorEntity> getlikecolor = new ArrayList<>();
        while(rst.next()){
            getlikecolor.add(new ColorEntity(rst.getString(1),rst.getString(2)));
        }
        return getlikecolor;
    }

    @Override
    public ArrayList<ColorEntity> getAll() throws Exception {
        String sql = "SELECT*FROM color";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<ColorEntity> all = new ArrayList<>();
        while(rst.next()){
            ColorEntity colorEntity = new ColorEntity(rst.getString(1),rst.getString(2));
            all.add(colorEntity);
        }
        return all;
    }


}
