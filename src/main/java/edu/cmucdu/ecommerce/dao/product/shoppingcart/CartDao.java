package edu.cmucdu.ecommerce.dao.product.shoppingcart;

import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.user.Buyer;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Cart.class)
public interface CartDao {
	public Cart getCartByBuyer(Buyer buyer);
}
