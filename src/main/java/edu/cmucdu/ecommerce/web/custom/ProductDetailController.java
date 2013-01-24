package edu.cmucdu.ecommerce.web.custom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.service.product.ProductService;

@Controller
public class ProductDetailController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/goodsDetail", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest httpServletRequest) {

		//Get request product id from other page
		String productId = httpServletRequest.getParameter("productId");
		long id = Long.parseLong(productId);
		
		Product product = productService.getProductFromID(id);
		map.addAttribute("product", product);

		List<ProductPic> pics = productService.getPictureFromID(id);
		map.addAttribute("pics", pics);
		
		return "goodsDetail";
	}

}
