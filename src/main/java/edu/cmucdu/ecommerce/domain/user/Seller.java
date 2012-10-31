package edu.cmucdu.ecommerce.domain.user;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Seller extends UserDetail {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private Set<SellerProduct> products = new HashSet<SellerProduct>();

    
	public String toString() {
        return getLocalName() + " " +getLocalDescription(); 
       }
}
