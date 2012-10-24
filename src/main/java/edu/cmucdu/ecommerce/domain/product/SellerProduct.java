package edu.cmucdu.ecommerce.domain.product;

import edu.cmucdu.ecommerce.domain.user.Seller;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class SellerProduct {

	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellerProduct")
    private Set<ProductPic> images = new HashSet<ProductPic>();

    @ManyToOne
    private Seller seller;

    private double price;

    private double weight;

    @OneToOne
    private Promotion promotion;
}
