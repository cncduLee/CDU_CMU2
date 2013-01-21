package edu.cmucdu.ecommerce.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.dao.test.TestDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.util.Description;
import edu.cmucdu.ecommerce.service.product.ProductService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	TestDao testDao;
	
	@Autowired
	SellerProductDao selllerProductDao;
	
	@Autowired
	SellerDao sellerDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getProduct(ModelMap map, HttpServletRequest httpRequest)
	{
		long id = 3;
		SellerProduct sp = selllerProductDao.findOne(id);
		Product product = sp.getProduct();
		long productId = product.getId();
		System.out.println(productId);
		Product p = productDao.findOne(productId);
		//p.setLocale(WebUtil.getLocaleEnum(httpRequest));
		
		Seller seller = sp.getSeller();
		long sellerId = seller.getId();
		Seller s = sellerDao.findOne(sellerId);
		
		map.addAttribute("sellerProduct", sp);
		map.addAttribute("product", p);
		map.addAttribute("seller",s);
		return "test";
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView getProduct(ModelMap map, String productId)
//	{
//		ModelAndView mav = new ModelAndView();
//		productId = "1";
//		
//		List<Product> result = testDao.getProductFromId(productId);
//		
////		List<Product> result = new ArrayList<Product>();
////        Iterator<Object[]> productList = testDao.getProductFromId(productId);
////        
////        while(productList.hasNext())
////        {
////        	Object[] productData = productList.next();
////        	Product productObj = new Product();
////        	productObj.setId((Long) productData[0]);
////        	productObj.setName((Description)productData[3]);
////        	productObj.setDescription((Description)productData[2]);
////        	result.add(productObj);
////        }
//		
//		System.out.println(">><<");
//		mav.addObject("product", result);
//		mav.setViewName("test");
//		
//		return mav;
//	}
	
}
