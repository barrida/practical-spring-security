package com.practical.spring.security.reactive.resource.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@SpringBootTest
@AutoConfigureWebTestClient
class ReactiveResourceServerApplicationTests {

    @Autowired
    private WebTestClient client;

    @Test
    @WithMockUser(authorities = "SCOPE_READ")
    void testGetProductsWithValidUser() {
        client.get()
                .uri("/getProducts")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetProductsWithValidUserWithMockUser() {
        client.mutateWith(mockUser().authorities("SCOPE_READ"))
                .get()
                .uri("/getProducts")
                .exchange()
                .expectStatus().isOk();
    }
}
