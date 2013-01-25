package edu.cmucdu.ecommerce.web.custom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import edu.cmucdu.ecommerce.domain.user.security.Authority;
import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.domain.util.Description;

@Controller
public class RegisterController {
	@Autowired
	SellerDao sellerDao;
	@Autowired
	BuyerDao buyerDao;
	
	@RequestMapping("/toRegister")
	public ModelAndView toRegisterPage(){
		return new ModelAndView("register", "command", new RegisterForm());
	} 
	
	@RequestMapping(value="registInfo", method=RequestMethod.POST)
	public String register(@ModelAttribute("register")RegisterForm registgerForm,
			BindingResult resulth,ModelMap errorMap){
		int type = Integer.parseInt(registgerForm.getType().trim());
		//validate
		
		
		if(registgerForm.getUsername()==null ||registgerForm.getPassword()==null || registgerForm.getFullName() == null){
			errorMap.addAttribute("notNull_error", "information should't blank ");
		}
		
		
		
		if(type == 1){
			Seller seller = new Seller();
			seller.setTelephoneNo(registgerForm.getTelephone()+"  ");
			Description description = new Description();
			description.setEnglishDesc(registgerForm.getAddress() + "  ");
			seller.setAddress(description);
			description.setEnglishDesc(registgerForm.getFullName() + "  ");
			seller.setName(description);
//			seller.setEmail(registgerForm.getEmail());
			
			Principal principal = new Principal();
			principal.setUsername(registgerForm.getUsername());
			principal.setPassword(registgerForm.getPassword());
			principal.setEnabled(true);
			principal.setUser(seller);
			//Authority
			Authority a = Authority.findAuthoritysByAuthorityLike("ROLE_SELLER")
					.getResultList().get(0);
			AuthorityPrincipalAssignment as = new AuthorityPrincipalAssignment();
			as.setRoleId(a);
			as.setUsername(principal);
			//principal
			principal.persist();
			//save to db
			sellerDao.save(seller);
		}
		if(type == 2){
			//register buyer
			Buyer buyer = new Buyer();
			
			Description description = new Description();
			buyer.setTelephoneNo(registgerForm.getTelephone()+"  ");
			description.setEnglishDesc(registgerForm.getAddress() + "  ");
			buyer.setAddress(description);
			description.setEnglishDesc(registgerForm.getFullName() + "  ");
			buyer.setName(description);
			
//			buyer.setEmail(registgerForm.getEmail());
			
			 Principal principal = new Principal();
		        principal.setUsername(registgerForm.getUsername());
		        principal.setPassword(registgerForm.getPassword());
		        principal.setEnabled(true);
		        principal.setUser(buyer);
		        
		        Authority a = Authority.findAuthoritysByAuthorityLike("ROLE_BUYER").getResultList().get(0);
		        AuthorityPrincipalAssignment as = new AuthorityPrincipalAssignment();
		        as.setRoleId(a);
		        as.setUsername(principal);
		        buyer.setPrinciple(principal);
		        principal.persist();
		        as.persist();
		        
		        buyerDao.save(buyer);
			
		}
		return "redirect:/";
	}
	
}
