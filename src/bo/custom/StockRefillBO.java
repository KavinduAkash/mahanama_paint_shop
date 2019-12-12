package bo.custom;

import bo.SuperBO;
import dto.BadgeDTO;
import dto.CustomDTO;
import dto.ItemDTO;
import dto.StockRefillDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockRefillBO extends SuperBO {
    public ArrayList<CustomDTO> getAllItem()throws Exception;
    public ArrayList<BadgeDTO> getAllBadge()throws Exception;

    public boolean addStock(StockRefillDTO stock) throws SQLException,ClassNotFoundException, Exception;

    public boolean updateStock(StockRefillDTO stock) throws SQLException,ClassNotFoundException,Exception;

    public boolean deleteStock(String ID) throws SQLException,ClassNotFoundException,Exception;

    public ArrayList<CustomDTO> getAllStock()throws Exception;

    public ArrayList<CustomDTO> searchItem(String value)throws Exception;
    public ArrayList<CustomDTO> searchItem(String value1,String value2)throws Exception;

    public String getLastId()throws Exception;

    public ArrayList<CustomDTO> getSearchStock(String date,String anyid)throws Exception;


}
