package com.practical.spring.security.basicauth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BasicAuthApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMainAccessDenied() throws Exception {
        mvc.perform(get("/main"))
                .andExpect(status().isUnauthorized())
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser(username = "suleyman", authorities = "READ")
    public void getMainWithMockUserAnnotation() throws Exception {
        mvc.perform(get("/main"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "suleyman", authorities = "WRITE")
    public void getMainWithMockUserAnnotationInvalidAuthority() throws Exception {
        mvc.perform(get("/main"))
                .andExpect(status().isForbidden())
                .andExpect(result ->
                        assertSame(result.getResponse().getErrorMessage(), HttpStatus.FORBIDDEN.getReasonPhrase()));
    }

    @Test
    public void getMainWithSecurityMockMvcRequestPostProcessorUserAndAuthority()throws Exception {
        mvc.perform(get("/main").with(user("user").authorities(new SimpleGrantedAuthority("READ"))))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("suleyman")
    public void getMainWithUserDetails() throws Exception {
        mvc.perform(get("/main"))
                .andExpect(status().isOk());
    }

}
