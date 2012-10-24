package edu.cmucdu.ecommerce.domain.user.security;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import edu.cmucdu.ecommerce.domain.user.UserDetail;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "security_principals")
public class Principal {

    @NotNull
    @Size(min = 3, max = 50)
    private String userName;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    private Boolean enabled;
    @OneToOne(cascade=CascadeType.ALL)
    private UserDetail user;
}
