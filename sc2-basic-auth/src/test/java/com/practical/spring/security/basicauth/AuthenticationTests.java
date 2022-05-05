package com.practical.spring.security.basicauth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author suleyman.yildirim
 */

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test calling /main endpoint authenticating with valid credentials returns ok.")
    public void getMainAuthenticatingWithValidUser() throws Exception {
        mvc.perform(get("/main")
                        .with(httpBasic("suleyman", "1234")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test calling /main endpoint authenticating with wrong credentials returns unauthorized.")
    public void getMainAuthenticatingWithInvalidUser() throws Exception {
        mvc.perform(get("/main")
                        .with(httpBasic("canan", "12345")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Logging in authenticating with valid user and valid credentials")
    public void formLoginWithValidCredentials() throws Exception {
        mvc.perform(formLogin().user("suleyman").password("1234"))
                .andExpect(redirectedUrl("/main"))
                .andExpect(status().isFound())
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("Logging in authenticating with valid user and invalid credentials")
    public void formLoginWithInvalidCredentials() throws Exception {
        mvc.perform(formLogin().user("suleyman").password("qe135"))
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(status().isFound())
                .andExpect(unauthenticated());
    }
}
