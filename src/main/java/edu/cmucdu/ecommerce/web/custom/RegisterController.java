package edu.cmucdu.ecommerce.web.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmucdu.ecommerce.dao.user.BuyerDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.user.security.Principal;

@Controller
public class RegisterController {
	@Autowired
	SellerDao sellerDao;
	@Autowired
	BuyerDao buyerDao;
	
	@RequestMapping("/register")
	public String regist(){
		return "register";//redirect to the regist page
	}
	
	@RequestMapping("/registInfo")
	public String register(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email,
			@RequestParam int type){
		Principal principle = new Principal();
		principle.setUsername(username);
		principle.setPassword(password);
		principle.setEnabled(true);
		if(type==1){
			//seller
			Seller seller = new Seller();
			//TODO add some buyer’s info
			principle.setUser(seller);	
			seller.setPrinciple(principle);
			sellerDao.save(seller);
		}
		else{
			//buyer
			Buyer buyer = new Buyer();
			//TODO add some buyer’s info
			principle.setUser(buyer);
			buyer.setPrinciple(principle);
			buyerDao.save(buyer);
		}
		return "/login";
	}
	
	
}
