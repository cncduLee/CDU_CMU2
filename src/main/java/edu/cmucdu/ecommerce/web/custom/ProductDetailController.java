package edu.cmucdu.ecommerce.web.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.service.product.ProductService;

@Controller
public class ProductDetailController {

	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	@Autowired
	SellerDao sellerDao;

	@RequestMapping(value = "/goodsDetail", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest httpServletRequest) {

		String productId = httpServletRequest.getParameter("productId");
		String sellerId = httpServletRequest.getParameter("sellerId");
		// List<Product> product = productDao.findOne(productId);
		System.out.println(productId + "   "+ sellerId);
		Product p = productDao.findOne(Long.parseLong(productId.trim()));
		Seller seller = sellerDao.findOne(Long.parseLong(sellerId.trim()));  
		
		map.addAttribute("product", p);

		return "goodsDetail";
	}

}
