package bo.custom.impl;

import bo.custom.CatBO;
import dao.DAOFactory;
import dao.custom.CatDAO;
import dto.CatDTO;
import entity.CatEntity;

import java.util.ArrayList;

public class CatBOImpl implements CatBO{

    CatDAO dao = (CatDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATOGARY);

    @Override
    public boolean addCatgory(CatDTO catdto) throws Exception {
        return dao.add(new CatEntity(catdto.getCatId(),catdto.getCatname(),catdto.getCatdesc()));
    }

    @Override
    public boolean updateCatogary(CatDTO catdto) throws Exception {
       return dao.update(new CatEntity(catdto.getCatId(),catdto.getCatname(),catdto.getCatdesc()));
    }

    @Override
    public boolean deleteCatogary(String ID) throws Exception {
        return dao.delete(ID);
    }

    @Override
    public ArrayList<CatDTO> getSearchValues(String value) throws Exception {
        ArrayList<CatDTO> search = new ArrayList<>();
        ArrayList<CatEntity> searchvalue =  dao.search(value);
        for (CatEntity ce : searchvalue) {
            search.add(new CatDTO(ce.getCatId(),ce.getCatName(),ce.getCatDesc()));
        }
        return search;
    }

    @Override
    public ArrayList<CatDTO> getAllCatogary() throws Exception {
       ArrayList<CatDTO> all = new ArrayList<>();
        ArrayList<CatEntity> allcat = dao.getAll();
       for(CatEntity ac : allcat){
            all.add(new CatDTO(ac.getCatId(),ac.getCatName(),ac.getCatDesc()));
       }
       return all;
    }

    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }
}
