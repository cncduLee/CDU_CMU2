package edu.cmucdu.ecommerce.domain.user.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.cmucdu.ecommerce.domain.user.UserDetail;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "users")
public class Principal  implements UserDetails{

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    private Boolean checkEnabled;
    @OneToOne(cascade=CascadeType.ALL)
    private UserDetail user;
    @Transient
    Collection< GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	private EntityManager entityManager;
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Principal(){
		
	}
	public Principal(String username, String password, Boolean enabled,
			Collection<GrantedAuthority> authorities,
			boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, EntityManager entityManager) {
		super();
		this.username = username;
		this.password = password;
		this.checkEnabled = enabled;
		this.authorities = authorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.entityManager = entityManager;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Transient
	boolean isAccountNonExpired;
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	@Transient
	boolean isAccountNonLocked;
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	@Transient
	boolean isCredentialsNonExpired;
	@Override
	public boolean isCredentialsNonExpired() {
	
		return isCredentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return checkEnabled;
	}

    public void setEnabled(boolean enabled){
    	checkEnabled = enabled;
    }
    
}
