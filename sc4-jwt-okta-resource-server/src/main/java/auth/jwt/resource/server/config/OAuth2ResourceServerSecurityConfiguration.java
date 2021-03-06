package auth.jwt.resource.server.config;

import auth.jwt.resource.server.validation.AudienceValidator;
import auth.jwt.resource.server.validation.InformativeAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${okta.oauth2.issuer}")
    private String issuerUrl;

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
                .oauth2ResourceServer()
                .authenticationEntryPoint(new InformativeAuthenticationEntryPoint())
                .jwt(jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder()));
    }

    @Bean
    public JwtDecoder customJwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUrl);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUrl);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<Jwt>(withIssuer, new AudienceValidator());
        jwtDecoder.setJwtValidator(withAudience);
        return jwtDecoder;
    }

}
