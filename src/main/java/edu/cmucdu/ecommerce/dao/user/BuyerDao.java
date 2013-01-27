package edu.cmucdu.ecommerce.dao.user;

import edu.cmucdu.ecommerce.domain.user.Buyer;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Buyer.class)
public interface BuyerDao {
}
