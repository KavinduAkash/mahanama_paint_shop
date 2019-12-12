package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }
    public static DAOFactory getInstance(){
        if(null == daoFactory){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        COLOR,CATOGARY,BRAND,BADGE,ITEM,QUARY,STOCKREFILL,ORDER,ORDERDETAIL,PAYMENT,RETURN;
    }

    public SuperDAO getDAO(DAOTypes daotype){
        switch (daotype){
            case COLOR:
                return new ColorDAOImpl();
            case CATOGARY:
                return new CatDAOImpl();
            case BRAND:
                return new BrandDAOImpl();
            case BADGE:
                return new BadgeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case QUARY:
                return new QuaryDAOImpl();
            case STOCKREFILL:
                return new StockRefillDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case RETURN:
                return new ReturnDAOImpl();
            default:
                return null;
        }
    }
}
