package bo.custom.impl;

import bo.custom.BrandBO;
import dao.DAOFactory;
import dao.custom.BrandDAO;
import dto.BrandDTO;
import entity.BrandEntity;

import java.util.ArrayList;

public class BrandBOImpl implements BrandBO {

    BrandDAO dao = (BrandDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRAND);

    @Override
    public boolean addBrand(BrandDTO brand) throws Exception {
        return dao.add(new BrandEntity(brand.getBrandId(),brand.getBrandname(),brand.getCountry(),brand.getBranddesc()));
    }

    @Override
    public boolean updateBrand(BrandDTO brand) throws Exception {
        return dao.add(new BrandEntity(brand.getBrandId(),brand.getBrandname(),brand.getCountry(),brand.getBranddesc()));
    }

    @Override
    public boolean deleteBrand(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public ArrayList<BrandDTO> getSearchBrands(String value) throws Exception {
        ArrayList<BrandEntity> searchbrand = dao.search(value);
        ArrayList<BrandDTO> searchvalue = new ArrayList<>();
        for (BrandEntity be : searchbrand) {
            searchvalue.add(new BrandDTO(be.getBrandId(),be.getBrandName(),be.getCountry(),be.getDescription()));
        }
        return searchvalue;
    }

    @Override
    public ArrayList<BrandDTO> getAllBrands() throws Exception {
        ArrayList<BrandDTO> all = new ArrayList<>();
        ArrayList<BrandEntity> allbrands = dao.getAll();
        for (BrandEntity be : allbrands) {
            all.add(new BrandDTO(be.getBrandId(),be.getBrandName(),be.getCountry(),be.getDescription()));
        }
        return all;
    }

    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }
}
