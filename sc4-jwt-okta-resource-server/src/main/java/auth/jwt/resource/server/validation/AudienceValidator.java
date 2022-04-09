package auth.jwt.resource.server.validation;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenError;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
    BearerTokenError error = new BearerTokenError("invalid_token", HttpStatus.UNAUTHORIZED, "The audience is not correct", null);

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (token.getSubject().contains("suleyman")) {
            return OAuth2TokenValidatorResult.success();
        } else {
            return OAuth2TokenValidatorResult.failure(error);
        }
    }
}
