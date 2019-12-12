package dao.custom;

import dao.CrudDAO;
import entity.OrderDetailEntity;

import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetailEntity,String>{
    public ArrayList<OrderDetailEntity>getAllCompatibleOrderDetils(String id)throws Exception;
}
