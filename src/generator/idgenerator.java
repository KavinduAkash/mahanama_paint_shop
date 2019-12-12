package generator;

import bo.BOFactory;
import bo.custom.*;
import dao.DAOFactory;

import java.text.DecimalFormat;
import java.util.Arrays;

public class idgenerator {

    static ColorBO colorbo = (ColorBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COLOR);
    static PlaceOrderBO orderbo = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    static PaymentBO paybo = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    static CatBO catbo = (CatBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATOGARY);
    static BrandBO brandbo = (BrandBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRAND);
    static BadgeBO badgebo = (BadgeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BADGE);
    static ManageItemBO itembo = (ManageItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    static StockRefillBO stockbo = (StockRefillBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCKREFILL);
    static ReturnBO returnbo = (ReturnBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURN);

    private static String intToString(int num, int digits) {
        assert digits > 0 : "Invalid number of digits";

        // create variable length array of zeros
        char[] zeros = new char[digits];
        Arrays.fill(zeros, '0');
        // format number as String
        DecimalFormat df = new DecimalFormat(String.valueOf(zeros));

        return df.format(num);
    }

    public enum IDTypes {
        COLOR, CATOGARY, ORDER, PAYMENT, BRAND, BADGE, ITEM, STOCK, RETURN;
    }

    public static String generateID(IDTypes type) throws Exception {

        // ResultSet rst = DBconnection.getInstance().getConnection().createStatement().executeQuery("SELECT oid From orde order by oid desc");
        String id = getIt(type);
        if (null != id) {
            String[] part = id.split("(?<=\\D)(?=\\d)");
            System.out.println(part[0]);
            System.out.println("part[0] : " + part[0]);
            System.out.println("part[1] : " + part[1]);
            int stringLength = (int) (part[1].length());
            System.out.println("stringLength : " + stringLength);
            int number = Integer.parseInt(part[1]);
            //System.out.println("New Id : "+part[0]+intToString(number+1, stringLength));
            System.out.println("New Id : ok");
            return part[0] + intToString(number + 1, stringLength);
        }
        switch (type) {
            case COLOR:
                return "COL001";
            case ORDER:
                return "ORDER001";
            case PAYMENT:
                return "PAY001";
            case CATOGARY:
                return "CAT001";
            case BRAND:
                return "BRAN001";
            case BADGE:
                return "BADGE001";
            case ITEM:
                return "ITEM001";
            case STOCK:
                return "STOCK001";
            case RETURN:
                return "RETURN001";
            default:
                return null;
        }
    }

    private static String getIt(IDTypes type) throws Exception {
        switch (type) {
            case COLOR:
                return colorbo.getLastId();
            case ORDER:
                return orderbo.getLastId();
            case PAYMENT:
                return paybo.getLastId();
            case CATOGARY:
                return catbo.getLastId();
            case BRAND:
                return brandbo.getLastId();
            case BADGE:
                return badgebo.getLastId();
            case ITEM:
                return itembo.getLastId();
            case STOCK:
                return stockbo.getLastId();
            case RETURN:
                return returnbo.getLastId();
            default:
                return null;
        }

    }
}
