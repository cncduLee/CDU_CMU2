package edu.cmucdu.ecommerce.dao.product;

import edu.cmucdu.ecommerce.domain.product.Promotion;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Promotion.class)
public interface PromotionDao {
}
