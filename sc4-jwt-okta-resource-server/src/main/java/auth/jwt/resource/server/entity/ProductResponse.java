package auth.jwt.resource.server.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    List<Product> productList;
    Authentication authentication;
}
