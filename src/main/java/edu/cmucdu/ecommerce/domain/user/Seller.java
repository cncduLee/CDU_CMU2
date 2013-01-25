package edu.cmucdu.ecommerce.domain.user;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Seller extends UserDetail {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private Set<SellerProduct> products = new HashSet<SellerProduct>();
}
