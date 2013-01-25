package edu.cmucdu.ecommerce.dao.product;

import edu.cmucdu.ecommerce.domain.product.Product;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Product.class)
public interface ProductDao {
}
