package edu.cmucdu.ecommerce.dao.user;

import edu.cmucdu.ecommerce.domain.user.BuyerRequest;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = BuyerRequest.class)
public interface BuyerRequestDao {
}
