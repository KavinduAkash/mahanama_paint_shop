package dao.custom;

import dao.CrudDAO;
import entity.BadgeEntity;
import entity.BrandEntity;
import entity.CustomEntity;
import entity.StockRefillEntity;

import java.util.ArrayList;

public interface StockRefillDAO extends CrudDAO<StockRefillEntity,String> {
    public ArrayList<BadgeEntity> getAllBadges()throws Exception;

}
