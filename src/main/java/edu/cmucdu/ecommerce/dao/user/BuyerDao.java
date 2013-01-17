package edu.cmucdu.ecommerce.dao.user;

import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.Seller;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Buyer.class)
public interface BuyerDao {
	Buyer findByPrincipleUsernameAndPrinciplePassword(String username, String password);
}
