package bo.custom;

import bo.SuperBO;
import dto.ColorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ColorBO extends SuperBO {
    public boolean addColor(ColorDTO customer) throws SQLException,ClassNotFoundException, Exception;

    public boolean updateColor(ColorDTO customer) throws SQLException,ClassNotFoundException,Exception;

    public boolean deleteColor(String ID) throws SQLException,ClassNotFoundException,Exception;

    public ArrayList<ColorDTO> searchColor(String value)throws Exception;

    public ArrayList<ColorDTO> getAllColors()throws Exception;

    public String getLastId()throws Exception;
}
