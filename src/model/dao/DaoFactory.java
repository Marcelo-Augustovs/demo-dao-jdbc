package model.dao;

import model.dao.impl.SellerDaoJDBc;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBc();
    }
}
