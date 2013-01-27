package edu.cmucdu.ecommerce.service.shoppingcart;


import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction;
import edu.cmucdu.ecommerce.domain.user.UserDetail;

public interface ShoppingCartService {
	
	/**
	 * add product into the shopping cart
	 * 
	 * @param cartTransaction
	 * @return
	 */
	public boolean addToCart(CartTransaction cartTransaction);
	
	/**
	 * remove product from the shopping cart 
	 * @param productId
	 * @return
	 */
	public boolean removeProducts(Long productId);
	
	/**
	 * current user's shopping cart
	 * @param currentUser
	 * @return
	 */
	public Cart showCart(UserDetail currentUser);
	
	/**
	 * Calculate the Total money all the products int shopping cart
	 * @return
	 */
	public void calculateTotalMoney();
}
