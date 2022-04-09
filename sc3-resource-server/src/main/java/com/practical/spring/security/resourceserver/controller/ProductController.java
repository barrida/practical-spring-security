package com.practical.spring.security.resourceserver.controller;

import com.practical.spring.security.resourceserver.entity.ProductResponse;
import com.practical.spring.security.resourceserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Suleyman Yildirim
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    public ResponseEntity<ProductResponse> getProducts(Authentication authentication) {
        var products = productService.findAll();
        return ResponseEntity.ok(ProductResponse.builder().productList(products).authentication(authentication).build());
    }

    @GetMapping("/updateProducts")
    @PreAuthorize("hasAuthority('SCOPE_WRITE')")
    public ResponseEntity<ProductResponse> updateProducts(Authentication authentication, @RequestParam double price) {
        var processedProducts = productService.processProducts(price);
        return ResponseEntity.ok(ProductResponse.builder().productList(processedProducts).authentication(authentication).build());

    }

}
