package edu.cmucdu.ecommerce.domain.product.shoppingcart;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class CartTransaction {

    @ManyToOne
    private SellerProduct sellerProduct;

    private Integer amount;

    @ManyToOne
    private Cart cart;

	public void setCart(Cart cart) {
        this.cart = cart;
        cart.getCartTransaction().add(this);
    }
}
