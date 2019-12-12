package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QuaryDAO;
import entity.BadgeEntity;
import entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class QuaryDAOImpl implements QuaryDAO {

    @Override
    public ArrayList<CustomEntity> getAllItem() throws Exception {
        String sql = "SELECT i.itemId,br.brandName,cat.catName,i.itemName,i.itemDescription,co.colorName,i.mesure,i.handOnQty " +
                "FROM item i,catogary cat,brand br,color co " +
                "WHERE i.brandId = br.brandId AND i.catId = cat.catId AND i.colorId = co.colorId";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> allitem = new ArrayList<>();
        while(rst.next()){
            allitem.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),Integer.parseInt(rst.getString(8))));
        }
        return allitem;
    }

    @Override
    public CustomEntity getSelect(String t) throws Exception {
        t = "'"+t+"'";
        String sql = "SELECT i.itemId,cat.catId,cat.catName,br.brandId,br.brandName,i.itemName,i.itemDescription,co.colorId,co.colorName,i.mesure,i.handOnQty" +
                     " FROM item i,catogary cat,brand br,color co "+
                     "WHERE i.brandId = br.brandId AND i.catId = cat.catId AND i.colorId = co.colorId AND i.itemId = "+t+"";

        ResultSet rst = CrudUtil.executeQuery(sql);
        CustomEntity cusentity = null;
        while(rst.next()){
            cusentity = new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10),Integer.parseInt(rst.getString(11)));
        }
        return cusentity;
    }

    //------getAllStock---------------------------------------------------------------------------------------------------------
    @Override
    public ArrayList<CustomEntity> getAllStock() throws Exception {
        String sql = "SELECT s.addDate,s.stockId,bg.badgeId,bg.buyPrice,bg.salePrice,bg.expDate,i.itemId,i.itemName,co.colorName,i.mesure,s.qty FROM stock s,badge bg,item i,color co WHERE s.badgeId = bg.badgeId AND s.itemId = i.itemId AND i.colorId = co.colorId";

        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> allstock = new ArrayList<>();
        while(rst.next()){
            allstock.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getDouble(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10),rst.getInt(11)));
            System.out.println(rst.getInt(11));
        }
        return allstock;
    }

    @Override
    public ArrayList<CustomEntity> getSearchItem(String ID) throws Exception {
        String sql = "SELECT i.itemId,br.brandName,cat.catName,i.itemName,i.itemDescription,co.colorName,i.mesure,i.handOnQty" +
                " FROM item i,catogary cat,brand br,color co " +
                "WHERE i.brandId = br.brandId AND i.catId = cat.catId AND i.colorId = co.colorId AND i.itemId LIKE "+ID+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> allSearchItems = new ArrayList<>();
        while(rst.next()){
            allSearchItems.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),Integer.parseInt(rst.getString(8))));
        }
        return allSearchItems;

    }

    @Override
    public ArrayList<CustomEntity> getSearchItem(String N, String c) throws Exception {
        String sql = "SELECT i.itemId,br.brandName,cat.catName,i.itemName,i.itemDescription,co.colorName,i.mesure,i.handOnQty" +
                " FROM item i,catogary cat,brand br,color co " +
                "WHERE i.brandId = br.brandId AND i.catId = cat.catId AND i.colorId = co.colorId AND (i.itemName LIKE "+N+" OR cat.catName LIKE "+N+" OR br.brandName LIKE "+N+" ) AND co.colorName LIKE "+c+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> allSearchItems = new ArrayList<>();
        while(rst.next()){
            allSearchItems.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),Integer.parseInt(rst.getString(8))));
        }
        return allSearchItems;
    }

    @Override
    public ArrayList<CustomEntity> searchByItemId(String itemId) throws Exception {
        String sql = "SELECT bg.badgeId,bg.expDate,bg.salePrice,s.qty FROM badge bg,stock s WHERE s.badgeId = bg.badgeId AND s.itemId ="+"'"+itemId+"'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> badges = new ArrayList<>();
        while(rst.next()){
            System.out.println((rst.getString(1)+""));
            badges.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4)));

        }
        return badges;
    }

    @Override
    public double thisMonthlyProfit() throws Exception {
        String sql = "SELECT SUM((bdg.salePrice-bdg.buyPrice)*od.qty) FROM customerorder o,orderdetail od,badge bdg WHERE o.orderId = od.orderId AND bdg.badgeId = od.badgeId AND o.state = 'Completed' AND  MONTH(CURDATE())=MONTH(o.date) AND YEAR(CURDATE())=YEAR(o.date)";
        ResultSet rst = CrudUtil.executeQuery(sql);
        double thismonthprofit = 0;
        while(rst.next()){
            thismonthprofit = rst.getDouble(1);
        }
        return thismonthprofit;
    }

    @Override
    public double todayProfit() throws Exception {
        String sql = "SELECT SUM((bdg.salePrice-bdg.buyPrice)*od.qty) FROM customerorder o,orderdetail od,badge bdg WHERE o.orderId = od.orderId AND bdg.badgeId = od.badgeId AND o.state = 'Completed' AND CURDATE()=o.date";
        ResultSet rst = CrudUtil.executeQuery(sql);
        double todayprofit = 0;
        while(rst.next()){
            todayprofit = rst.getDouble(1);
        }
        return todayprofit;
    }

    @Override
    public int getExpObjectCount() throws Exception {
       String sql = " SELECT count(s.qty) FROM stock s,badge b WHERE s.badgeId = b.badgeId AND b.expDate>CURDATE()";
       ResultSet rst = CrudUtil.executeQuery(sql);
       int count = 0;
       while(rst.next()){
           count = rst.getInt(1);
       }
        return count;
    }

    @Override
    public ArrayList<CustomEntity> getAllSearchStock(String date, String anyid) throws Exception {
        String sql = "SELECT s.addDate,s.stockId,bg.badgeId,bg.buyPrice,bg.salePrice,bg.expDate,i.itemId,i.itemName,co.colorName,i.mesure,s.qty FROM stock s,badge bg,item i,color co WHERE s.badgeId = bg.badgeId AND s.itemId = i.itemId AND i.colorId = co.colorId AND s.addDate LIKE "+date+" AND s.stockId LIKE "+anyid+" OR bg.badgeId LIKE "+anyid+" OR i.itemId LIKE "+anyid+"";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> allstock = new ArrayList<>();
        while(rst.next()){
            allstock.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),rst.getDouble(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10),rst.getInt(11)));
            System.out.println(rst.getInt(11));
        }
        return allstock;
    }

    @Override
    public ArrayList<CustomEntity> getMostMoveItem() throws Exception {
        String sql = "SELECT i.itemId,br.brandName,cat.catName,i.itemName,i.itemDescription,co.colorName,i.mesure,i.handOnQty,count(od.itemId) FROM item i,catogary cat,brand br,color co,customerorder cuso,orderdetail od WHERE i.brandId = br.brandId AND i.catId = cat.catId AND i.colorId = co.colorId AND od.itemId = i.itemId AND cuso.orderId = od.orderId  GROUP BY od.itemId ORDER BY count(od.itemId) desc LIMIT 20";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<CustomEntity> MostMoveitem = new ArrayList<>();
        while(rst.next()){
            MostMoveitem.add(new CustomEntity(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),Integer.parseInt(rst.getString(8))));
        }
        return MostMoveitem;
    }

    @Override
    public ArrayList<CustomEntity> getLessMoveItem() throws Exception {
       // String sql = "SELECT*FROM i.itemId,br.brandName,cat.catName,i.itemName,i.i"
        return null;
    }
}