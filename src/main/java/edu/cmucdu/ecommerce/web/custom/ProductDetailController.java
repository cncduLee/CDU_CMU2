package edu.cmucdu.ecommerce.web.custom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.service.product.ProductService;



@Controller
public class ProductDetailController {

	@Autowired
	ProductService productService;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	SellerProductDao sellerProductDao; 

	@RequestMapping(value = "/goodsDetail", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest httpServletRequest) {

		//Get request product id from other page
		String productId = httpServletRequest.getParameter("productId");
		String sellerId = httpServletRequest.getParameter("sellerId");
		// List<Product> product = productDao.findOne(productId);
		System.out.println(productId + "   "+ sellerId);

		long id = Long.parseLong(productId);
		SellerProduct sellerProduct = sellerProductDao.findOne(id);
		
		map.addAttribute("product", sellerProduct.getProduct());
		map.addAttribute("pics", sellerProduct.getProduct().getImages().get(0));
		map.addAttribute("piclist", sellerProduct.getProduct().getImages());
		map.addAttribute("seller", sellerProduct.getSeller());
		
		return "goodsDetail";
	}

}
