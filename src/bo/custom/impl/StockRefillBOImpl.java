package bo.custom.impl;

import bo.custom.StockRefillBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.QuaryDAO;
import dao.custom.StockRefillDAO;
import db.DBConnection;
import dto.BadgeDTO;
import dto.CustomDTO;
import dto.StockRefillDTO;
import entity.BadgeEntity;
import entity.CustomEntity;
import entity.StockRefillEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockRefillBOImpl implements StockRefillBO {
    QuaryDAO qdao = (QuaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUARY);
    StockRefillDAO dao = (StockRefillDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCKREFILL);
    ItemDAO itemdao = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

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
    public ArrayList<BadgeDTO> getAllBadge() throws Exception {
        ArrayList<BadgeEntity> badges = dao.getAllBadges();
        ArrayList<BadgeDTO> all = new ArrayList<>();
        for (BadgeEntity be : badges) {
            all.add(new BadgeDTO(be.getBadgeId(),be.getBuyPrice(),be.getExpDate(),be.getSalePrice()));
        }
        return all;
    }





    @Override
    public boolean addStock(StockRefillDTO stock) throws SQLException, ClassNotFoundException, Exception {
        System.out.println("stockadd 5");
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        System.out.println("stockadd 6");
        boolean added = dao.add(new StockRefillEntity(stock.getAddDate(),stock.getStockId(),stock.getItemId(),stock.getBadgeId(),stock.getQty()));
        System.out.println("stockadd 7");
        System.out.println("addoption1 : "+added);
        if(added){
            System.out.println("stockadd 8");
            added = itemdao.qtyUpdate(stock.getItemId(),stock.getQty());
            System.out.println("stockadd 9");
            if (!added) {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("stockadd 10");
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("stockadd 11");
            return true;
        }else{
            connection.rollback();
            connection.setAutoCommit(true);
            System.out.println("stockadd 12");
            return false;
        }
    }

    @Override
    public boolean updateStock(StockRefillDTO stock) throws SQLException, ClassNotFoundException, Exception {
        StockRefillEntity stockRefillEntity = new StockRefillEntity(stock.getAddDate(),stock.getStockId(),stock.getItemId(),stock.getBadgeId(),stock.getQty());
        return dao.update(stockRefillEntity);
    }

    @Override
    public boolean deleteStock(String ID) throws SQLException, ClassNotFoundException, Exception {
        return dao.delete(ID);
    }

    @Override
    public ArrayList<CustomDTO> getAllStock() throws Exception {
        ArrayList<CustomEntity> allstock = qdao.getAllStock();
        ArrayList<CustomDTO> all = new ArrayList<>();
        for (CustomEntity ce : allstock) {
            all.add(new CustomDTO(ce.getAddDate(),ce.getStockId(),ce.getBadgeId(),ce.getBuyPrice(),ce.getSalePrice(),ce.getExpDate(),ce.getItemId(),ce.getItemName(),ce.getColorName(),ce.getMesure(),ce.getStockqty()));
        }
        return all;
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
    public String getLastId() throws Exception {
        return dao.getLastId();
    }

    @Override
    public ArrayList<CustomDTO> getSearchStock(String date, String anyid) throws Exception {
        ArrayList<CustomEntity> allstock = qdao.getAllSearchStock(date,anyid);
        ArrayList<CustomDTO> all = new ArrayList<>();
        for (CustomEntity ce : allstock) {
            all.add(new CustomDTO(ce.getAddDate(),ce.getStockId(),ce.getBadgeId(),ce.getBuyPrice(),ce.getSalePrice(),ce.getExpDate(),ce.getItemId(),ce.getItemName(),ce.getColorName(),ce.getMesure(),ce.getStockqty()));
        }
        return all;
    }
}
