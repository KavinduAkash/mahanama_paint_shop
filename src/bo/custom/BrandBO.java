package bo.custom;

import bo.SuperBO;
import dto.BrandDTO;


import java.util.ArrayList;

public interface BrandBO extends SuperBO {
    public boolean addBrand(BrandDTO brand)throws Exception;
    public boolean updateBrand(BrandDTO brand)throws Exception;
    public boolean deleteBrand(String id)throws Exception;
    public ArrayList<BrandDTO> getSearchBrands(String value) throws Exception;
    public ArrayList<BrandDTO> getAllBrands() throws Exception;
    public String getLastId()throws Exception;
}
