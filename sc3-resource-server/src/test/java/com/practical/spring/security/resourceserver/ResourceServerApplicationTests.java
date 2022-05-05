package com.practical.spring.security.resourceserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceServerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getProductsWithValidOpaqueTokenReturnsOk() throws Exception {
        mvc.perform(get("/getProducts")
                        .with(opaqueToken().authorities(new SimpleGrantedAuthority("SCOPE_READ"))))
                .andExpect(status().isOk());
    }

}
