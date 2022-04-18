package com.practical.spring.security.reactive.resource.server.controller;

import com.practical.spring.security.reactive.resource.server.model.Product;
import com.practical.spring.security.reactive.resource.server.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    public Flux<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/updateProducts")
    @PreAuthorize("hasAuthority('SCOPE_WRITE')")
    public Flux<Product> updateProducts(@RequestParam double price) {
        return productService.processProducts(price);
    }

}