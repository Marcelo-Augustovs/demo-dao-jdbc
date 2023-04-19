package application;

import entities.Department;
import entities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: seller findById ===");
        Seller seller = sellerDao.findyById(3);
        System.out.println(seller);

        System.out.println("\n=== Test 2: seller findByDepartment ===");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n=== Test 3: seller findAll ===");
        list = sellerDao.findAll();
        for(Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n=== Test 4: seller insert ===");
        Seller newSeller = new Seller(null,"Greg","greg@gmail.com",new Date(),4000.00,department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());

        System.out.println("\n=== Test 5: seller update ===");
        seller = sellerDao.findyById(1);
        seller.setName("Martha Wane");
        sellerDao.update(seller);
        System.out.println("Update Completed");
    }
}
