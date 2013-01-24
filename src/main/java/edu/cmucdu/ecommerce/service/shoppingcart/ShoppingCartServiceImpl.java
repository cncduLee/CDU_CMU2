package edu.cmucdu.ecommerce.service.shoppingcart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.dao.product.shoppingcart.CartDao;
import edu.cmucdu.ecommerce.dao.product.shoppingcart.CartTransactionDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction;
import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.UserDetail;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	CartTransactionDao cartTransactionDao;
	@Autowired
	SellerProductDao sellerProductDao;
	@Autowired
	CartDao cartDao;
	@Autowired
	HttpSession session;
	
	@Override
	public boolean addToCart(CartTransaction cartTransaction) {
		return false;
//		try {
//			cartTransactionDao.save(cartTransaction);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
		
	}

	@Override
	public boolean removeProducts(Long productId) {
//		SellerProduct product = sellerProductDao.findOne(productId);
//		CartTransaction cartTransaction = cartTransactionDao.findCartTransactionBySellerProduct(product);
//		CartTransaction cartTransaction = cartTransactionDao.findCartTransactionBySellerProductProduct(productId);
//		cartTransactionDao.delete(cartTransaction);
		return false;
	}

	@Override
	public Cart showCart(UserDetail currentUser) {
		Cart cart = cartDao.getCartByBuyer((Buyer)currentUser);
		return cart;
	}

	@Override
	public void calculateTotalMoney() {
		float total = 0;
		Cart cart = (Cart)session.getAttribute("myCart"); 
		for(CartTransaction item : cart.getCartTransaction()){
			total += item.getAmount()*item.getSellerProduct().getPrice();
		}
		session.setAttribute("totalPrice", total);
	}

	
}
