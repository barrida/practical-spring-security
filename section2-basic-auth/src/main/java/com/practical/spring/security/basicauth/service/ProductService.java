package com.practical.spring.security.basicauth.service;

import com.practical.spring.security.basicauth.entity.Product;
import com.practical.spring.security.basicauth.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Suleyman Yildirim
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    //@PreFilter("filterObject.owner == authentication.name")
    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> processProducts(List<Product> products) {
        // do something with the products list
        return products;
    }

}



