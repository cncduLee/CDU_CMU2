package edu.cmucdu.ecommerce.web.custom;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckCode {
	
	@RequestMapping("/checkCode")
	public String getCheckCode(){
		System.out.println("==========");
		return "commom/image";
	}
}
