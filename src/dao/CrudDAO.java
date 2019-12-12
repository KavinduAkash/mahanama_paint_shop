package dao;

import dto.ColorDTO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDAO{
    public String getLastId() throws Exception;
    public boolean add(T t) throws Exception;
    public boolean update(T t)throws Exception;
    public boolean delete(ID t)throws Exception;
    public ArrayList<T> search(ID t)throws Exception;
    public ArrayList<T> getAll() throws Exception;
}
