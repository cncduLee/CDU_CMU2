package edu.cmucdu.ecommerce.domain.user.security;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "user_role")
public class AuthorityPrincipalAssignment {

    @ManyToOne(cascade=CascadeType.ALL)
    private Principal username;

    @ManyToOne(cascade=CascadeType.ALL)
    private Authority roleId;
}
