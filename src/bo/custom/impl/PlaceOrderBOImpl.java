package bo.custom.impl;

import bo.custom.PlaceOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import dto.*;
import entity.*;
import generator.idgenerator;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    QuaryDAO qdao = (QuaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUARY);
    OrderDAO orderdao = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO odetaildao = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    ItemDAO itemdao = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    StockRefillDAO stockdao = (StockRefillDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCKREFILL);

    @Override
    public ArrayList<CustomDTO> getAllItem() throws Exception {
        ArrayList<CustomEntity> items = qdao.getAllItem();
        ArrayList<CustomDTO> all = new ArrayList<>();
        for (CustomEntity cuse : items) {
            all.add(new CustomDTO(cuse.getItemId(), cuse.getCatName(), cuse.getBrandName(), cuse.getItemName(), cuse.getItemDescription(), cuse.getColorName(), cuse.getMesure(), cuse.getQty()));
        }
        return all;
    }

    @Override
    public ArrayList<CustomDTO> searchItem(String value) throws Exception {
        ArrayList<CustomEntity> searchitem = qdao.getSearchItem(value);
        ArrayList<CustomDTO> search = new ArrayList<>();
        for (CustomEntity ce : searchitem) {
            search.add(new CustomDTO(ce.getItemId(), ce.getBrandName(), ce.getCatName(), ce.getItemName(), ce.getItemDescription(), ce.getColorName(), ce.getMesure(), ce.getQty()));
        }
        return search;
    }

    @Override
    public ArrayList<CustomDTO> searchItem(String value1, String value2) throws Exception {
        ArrayList<CustomEntity> searchitem = qdao.getSearchItem(value1, value2);
        ArrayList<CustomDTO> search = new ArrayList<>();
        for (CustomEntity ce : searchitem) {
            search.add(new CustomDTO(ce.getItemId(), ce.getBrandName(), ce.getCatName(), ce.getItemName(), ce.getItemDescription(), ce.getColorName(), ce.getMesure(), ce.getQty()));
        }
        return search;
    }

    @Override
    public ArrayList<CustomDTO> getCompatibleBadges(String value) throws Exception {
        ArrayList<CustomEntity> compatiblebadges = qdao.searchByItemId(value);
        ArrayList<CustomDTO> badges = new ArrayList<>();
        for (CustomEntity be : compatiblebadges) {
            System.out.println(be.getBadgeId());
            badges.add(new CustomDTO(be.getBadgeId(), be.getExpDate(), be.getSalePrice(), be.getStockqty()));
        }
        return badges;
    }

    @Override
    public String getLastId() throws Exception {
        return orderdao.getLastId();
    }

    @Override
    public boolean addOrder(PlaceOrderDTO placeorderdto) throws Exception {
        System.out.println("come 1");
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isAdded = orderdao.add(new OrderEntity(placeorderdto.getOrderId(), placeorderdto.getOrderdate(), placeorderdto.getTotalprice(), placeorderdto.getState()));
        System.out.println("add order : "+isAdded);
        if (isAdded) {
            ArrayList<OrderDetailDTO> odetail = placeorderdto.getOrderdetail();
            for (OrderDetailDTO od : odetail) {
                isAdded = odetaildao.add(new OrderDetailEntity(od.getOrderId(), od.getIndex(), od.getItemId(), od.getBadgeId(), od.getQty(), od.getAmount()));
                System.out.println("add orderdetail : "+isAdded);
                if(isAdded){
                    isAdded  = itemdao.itemQtyChange(od.getItemId(), od.getQty());
                    if(!isAdded){
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }else{
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
                connection.commit();
                connection.setAutoCommit(true);
                return true;

        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    @Override
    public ArrayList<OrderDTO> getSkipOrders() throws Exception {
        ArrayList<OrderEntity> alloders = orderdao.getAllSkipPayments();
        ArrayList<OrderDTO> ad = new ArrayList<>();
        for (OrderEntity ao : alloders) {
            ad.add(new OrderDTO(ao.getOrderId(),ao.getOrderdate(),ao.getTotalprice(),ao.getState()));
        }
        return ad;
    }


    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {
        ArrayList<OrderEntity> alloders = orderdao.getAll();
        ArrayList<OrderDTO> ad = new ArrayList<>();
        for (OrderEntity ao : alloders) {
            ad.add(new OrderDTO(ao.getOrderId(),ao.getOrderdate(),ao.getTotalprice(),ao.getState()));
        }
        return ad;
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        ArrayList<OrderDetailEntity> orderdetail = odetaildao.getAllCompatibleOrderDetils(id);
        for (OrderDetailEntity ode : orderdetail) {
            ItemEntity item = new ItemEntity(ode.getItemId(),ode.getQty());
            boolean isOK = itemdao.addOrderDeleteItemQty(item);
            if(isOK){
                String stockid = null;
                try {
                    stockid = idgenerator.generateID(idgenerator.IDTypes.STOCK);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);
                StockRefillEntity srfe = new StockRefillEntity(date,stockid,ode.getItemId(),ode.getBadgeId(),ode.getQty());
                isOK = stockdao.add(srfe);
                if(!isOK){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    System.out.println("stockadd 10");
                    return false;
                }
            }else{
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("stockadd 10");
                return false;
            }
        }
        boolean isOk = orderdao.delete(id);
        if(!isOk){
            connection.rollback();
            connection.setAutoCommit(true);
            System.out.println("stockadd 10");
            return false;
        }else{
            connection.commit();
            connection.setAutoCommit(true);
            System.out.println("stockadd 11");
            return true;
        }
    }

    @Override
    public ArrayList<String> isOrderIdAvailble(String id) throws Exception {
        return orderdao.isOrderIdAvailable(id);
    }

    @Override
    public ArrayList<OrderDetailDTO> getAllOrdersDetails(String id) throws Exception {
        ArrayList<OrderDetailEntity> orderdetails = odetaildao.getAllCompatibleOrderDetils(id);
        ArrayList<OrderDetailDTO> allorderDetail = new ArrayList<>();
        for(OrderDetailEntity ode : orderdetails){
            allorderDetail.add(new OrderDetailDTO(ode.getOrderId(),ode.getIndex(),ode.getItemId(),ode.getBadgeId(),ode.getQty(),ode.getAmount()));
        }
        return allorderDetail;
    }

    @Override
    public int getSaleCount() throws Exception {
        return orderdao.completeOrderCount();
    }
}