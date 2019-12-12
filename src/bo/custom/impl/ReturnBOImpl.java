package bo.custom.impl;

import bo.BOFactory;
import bo.custom.ReturnBO;
import bo.custom.StockRefillBO;
import controller.ReturnsController;
import dao.DAOFactory;
import dao.custom.ReturnDAO;
import db.DBConnection;
import dto.ReturnDTO;
import dto.StockRefillDTO;
import entity.ReturnEntity;
import generator.idgenerator;

import java.sql.Connection;
import java.util.ArrayList;

public class ReturnBOImpl implements ReturnBO{

    ReturnDAO dao = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN);
    static StockRefillBO stockrefillbo = (StockRefillBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCKREFILL);


    @Override
    public String getLastId() throws Exception {
        return dao.getLastId();
    }

    @Override
    public boolean makeReturnToShop(ReturnDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        System.out.println("here check : "+dto.getReturnMethod());
        ReturnEntity returnEntity = new ReturnEntity(dto.getCusreturnId(),dto.getReturndate(),dto.getOrderId(),dto.getItemID(),dto.getBadgeId(),dto.getReturnqty(),dto.getReturnamount(),dto.getReason(),dto.getReturnMethod());
        boolean isAdded = dao.add(returnEntity);
        System.out.println("ok1"+isAdded);
        if(isAdded){
            try {
                isAdded = ReturnBOImpl.addNewStock(dto.getStock());
                System.out.println("ok1.1"+isAdded);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!isAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("stockadd 10");
                return false;
            }
            connection.setAutoCommit(true);
            System.out.println("stockadd 11");
            return true;
        }else{
            connection.rollback();
            connection.setAutoCommit(true);
            System.out.println("stockadd 12");
            return false;
        }
    }

    @Override
    public ArrayList<ReturnDTO> getAllReturns() throws Exception {
        ArrayList<ReturnEntity> allreturns = dao.getAll();
        ArrayList<ReturnDTO> all = new ArrayList<>();
        for (ReturnEntity re : allreturns){
            all.add(new ReturnDTO(re.getCusreturnId(),re.getReturndate(),re.getOrderId(),re.getItemID(),re.getBadgeId(),re.getReturnqty(),re.getReturnamount(),re.getReason(),re.getReturnMethod()));
        }
        return all;
    }

    private static boolean addNewStock(StockRefillDTO sto) throws Exception {
        System.out.println("stockadd 4");
        return stockrefillbo.addStock(sto);
    }
}