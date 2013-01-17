package edu.cmucdu.ecommerce.web.custom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView goTpRegisterPage(Model uiModel, HttpServletRequest httpServletRequest){
		System.out.println("--------");
		List<Seller> sellers = sellerDao.findAll();
		uiModel.addAttribute("all", sellers);
		return new ModelAndView("register", "registForm", new RegisterForm());//redirect to the regist page
	}
	
	@RequestMapping(value="/regist", method = RequestMethod.POST)  
	public String register(@ModelAttribute("registForm") RegisterForm registForm, BindingResult result){
//		int type = Integer.parseInt(registForm.getType());
//		if(type==1){
//			//seller
//			Seller seller = new Seller();
//			//TODO add some buyer’s info
//			principle.setUser(seller);	
//			seller.setPrinciple(principle);
//			sellerDao.save(seller);
//		}
//		else{
//			//buyer
//			Buyer buyer = new Buyer();
//			//TODO add some buyer’s info
//			principle.setUser(buyer);
//			buyer.setPrinciple(principle);
//			buyerDao.save(buyer);
//		}
		System.out.println("---------"+registForm.getAddress());
		return "redirect:login";
	}
	
	
}
