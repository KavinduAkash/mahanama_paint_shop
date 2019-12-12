package bo.custom;

import bo.SuperBO;

public interface PDashboardBO extends SuperBO {
    public double thisMonthlyProfit()throws Exception;
    public double todayProfit()throws Exception;
    public int getExpDateCount()throws Exception;
    public int getSkipPaymentCount()throws Exception;
    public int getCompletePaymentCount()throws Exception;
    public int getShopReturnCount()throws Exception;
    public int getStockHallReturnCount()throws Exception;
    public int getExpObjectCount()throws Exception;
}
