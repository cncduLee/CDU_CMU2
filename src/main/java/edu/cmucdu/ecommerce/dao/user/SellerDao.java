package edu.cmucdu.ecommerce.dao.user;

import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.user.security.Principal;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Seller.class)
public interface SellerDao {
	Seller findByPrincipleUsernameAndPrinciplePassword(String username, String password);
}
