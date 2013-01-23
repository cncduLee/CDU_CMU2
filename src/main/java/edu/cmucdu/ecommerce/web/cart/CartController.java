package edu.cmucdu.ecommerce.web.cart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import edu.cmucdu.ecommerce.dao.product.shoppingcart.CartDao;
import edu.cmucdu.ecommerce.dao.product.shoppingcart.CartTransactionDao;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.CartTransaction;
import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.UserDetail;
import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.service.shoppingcart.ShoppingCartService;


@Controller
public class CartController {
	@Autowired
	SellerProductDao sellerProductDao;
	@Autowired  
	private HttpSession session; 
	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping(value="cartPage")
	public String index() {
		try {
			shoppingCartService.calculateTotalMoney();
		} catch (Exception e) {
			return "/";	
		}
		return "cart/index";
	}

	@RequestMapping(value = "cartAdd/productId={id}&amount={amount}", method = RequestMethod.GET)
	public String addProduct(
			@PathVariable("id") long id,
			@PathVariable("amount") int amount,
			Model uiModel,
			HttpServletRequest httpServletRequest) {
		
		Cart cart = (Cart) session.getAttribute("myCart");
		if(cart.getBuyer() == null){
			return "redirect:../toLoginPage";
		}
		try{

			CartTransaction ct = new CartTransaction();
			ct.setCart(cart);
			ct.setAmount(amount);
			ct.setSellerProduct(sellerProductDao.findOne(id));
			
			//cartDao.saveAndFlush(cart);
			
		}catch(Exception e){
			//can't get the sellerProduct from db
			return "redirect:../toLoginPage";
		}
		shoppingCartService.calculateTotalMoney();
		return "redirect:../cartPage";
	}
	
	@RequestMapping(value="cartRest/productId={id}&type={type}", method = RequestMethod.GET)
	public String addAmount(
			@PathVariable("id") long id,
			@PathVariable("type") int type,
			Model uiModel,
			HttpServletRequest httpServletRequest){
		
		Cart cart = (Cart) session.getAttribute("myCart");
		
		List<CartTransaction> all = cart.getCartTransaction();
		for(CartTransaction item : all){
			if(id == item.getSellerProduct().getId()){
				if(type==1){
					item.setAmount(item.getAmount()+1);//add
				}
				if(type == 2){
					if(item.getAmount() > 0){
						item.setAmount(item.getAmount()-1);//desc
					}
					
				}	
			}
		}
		cart.setCartTransaction(all);
		session.setAttribute("myCart",cart);
		shoppingCartService.calculateTotalMoney();
		return "redirect:../cartPage";
	}
	
	@RequestMapping(value="cartRemove/productId={id}", method = RequestMethod.GET)
	public String remove(
			@PathVariable("id") long id,
			Model uiModel,
			HttpServletRequest httpServletRequest){
		
		Cart cart = (Cart) session.getAttribute("myCart");
		
		List<CartTransaction> all = cart.getCartTransaction();
//		for(CartTransaction item : all){
//			if(id == item.getSellerProduct().getId()){
//				all.remove(item);// can't delete the obj ---LPM
//			}
//		}
		for(int i = 0;i<all.size();i++){
			if(id == all.get(i).getSellerProduct().getId()){
				all.remove(i);
			}
		}
		cart.setCartTransaction(all);
		session.setAttribute("myCart",cart);
		shoppingCartService.calculateTotalMoney();
		return "redirect:../cartPage";
	}


}