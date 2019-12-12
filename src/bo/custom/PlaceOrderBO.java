package bo.custom;

import bo.SuperBO;
import dto.*;

import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    public ArrayList<CustomDTO> getAllItem()throws Exception;
    public ArrayList<CustomDTO> searchItem(String value)throws Exception;
    public ArrayList<CustomDTO> searchItem(String value1,String value2)throws Exception;
    public ArrayList<CustomDTO> getCompatibleBadges(String value)throws Exception;
    public String getLastId()throws Exception;
    public boolean addOrder(PlaceOrderDTO placeOrderDTO)throws Exception;
    public ArrayList<OrderDTO> getSkipOrders()throws Exception;
    public ArrayList<OrderDTO> getAllOrders()throws Exception;
    public boolean deleteOrder(String id)throws Exception;
    public ArrayList<String> isOrderIdAvailble(String id)throws Exception;
    public ArrayList<OrderDetailDTO> getAllOrdersDetails(String id)throws Exception;
    public int getSaleCount()throws Exception;
}
