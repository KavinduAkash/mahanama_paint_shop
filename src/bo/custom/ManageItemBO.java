package bo.custom;

import bo.SuperBO;
import dto.*;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageItemBO extends SuperBO{
    public ArrayList<CatDTO> getAllCat() throws Exception;
    public ArrayList<BrandDTO> getAllBrand() throws Exception;
    public ArrayList<ColorDTO> getAllColor() throws Exception;

    public ArrayList<CatDTO> getSearchCat(String t) throws Exception;
    public ArrayList<BrandDTO> getSearchBrand(String t) throws Exception;
    public ArrayList<ColorDTO> getSearchColor(String t) throws Exception;

    public CustomDTO getselectItem(String t) throws Exception;

    public String isItemAvailable(String id)throws Exception;

    public ArrayList<CustomDTO>mostMovableItems()throws Exception;

    public ArrayList<CustomDTO>lessMovableItems()throws Exception;
    //-------------------------------------------------------------------
    public boolean addItem(ItemDTO item) throws SQLException,ClassNotFoundException, Exception;

    public boolean updateItem(ItemDTO item) throws SQLException,ClassNotFoundException,Exception;

    public boolean deleteItem(String ID) throws SQLException,ClassNotFoundException,Exception;

    public ArrayList<CustomDTO> searchItem(String value)throws Exception;
    public ArrayList<CustomDTO> searchItem(String value1,String value2)throws Exception;

    public ArrayList<CustomDTO> getAllItem()throws Exception;

    public String getLastId()throws Exception;

}
