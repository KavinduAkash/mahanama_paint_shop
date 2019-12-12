package dao.custom;

import dao.CrudDAO;
import entity.BadgeEntity;

import java.util.ArrayList;

public interface BadgeDAO extends CrudDAO<BadgeEntity, String>{
    public ArrayList<BadgeEntity> search(String t1,String t2,String t3)throws Exception;
    public ArrayList<BadgeEntity> isBadgeAvailable(String id)throws Exception;
    public int expBadgeCount()throws Exception;


}
