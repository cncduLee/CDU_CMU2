package edu.cmucdu.ecommerce.web.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {
	
	@RequestMapping("contact")
	public String contactPage(){
		return "contact";
	}
	
	@RequestMapping("recomment")
	public String recommentPage(){
		return "recomment";
	}
	@RequestMapping("videoPage")
	public String videoPage(){
		return "video";
	}
	@RequestMapping("ruleDetailPage")
	public String detailRules(){
		return "rules/detailRules";
	}
	
	@RequestMapping("aboutPage")
	public String aboutPage(){
		return "about";
	}
}
