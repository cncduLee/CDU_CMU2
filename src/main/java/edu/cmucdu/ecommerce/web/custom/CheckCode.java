package edu.cmucdu.ecommerce.web.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckCode {
	
	@RequestMapping("/checkCode")
	public String getCheckCode(Model uiModel,HttpServletRequest httpServletRequest){
		
		return "commom/image";
	}
}
