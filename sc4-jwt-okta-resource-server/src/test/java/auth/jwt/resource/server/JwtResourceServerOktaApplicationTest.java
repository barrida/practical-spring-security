package auth.jwt.resource.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtResourceServerOktaApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getProductsUnauthenticatedReturnsUnauthorized() throws Exception {
        mvc.perform(get("/getProducts"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "suleyman", authorities = "SCOPE_READ")
    public void getProductsMockUserAuthenticatedReturnsOk() throws Exception {
        mvc.perform(get("/getProducts"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsWithValidJwtTokenReturnsOk() throws Exception {
        this.mvc.perform(get("/getProducts").with(
                jwt().authorities(new SimpleGrantedAuthority("SCOPE_READ"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsAuthenticatedWithUserReturnsOk()throws Exception {
        mvc.perform(get("/getProducts").with(user("user").authorities(new SimpleGrantedAuthority("SCOPE_READ"))))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "suleyman", authorities = "SCOPE_WRITE")
    public void updateProductsMockUserAuthenticatedReturnsOk() throws Exception {
        mvc.perform(get("/updateProducts").param("price","5"))
                .andExpect(status().isOk());
    }


}
