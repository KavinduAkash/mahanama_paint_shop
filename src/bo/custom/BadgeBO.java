package bo.custom;

import bo.SuperBO;
import dto.BadgeDTO;

import java.util.ArrayList;

public interface BadgeBO extends SuperBO {
    public boolean addBadge(BadgeDTO badgeDTO)throws Exception;
    public boolean updateBadge(BadgeDTO badgeDTO)throws Exception;
    public boolean deleteBadge(String id)throws Exception;
    public ArrayList<BadgeDTO> getSearchBadge(String value) throws Exception;
    public ArrayList<BadgeDTO> getSearchBadge(String value1,String value2,String value3) throws Exception;
    public ArrayList<BadgeDTO> getAllbadge() throws Exception;
    public String getLastId()throws Exception;
    public ArrayList<BadgeDTO>isAvailableBadge(String id)throws Exception;
}
