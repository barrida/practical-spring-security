package com.practical.spring.security.reactive.web.client.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suleyman Yildirim
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private String id;
    private String name;
    private double price;
    private Currency currency;
    private String owner;
}
