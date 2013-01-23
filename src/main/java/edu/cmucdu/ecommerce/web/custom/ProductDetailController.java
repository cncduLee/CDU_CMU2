package edu.cmucdu.ecommerce.web.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.service.product.ProductService;

@Controller
public class ProductDetailController {

	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/goodsDetail", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest httpServletRequest) {

		String productId = httpServletRequest.getParameter("productId");
		// List<Product> product = productDao.findOne(productId);
		System.out.println(productId);
		Product p = productDao.findOne(Long.parseLong(productId));

		map.addAttribute("product", p);

		return "goodsDetail";
	}

}
