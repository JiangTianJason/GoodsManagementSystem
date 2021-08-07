package com.example.springdemo.service;

import com.example.springdemo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product findById(Integer id);
    Product findByName(String name);
    void saveProduct(Product product);
    void deleteProduct(Integer id);
    Page<Product> findMarker(Pageable pageable);
}
