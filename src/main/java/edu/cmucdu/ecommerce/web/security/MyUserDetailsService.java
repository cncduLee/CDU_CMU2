package edu.cmucdu.ecommerce.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.security.AuthorityDao;
import edu.cmucdu.ecommerce.dao.security.AuthorityPrincipalAssignmentDao;
import edu.cmucdu.ecommerce.dao.security.PrincipalDao;
import edu.cmucdu.ecommerce.domain.user.security.Authority;
import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import edu.cmucdu.ecommerce.domain.user.security.Principal;

@Service     
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private PrincipalDao principalDao;
	@Autowired
	private AuthorityPrincipalAssignmentDao apaDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Principal user = principalDao.findByUsername(username);
		if (user != null){
			 // convert roles
           // List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            List<AuthorityPrincipalAssignment> authoritys = apaDao.findByUsername(user);
            for (AuthorityPrincipalAssignment authority : authoritys) {
				user.getAuthorities().add(new GrantedAuthorityImpl(authority.getRoleId().getAuthority()));
			}
            user.setIsAccountNonExpired(true);
            user.setIsAccountNonLocked(true);
            user.setIsCredentialsNonExpired(true);
            return user;
		}else{
            throw new UsernameNotFoundException("No user with username '" + username + "' found!");
		}
	}

}
