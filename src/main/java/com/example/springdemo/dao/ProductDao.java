package com.example.springdemo.dao;

import com.example.springdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
    Product findByProductName(String name);

    Product findByProductId(Integer id);
}
