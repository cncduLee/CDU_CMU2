package edu.cmucdu.ecommerce.dao.security;

import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AuthorityPrincipalAssignment.class)
public interface AuthorityPrincipalAssignmentDao {
}
