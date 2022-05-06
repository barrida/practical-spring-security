package com.practical.spring.security.reactive.web.client;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import com.practical.spring.security.reactive.web.client.model.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOAuth2Client;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class Sc5ReactiveWebClientApplicationTests {

	@Autowired
	private WebTestClient client;

	public static MockWebServer mockWebServer;

	@BeforeAll
	static void setUp() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) throws IOException {
		registry.add("products.uri", () -> "http://localhost:" + mockWebServer.getPort() + "/getProducts");
	}

	@Test
	void testGetProductsWithValidUser() {

		mockWebServer.enqueue(new MockResponse()
				.addHeader("Content-Type", "application/json; charset=utf-8")
				.setBody("{\"id\": 1," +
						"\"name\": \"beef\"," +
						"\"price\": 5.0," +
						"\"currency\": \"GBP\"," +
						"\"owner\": \"suleyman\"" +
						"}"));

		var res = client
				.mutateWith(mockOidcLogin())
				.mutateWith(mockOAuth2Client("okta"))
				.get()
				.uri("/")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk();

	}

	@AfterAll
	static void tearDown() throws IOException {
		mockWebServer.shutdown();
	}

}
