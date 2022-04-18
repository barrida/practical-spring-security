package com.practical.spring.security.reactive.resource.server.model.repository;

import com.practical.spring.security.reactive.resource.server.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Suleyman Yildirim
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, Integer> {
}
