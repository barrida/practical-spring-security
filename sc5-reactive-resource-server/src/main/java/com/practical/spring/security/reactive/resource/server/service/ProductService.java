package com.practical.spring.security.reactive.resource.server.service;

import com.practical.spring.security.reactive.resource.server.model.Product;
import com.practical.spring.security.reactive.resource.server.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Suleyman Yildirim
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Flux<Product> processProducts(double price) {
        var products = this.findAll();
        return products.filter(product -> product.getPrice() > price);
    }

}



