package edu.cmucdu.ecommerce.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.service.product.ProductService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String test(ModelMap map)
	{
		//set amount of products to show in the front page randomly
		List<Product> products = productService.getProductRandomly(3);
		map.addAttribute("products", products);
		return "test";
	}
}
