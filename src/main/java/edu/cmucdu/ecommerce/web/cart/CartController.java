package edu.cmucdu.ecommerce.web.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction;
import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.security.Principal;


@Controller
@SessionAttributes("myCart")

public class CartController {
	@Autowired
	SellerProductDao sellerProductDao;
    
	
	@ModelAttribute("myCart")
	public Cart getCart(){
		System.out.println("---");
		Cart c = new Cart();
//		Principal principal = (Principal) SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//		c.setBuyer((Buyer) principal.getUser());
		System.out.println("--@@--");
		return c;
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "{id}")
//	public void post(@PathVariable Long id, ModelMap modelMap,
//			HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("======get");
//	}

	@RequestMapping(value="cartPage")
	public String index() {
		System.out.println("======index");
		return "cart/index";
	}

	@RequestMapping(value = "cartAdd/productId={id}&amount={amount}", method = RequestMethod.GET)
	public String addProduct(
			@PathVariable("id") long id,
			@PathVariable("amount") int amount,
			@ModelAttribute("myCart") Cart cart,
			Model uiModel,
			HttpServletRequest httpServletRequest) {
		CartTransaction ct = new CartTransaction();
		ct.setCart(cart);
		ct.setAmount(amount);
		ct.setSellerProduct(sellerProductDao.findOne(id));
		//wait for show all the product list
		return "redirect:../cartPage";
	}
	
	


}