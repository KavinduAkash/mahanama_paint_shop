package bo.custom;

import bo.SuperBO;
import dto.CatDTO;

import java.util.ArrayList;

public interface CatBO extends SuperBO {
    public boolean addCatgory(CatDTO catdto) throws Exception;

    public boolean updateCatogary(CatDTO catdto) throws Exception;

    public boolean deleteCatogary(String ID) throws Exception;

    public ArrayList<CatDTO> getSearchValues(String value) throws Exception;

    public ArrayList<CatDTO> getAllCatogary() throws Exception;

    public String getLastId()throws Exception;
}
