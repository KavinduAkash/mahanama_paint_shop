package bo.custom;

import bo.SuperBO;
import dto.ReturnDTO;

import java.util.ArrayList;

public interface ReturnBO extends SuperBO{
    public String getLastId()throws Exception;
    public boolean makeReturnToShop(ReturnDTO dto)throws Exception;
    public ArrayList<ReturnDTO>getAllReturns()throws Exception;
}
