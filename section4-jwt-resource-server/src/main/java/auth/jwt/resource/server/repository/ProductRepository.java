package auth.jwt.resource.server.repository;

import auth.jwt.resource.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Suleyman Yildirim
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
