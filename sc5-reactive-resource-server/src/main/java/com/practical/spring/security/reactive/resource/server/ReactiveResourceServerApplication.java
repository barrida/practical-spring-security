package com.practical.spring.security.reactive.resource.server;

import com.practical.spring.security.reactive.resource.server.model.Currency;
import com.practical.spring.security.reactive.resource.server.model.Product;
import com.practical.spring.security.reactive.resource.server.model.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveResourceServerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, ProductRepository productRepository) {
		return args -> {
			Flux<Product> productFlux = Flux.just(
							new Product(null, "Chicken", 5, Currency.GBP, "suleyman"),
							new Product(null, "Beef", 15, Currency.GBP, "canan"),
							new Product(null, "Apple", 3, Currency.GBP, "fatma"),
							new Product(null, "Rice", 4, Currency.GBP, "suleyman"),
							new Product(null, "Fish", 8, Currency.GBP, "suleyman")
					)
					.flatMap(productRepository::save);

			productFlux
					.thenMany(productRepository.findAll())
					.subscribe(System.out::println);
		};
	}

}
