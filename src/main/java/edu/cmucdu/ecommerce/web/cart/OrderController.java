package edu.cmucdu.ecommerce.web.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	
	@RequestMapping("makeOrder")
	public String makeOrder(){
		return "cart/order";
	}
	
	@RequestMapping("submitOrder")
	public String successPage(){
		return "cart/success";
	}
	
}
