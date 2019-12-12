package dao.custom;

import dao.CrudDAO;
import entity.ReturnEntity;

public interface ReturnDAO extends CrudDAO<ReturnEntity,String>{
    public int getShopReturnCount()throws Exception;
    public int getStockHallReturnCount()throws Exception;

}
