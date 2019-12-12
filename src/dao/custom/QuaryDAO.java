package dao.custom;

import dao.SuperDAO;
import entity.BadgeEntity;
import entity.CustomEntity;
import entity.ItemEntity;

import java.util.ArrayList;

public interface QuaryDAO extends SuperDAO {

    public ArrayList<CustomEntity> getAllItem()throws Exception;
    public CustomEntity getSelect(String t)throws Exception;
    public ArrayList<CustomEntity> getAllStock() throws Exception;
    public ArrayList<CustomEntity> getSearchItem(String ID) throws Exception;
    public ArrayList<CustomEntity> getSearchItem(String N,String c) throws Exception;
    public ArrayList<CustomEntity> searchByItemId(String itemId)throws Exception;
    public double thisMonthlyProfit()throws Exception;
    public double todayProfit()throws Exception;
    public int getExpObjectCount()throws Exception;
    public ArrayList<CustomEntity>getAllSearchStock(String date,String anyid)throws Exception;
    public ArrayList<CustomEntity> getMostMoveItem()throws Exception;
    public ArrayList<CustomEntity> getLessMoveItem()throws Exception;
}
