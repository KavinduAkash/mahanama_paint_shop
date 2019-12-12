package dao.custom;

import dao.CrudDAO;
import dto.BrandDTO;
import dto.CatDTO;
import dto.ColorDTO;
import dto.CustomDTO;
import entity.*;

import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemEntity,String>{
    public ArrayList<CatEntity> allCat()throws Exception;
    public ArrayList<BrandEntity> allBrand()throws Exception;
    public ArrayList<ColorEntity> allColor()throws Exception;

    public ArrayList<CatEntity> getSearchCat(String t) throws Exception;
    public ArrayList<BrandEntity> getSearchBrand(String t) throws Exception;
    public ArrayList<ColorEntity> getSearchColor(String t) throws Exception;

    public boolean qtyUpdate(String t,int q) throws Exception;

    public String isItemAvailable(String id) throws Exception;

    public boolean addOrderDeleteItemQty(ItemEntity ie)throws Exception;

    public boolean itemQtyChange(String itemId,int qty)throws Exception;


}
