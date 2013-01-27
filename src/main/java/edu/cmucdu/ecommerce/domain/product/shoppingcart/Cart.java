package edu.cmucdu.ecommerce.domain.product.shoppingcart;

import edu.cmucdu.ecommerce.domain.user.Buyer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Cart {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartTransaction> cartTransaction = new ArrayList<CartTransaction>();

    @ManyToOne
    private Buyer buyer;
}
