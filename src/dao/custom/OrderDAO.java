package dao.custom;

import dao.CrudDAO;
import entity.OrderEntity;

import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<OrderEntity,String> {
    public ArrayList<OrderEntity> getAllSkipPayments()throws Exception;
    public boolean setState(OrderEntity orderEntity)throws Exception;
    public ArrayList<String> isOrderIdAvailable(String id)throws Exception;
    public int completeOrderCount()throws Exception;
    public int getSkipOrderCount()throws Exception;
}
