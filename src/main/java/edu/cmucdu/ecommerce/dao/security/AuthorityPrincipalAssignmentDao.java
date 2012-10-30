package edu.cmucdu.ecommerce.dao.security;

import java.util.List;

import edu.cmucdu.ecommerce.domain.user.security.Authority;
import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import edu.cmucdu.ecommerce.domain.user.security.Principal;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AuthorityPrincipalAssignment.class)
public interface AuthorityPrincipalAssignmentDao {
	List<AuthorityPrincipalAssignment> findByUsername(Principal user);
}
