package model.dao;

import entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findyById(Integer id);
    List<Seller> findAll();

}
