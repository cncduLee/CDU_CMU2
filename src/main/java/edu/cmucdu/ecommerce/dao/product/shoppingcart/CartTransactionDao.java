package edu.cmucdu.ecommerce.dao.product.shoppingcart;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction;


@RooJpaRepository(domainType = CartTransaction.class)
public interface CartTransactionDao {
	public CartTransaction findCartTransactionBySellerProduct(SellerProduct sellerProduct);
	public CartTransaction findCartTransactionBySellerProductProduct(Long productId);
	
}
