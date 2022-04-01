package com.practical.spring.security.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
    String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
    String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
    String clientSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .mvcMatchers("/getProducts/**").hasAuthority("SCOPE_READ")
                                .mvcMatchers("/updateProducts/**").hasAuthority("SCOPE_WRITE")
                                .mvcMatchers("/h2-console/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .opaqueToken(opaqueToken ->
                                        opaqueToken
                                                .introspectionUri(this.introspectionUri)
                                                .introspectionClientCredentials(this.clientId, this.clientSecret)
                                )
                );

    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                    authorizeRequests
//                    .mvcMatchers("/getProducts/**").hasAuthority("SCOPE_READ")
//                                .mvcMatchers("/updateProducts/**").hasAuthority("SCOPE_WRITE")
//                                .mvcMatchers("/h2-console/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer((oauth2) -> oauth2
//                        .opaqueToken((opaque) -> opaque
//                                .introspectionUri(this.introspectionUri)
//                                .introspectionClientCredentials(this.clientId, this.clientSecret)
//                        )
//                );
//
//        return http.build();
//    }

}
