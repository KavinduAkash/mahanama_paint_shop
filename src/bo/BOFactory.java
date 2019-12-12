package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;


    public BOFactory() {
    }
    public static BOFactory getInstance(){
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes {
        COLOR,CATOGARY,BRAND,BADGE,ITEM,STOCKREFILL,PLACEORDER,PAYMENT,RETURN,DASH;

    }
    public SuperBO getBO(BOTypes types){
        switch(types){
            case COLOR:
                return new ColorBOImpl();
            case CATOGARY:
                return new CatBOImpl();
            case BRAND:
                return new BrandBOImpl();
            case BADGE:
                return new BadgeBOImpl();
            case ITEM:
                return new ManageItemBOImpl();
            case STOCKREFILL:
                return new StockRefillBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case RETURN:
                return new ReturnBOImpl();
            case DASH:
                return new PDashboardBOImpl();
             default:
                 return null;
        }
    }
}
