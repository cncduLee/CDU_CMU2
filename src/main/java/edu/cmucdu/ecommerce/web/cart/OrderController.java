package edu.cmucdu.ecommerce.web.cart;

import java.io.OutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction;

@Controller
public class OrderController {
	@Autowired 
	static HttpSession session;
	
	
	@RequestMapping("makeOrder")
	public String makeOrder(){
		//get shoppingcart info from the session
		return "cart/order";
	}
	
	@RequestMapping("submitOrder")
	public String successPage(){
		return "cart/success";
	}
	
	public static long totalCost(){
		long total = 0l;
		Cart cart = (Cart) session.getAttribute("myCart");
		for(CartTransaction t : cart.getCartTransaction()){
			total += t.getAmount() * t.getSellerProduct().getPrice();
		}
		return total;
	}
}
