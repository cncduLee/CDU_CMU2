package edu.cmucdu.ecommerce.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String test(ModelMap map)
	{
		return "test";
	}
}
