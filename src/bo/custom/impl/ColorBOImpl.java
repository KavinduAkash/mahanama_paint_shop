package bo.custom.impl;

import bo.SuperBO;
import bo.custom.ColorBO;
import dao.DAOFactory;
import dao.custom.ColorDAO;
import dto.ColorDTO;
import entity.ColorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ColorBOImpl implements ColorBO {


    ColorDAO dao= (ColorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COLOR);

    @Override
    public boolean addColor(ColorDTO customer) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new ColorEntity(customer.getColorId(),customer.getColorname()));
    }

    @Override
    public boolean updateColor(ColorDTO customer) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new ColorEntity(customer.getColorId(),customer.getColorname()));
    }

    @Override
    public boolean deleteColor(String ID) throws SQLException, ClassNotFoundException, Exception {
        return dao.delete(ID);
    }

    @Override
    public ArrayList<ColorDTO> searchColor(String value) throws Exception {
        ArrayList<ColorEntity>likecolor = dao.search(value);
        ArrayList<ColorDTO> color = new ArrayList<>();
        for(ColorEntity c : likecolor){
            color.add(new ColorDTO(c.getColorId(),c.getColorName()));
        }
        return color;
    }

    @Override
    public ArrayList<ColorDTO> getAllColors() throws Exception {
        ArrayList<ColorDTO> allcolors = new ArrayList<>();
        ArrayList<ColorEntity> all = dao.getAll();
        for(ColorEntity color : all){
            allcolors.add(new ColorDTO(color.getColorId(),color.getColorName()));
        }
        return allcolors;
    }

    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }

}
