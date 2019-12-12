package bo.custom.impl;

import bo.custom.BadgeBO;
import bo.custom.PDashboardBO;
import dao.DAOFactory;
import dao.custom.BadgeDAO;
import dao.custom.OrderDAO;
import dao.custom.QuaryDAO;
import dao.custom.ReturnDAO;

public class PDashboardBOImpl implements PDashboardBO{
    QuaryDAO qdao = (QuaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUARY);
    BadgeDAO badgedao = (BadgeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BADGE);
    OrderDAO orderdao = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    ReturnDAO returndao = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN);

    @Override
    public double thisMonthlyProfit() throws Exception {
        return qdao.thisMonthlyProfit();
    }

    @Override
    public double todayProfit() throws Exception {
        return qdao.todayProfit();
    }

    @Override
    public int getExpDateCount() throws Exception {
        return badgedao.expBadgeCount();
    }

    @Override
    public int getSkipPaymentCount() throws Exception {
        return orderdao.getSkipOrderCount();
    }

    @Override
    public int getCompletePaymentCount() throws Exception {
        return orderdao.completeOrderCount();
    }

    @Override
    public int getShopReturnCount() throws Exception {
        return returndao.getShopReturnCount();
    }

    @Override
    public int getStockHallReturnCount() throws Exception {
        return returndao.getStockHallReturnCount();
    }

    @Override
    public int getExpObjectCount() throws Exception {
        return qdao.getExpObjectCount();
    }
}
