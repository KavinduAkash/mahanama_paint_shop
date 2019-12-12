package bo.custom.impl;

import bo.custom.ManageItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.QuaryDAO;
import dto.*;
import entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageItemBOImpl implements ManageItemBO{

    ItemDAO dao = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    QuaryDAO qdao = (QuaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUARY);

    @Override
    public ArrayList<CatDTO> getAllCat() throws Exception {
        ArrayList<CatEntity> catall = dao.allCat();
        ArrayList<CatDTO> all = new ArrayList<>();
        for (CatEntity ce : catall) {
            all.add(new CatDTO(ce.getCatId(),ce.getCatName(),ce.getCatDesc()));
        }
        return all;
    }

    @Override
    public ArrayList<BrandDTO> getAllBrand() throws Exception {
        ArrayList<BrandEntity> brandall = dao.allBrand();
        ArrayList<BrandDTO> all = new ArrayList<>();
        for (BrandEntity be : brandall) {
            all.add(new BrandDTO(be.getBrandId(),be.getBrandName(),be.getCountry(),be.getDescription()));
        }
        return all;
    }

    @Override
    public ArrayList<ColorDTO> getAllColor() throws Exception {
        ArrayList<ColorEntity> colorall = dao.allColor();
        ArrayList<ColorDTO> all = new ArrayList<>();
        for (ColorEntity ce : colorall) {
            all.add(new ColorDTO(ce.getColorId(),ce.getColorName()));
        }
        return all;
    }

    @Override
    public ArrayList<CatDTO> getSearchCat(String t) throws Exception {
        ArrayList<CatEntity> searchCat = dao.getSearchCat(t);
        ArrayList<CatDTO> searchAll = new ArrayList<>();
        for (CatEntity ce : searchCat) {
            searchAll.add(new CatDTO(ce.getCatId(),ce.getCatName(),ce.getCatDesc()));
        }
        return searchAll;
    }

    @Override
    public ArrayList<BrandDTO> getSearchBrand(String t) throws Exception {
        ArrayList<BrandEntity> searchbrand = dao.getSearchBrand(t);
        ArrayList<BrandDTO> searchAll = new ArrayList<>();
        for (BrandEntity be : searchbrand) {
            searchAll.add(new BrandDTO(be.getBrandId(),be.getBrandName(),be.getCountry(),be.getDescription()));
        }
        return searchAll;
    }

    @Override
    public ArrayList<ColorDTO> getSearchColor(String t) throws Exception {
        ArrayList<ColorEntity> searchcolor = dao.getSearchColor(t);
        ArrayList<ColorDTO> searchAll = new ArrayList<>();
        for (ColorEntity coe : searchcolor) {
            searchAll.add(new ColorDTO(coe.getColorId(),coe.getColorName()));
        }
        return searchAll;
    }

    @Override
    public CustomDTO getselectItem(String t) throws Exception {
        CustomEntity select = qdao.getSelect(t);
        return new CustomDTO(select.getItemId(),select.getCatId(),select.getCatName(),select.getBrandId(),select.getBrandName(),select.getItemName(),select.getItemDescription(),select.getColorId(),select.getColorName(),select.getMesure(),select.getQty());
    }

    @Override
    public String isItemAvailable(String id) throws Exception {
        return dao.isItemAvailable(id);
    }

    @Override
    public ArrayList<CustomDTO> mostMovableItems() throws Exception {
        ArrayList<CustomEntity> items = qdao.getMostMoveItem();
        ArrayList<CustomDTO> allmostmove = new ArrayList<>();
        for (CustomEntity cuse : items) {
            allmostmove.add(new CustomDTO(cuse.getItemId(),cuse.getCatName(),cuse.getBrandName(),cuse.getItemName(),cuse.getItemDescription(),cuse.getColorName(),cuse.getMesure(),cuse.getQty()));
        }
        return allmostmove;
    }

    @Override
    public ArrayList<CustomDTO> lessMovableItems() throws Exception {
        ArrayList<CustomEntity> items = qdao.getLessMoveItem();
        ArrayList<CustomDTO> alllessmove = new ArrayList<>();
        for (CustomEntity cuse : items) {
            alllessmove.add(new CustomDTO(cuse.getItemId(),cuse.getCatName(),cuse.getBrandName(),cuse.getItemName(),cuse.getItemDescription(),cuse.getColorName(),cuse.getMesure(),cuse.getQty()));
        }
        return alllessmove;
    }

    @Override
    public boolean addItem(ItemDTO item) throws SQLException, ClassNotFoundException, Exception {
       return dao.add(new ItemEntity(item.getItemId(),item.getCatId(),item.getBrandId(),item.getItemname(),item.getItemdesc(),item.getColorId(),item.getMesure(),item.getQty()));
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new ItemEntity(item.getItemId(),item.getCatId(),item.getBrandId(),item.getItemname(),item.getItemdesc(),item.getColorId(),item.getMesure(),item.getQty()));
    }

    @Override
    public boolean deleteItem(String ID) throws SQLException, ClassNotFoundException, Exception {
        return dao.delete(ID);
    }

    @Override
    public ArrayList<CustomDTO> searchItem(String value) throws Exception {
        ArrayList<CustomEntity> searchitem= qdao.getSearchItem(value);
        ArrayList<CustomDTO> search = new ArrayList<>();
        for (CustomEntity ce : searchitem) {
            search.add(new CustomDTO(ce.getItemId(),ce.getBrandName(),ce.getCatName(),ce.getItemName(),ce.getItemDescription(),ce.getColorName(),ce.getMesure(),ce.getQty()));
        }
        return search;
    }

    @Override
    public ArrayList<CustomDTO> searchItem(String value1, String value2) throws Exception {
        ArrayList<CustomEntity> searchitem= qdao.getSearchItem(value1,value2);
        ArrayList<CustomDTO> search = new ArrayList<>();
        for (CustomEntity ce : searchitem) {
            search.add(new CustomDTO(ce.getItemId(),ce.getBrandName(),ce.getCatName(),ce.getItemName(),ce.getItemDescription(),ce.getColorName(),ce.getMesure(),ce.getQty()));
        }
        return search;
    }


    @Override
    public ArrayList<CustomDTO> getAllItem() throws Exception {
        ArrayList<CustomEntity> items = qdao.getAllItem();
        ArrayList<CustomDTO> all = new ArrayList<>();
        for (CustomEntity cuse : items) {
            all.add(new CustomDTO(cuse.getItemId(),cuse.getCatName(),cuse.getBrandName(),cuse.getItemName(),cuse.getItemDescription(),cuse.getColorName(),cuse.getMesure(),cuse.getQty()));
        }
        return all;
    }

    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }

}
