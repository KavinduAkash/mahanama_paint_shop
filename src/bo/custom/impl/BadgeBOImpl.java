package bo.custom.impl;

import bo.custom.BadgeBO;
import dao.DAOFactory;
import dao.custom.BadgeDAO;
import dto.BadgeDTO;
import entity.BadgeEntity;

import java.util.ArrayList;

public class BadgeBOImpl implements BadgeBO{

    BadgeDAO dao = (BadgeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BADGE);

    @Override
    public boolean addBadge(BadgeDTO badgeDTO) throws Exception {
        System.out.println("p4");
        return dao.add(new BadgeEntity(badgeDTO.getBadgeid(),badgeDTO.getBuyprice(),badgeDTO.getExpdate(),badgeDTO.getSaleprice()));
    }

    @Override
    public boolean updateBadge(BadgeDTO badgeDTO) throws Exception {
        return dao.update(new BadgeEntity(badgeDTO.getBadgeid(),badgeDTO.getBuyprice(),badgeDTO.getExpdate(),badgeDTO.getSaleprice()));
    }

    @Override
    public boolean deleteBadge(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public ArrayList<BadgeDTO> getSearchBadge(String value) throws Exception {
        ArrayList<BadgeEntity> badges =  dao.search(value);
        ArrayList<BadgeDTO> search = new ArrayList<>();
        for (BadgeEntity be : badges) {
            search.add(new BadgeDTO(be.getBadgeId(),be.getBuyPrice(),be.getExpDate(),be.getSalePrice()));
        }
        return search;
    }

    @Override
    public ArrayList<BadgeDTO> getSearchBadge(String value1, String value2, String value3) throws Exception {
        ArrayList<BadgeEntity> badges =  dao.search(value1,value2,value3);
        System.out.println("inside badgebo : "+badges);
        ArrayList<BadgeDTO> search = new ArrayList<>();
        for (BadgeEntity be : badges) {
            search.add(new BadgeDTO(be.getBadgeId(),be.getBuyPrice(),be.getExpDate(),be.getSalePrice()));
        }
        return search;
    }

    @Override
    public ArrayList<BadgeDTO> getAllbadge() throws Exception {
        ArrayList<BadgeEntity> allbadge = dao.getAll();
        ArrayList<BadgeDTO> all = new ArrayList<>();
        for (BadgeEntity be : allbadge) {
            all.add(new BadgeDTO(be.getBadgeId(),be.getBuyPrice(),be.getExpDate(),be.getSalePrice()));
        }
        return all;
    }

    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }

    @Override
    public ArrayList<BadgeDTO> isAvailableBadge(String id) throws Exception {
        ArrayList<BadgeEntity> badgedata= dao.isBadgeAvailable(id);
        ArrayList<BadgeDTO> available = new ArrayList<>();
        for (BadgeEntity be : badgedata) {
            available.add(new BadgeDTO(be.getBadgeId(),be.getBuyPrice(),be.getExpDate(),be.getSalePrice()));
        }
        return available;
    }
}
