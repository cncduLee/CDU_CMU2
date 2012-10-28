package edu.cmucdu.ecommerce.dao.security;

import edu.cmucdu.ecommerce.domain.user.security.Authority;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Authority.class)
public interface AuthorityDao {
}
