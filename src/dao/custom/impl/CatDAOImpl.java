package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CatDAO;
import entity.CatEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CatDAOImpl implements CatDAO{

    @Override
    public String getLastId() throws Exception {
        String sql = "SELECT catId FROM catogary order by catId desc LIMIT 1";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(CatEntity catEntity) throws Exception {
        String sql = "INSERT INTO catogary VALUES(?,?,?)";
        return CrudUtil.executeUpdate(sql,catEntity.getCatId(),catEntity.getCatName(),catEntity.getCatDesc());
    }

    @Override
    public boolean update(CatEntity catEntity) throws Exception {
        String sql = "UPDATE catogary SET catName = ?,catDesc = ? WHERE catId = ?";
        return CrudUtil.executeUpdate(sql,catEntity.getCatName(),catEntity.getCatDesc(),catEntity.getCatId());
    }

    @Override
    public boolean delete(String t) throws Exception {
        System.out.println("Cat delete problem 3");
        String sql = "DELETE FROM catogary WHERE catId = ?";
        boolean b = CrudUtil.executeUpdate(sql,t);
        System.out.println("Crud return : "+b);
        return b;
    }

    @Override
    public ArrayList<CatEntity> search(String t) throws Exception {
       String sql = "SELECT*FROM catogary WHERE catId LIKE "+t+" OR catName LIKE "+t+" OR catDesc LIKE "+t+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CatEntity> searchcat = new ArrayList<>();
        while (rst.next()){
            searchcat.add(new CatEntity(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return searchcat;
    }

    @Override
    public ArrayList<CatEntity> getAll() throws Exception {
        String sql = "SELECT*FROM catogary";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CatEntity> allcat = new ArrayList<>();
        while (rst.next()){
            allcat.add(new CatEntity(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return allcat;
    }
}
