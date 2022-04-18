package com.practical.spring.security.reactive.web.client.controller;

import com.practical.spring.security.reactive.web.client.config.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

/**
 * @author suleyman.yildirim
 */
@RestController
public class OAuth2ClientController {

    @Value("${products.uri}")
    private String productUri;

    @Autowired
    private WebClient webClient;

    @GetMapping("/")
    public Flux<Product> getProducts(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient authorizedClient) {

        return this.webClient.get()
                .uri(productUri)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToFlux(Product.class);
    }
}
