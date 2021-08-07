package com.example.springdemo.service;

import com.example.springdemo.dao.ProductDao;
import com.example.springdemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Integer id){
        return productDao.findByProductId(id);
    }

    @Override
    public Product findByName(String name){
        return productDao.findByProductName(name);
    }

    @Override
    public void saveProduct(Product product){
        productDao.save(product);
    }

    @Override
    public void deleteProduct(Integer id){
        productDao.deleteById(id);
    }

    @Override
    public Page<Product> findMarker(Pageable pageable){
        return productDao.findAll(pageable);
    }
}
