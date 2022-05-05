package auth.jwt.resource.server.service;

import auth.jwt.resource.server.entity.Product;
import auth.jwt.resource.server.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author suleyman.yildirim
 */
@SpringBootTest
@Transactional(propagation = Propagation.REQUIRES_NEW) //reset the repository for before each test
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = productService.findAll();
    }

    @Test
    void testProductServiceWithNoUser() {
        assertThrows(AuthenticationException.class, () -> productService.processProducts(5));
    }

    @DisplayName("When the method is called with an authenticated user, " +
            "it only returns products, of which price is greater than passed argument, for the authenticated user")
    @Test
    @WithMockUser(username = "canan")
    void testProductServiceForPostFilterAnnotation() {
        var products = productService.processProducts(5);
        assertEquals(2, products.size());
        products.forEach(p -> assertEquals("canan", p.getOwner()));
    }

    @DisplayName("When the method is called with an authenticated user, " +
            "it only returns products that belong to the authenticated user")
    @Test
    @WithMockUser(username = "<your_okta_user_email>")
    void testProductServiceForPreFilterAnnotation() {
        var productsWithPreFilter = productService.processProductsWithPreFilter(this.products);
        assertEquals(3, productsWithPreFilter.size());
        productsWithPreFilter.forEach(p -> assertEquals("<your_okta_user_email>", p.getOwner()));
    }

    @DisplayName("When the method is called with an authenticated user having a correct authority, " +
            "it returns the expected result")
    @Test
    @WithMockUser(authorities = "SCOPE_DELETE")
    void testProductServiceWithUserButCorrectAuthority() {
        productService.deleteProduct(this.products.get(0));
        assertEquals(5, productService.findAll().size());
    }

    @DisplayName("When the method is called with an authenticated user having a wrong authority, " +
            "it throws AccessDeniedException")
    @Test
    @WithMockUser(authorities = "SCOPE_READ")
    void testProductServiceWithUserButWrongAuthority() {
        assertThrows(AccessDeniedException.class, () -> productService.deleteProduct(this.products.get(0)));
    }

}