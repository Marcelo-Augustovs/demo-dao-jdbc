package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBc;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBc(DB.getConnection());
    }
}
