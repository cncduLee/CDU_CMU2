package edu.cmucdu.ecommerce.web.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmucdu.ecommerce.dao.user.BuyerDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;


@Controller
public class LoginController {
	
	@Autowired
	SellerDao sellerDao;
	@Autowired
	BuyerDao buyerDao;
	
	@RequestMapping("/toLoginPage")
	public String goToLoginPage(){
		return "login";//redirect login page
	}
	
	@RequestMapping("/login")
	public String Login(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String type){
		// get the data from page
		
		// validate this user is exit?
		//add to session
		return "login";
	}
}
