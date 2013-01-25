package edu.cmucdu.ecommerce.dao.security;

import edu.cmucdu.ecommerce.domain.user.security.Principal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Principal.class)
public interface PrincipalDao {
	@Query
	public Principal findByUsername(String username);
}
