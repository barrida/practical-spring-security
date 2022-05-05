package com.practical.spring.security.basicauth.controller;

import com.practical.spring.security.basicauth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Suleyman Yildirim
 */
@Controller
public class MainController {

    @Autowired
    private ProductService productService;


    @GetMapping("/main")
    @PreAuthorize("hasAuthority('READ')")
    public String main(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", productService.findAll());
        return "main.html";
    }

    @GetMapping("/update")
    public String processProduct(Authentication authentication, Model model) {
        var products = productService.findAll();
        final var products1 = productService.processProducts(products);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", products1);
        return "main.html";
    }

}
