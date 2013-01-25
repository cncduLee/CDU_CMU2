package edu.cmucdu.ecommerce.web.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.cmucdu.ecommerce.dao.user.BuyerDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.dao.user.UserDetailDao;
import edu.cmucdu.ecommerce.domain.product.shoppingcart.Cart;
import edu.cmucdu.ecommerce.domain.user.Buyer;
import edu.cmucdu.ecommerce.domain.user.UserDetail;

@Controller
public class LoginController {

	@Autowired
	UserDetailDao userDetailDao;
	
	@Autowired
	SellerDao sellerDao;
	@Autowired
	BuyerDao buyerDao;
	@Autowired  
	private HttpSession session;
	@Autowired  
	private HttpServletRequest request;  
	
	private String refererPage = "/";
	
	@RequestMapping("/toLoginPage")
	public String goToLoginPage() {
		refererPage = request.getHeader("referer");
		System.out.println("--"+refererPage);
		return "login";// redirect login page
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(@RequestParam String username,
			@RequestParam String password, 
			@RequestParam String typeName,
			@RequestParam String ccode,
			ModelMap errorMap) {
		
		int type = Integer.parseInt(typeName.trim());
		UserDetail user = null;
		
		String checkCode = (String) session.getAttribute("checkcode");
		if(ccode != null && ccode.trim().equals(checkCode)){
			//ccode is right
			if (type == 1) {
				// seller login
				user = sellerDao.findByPrincipleUsernameAndPrinciplePassword(username,password);
			}
			if (type == 2) {
				// buyer login
				user = buyerDao.findByPrincipleUsernameAndPrinciplePassword(username, password);
			}
			
//			user = userDetailDao.findByPrincipleUsernameAndPrinciplePassword(username, password);
			
			if(user != null){
				//login success
				//add login user to session
				session.setAttribute("logined_user", user);
				if(type==2){
					Cart c = new Cart();
					c.setBuyer((Buyer)user);
					session.setAttribute("myCart", c);
				}
				//redirect
				return "redirect:"+loginSuccessDirect();//redirect homepage
			}else{
				//error
				errorMap.addAttribute("error", "can't find the user!");
				return "login";
			}
		}else{
			errorMap.addAttribute("error", "checkCode is wrong!");
			return "login";
		}
	}
	
	private String loginSuccessDirect(){
		return refererPage==null?"/":refererPage.substring(refererPage.lastIndexOf("/"),refererPage.length());
	}
}
