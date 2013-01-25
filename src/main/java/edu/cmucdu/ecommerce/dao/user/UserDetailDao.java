package edu.cmucdu.ecommerce.dao.user;

import edu.cmucdu.ecommerce.domain.user.UserDetail;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = UserDetail.class)
public interface UserDetailDao {
	UserDetail findByPrincipleUsernameAndPrinciplePassword(String userName,String password);
}
