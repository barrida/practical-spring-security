package com.practical.spring.security.basicauth.repository;

import com.practical.spring.security.basicauth.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Suleyman Yildirim
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
