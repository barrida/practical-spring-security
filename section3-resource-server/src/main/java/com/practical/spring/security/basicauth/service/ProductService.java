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
import java.util.stream.Collectors;

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

    @PostFilter("filterObject.owner == authentication.name")
    public List<Product> processProducts(double price) {
        var products = this.findAll();
        return products.stream()
                .filter(product -> product.getPrice() > price)
                .collect(Collectors.toList());
    }

}



