package edu.cmucdu.ecommerce.web.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RulesController {
	
	@RequestMapping("/rulesPage")
	public String goToRulesPage(){
		return "rules/rules";
	}
}
