package edu.cmucdu.ecommerce.web.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.domain.product.Promotion;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.service.product.ProductService;

@Controller
public class IndexController {
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	@Autowired
	SellerProductDao sellerProductDao;
	
	@Transactional
	@RequestMapping("/")
	public String index(Model uiModel, HttpServletRequest httpServletRequest) {
		List<SellerProduct> products = productService.getSellerPruductRandomly(3);//sellerProductDao.findAll();
		
		List<IndexProductEntity> idp = new ArrayList<IndexProductEntity>();
		List<HashMap<String,Object>> picAndSellers = new ArrayList<HashMap<String,Object>>();

		List<SellerProduct> pds = sellerProductDao.findAll();
		
		for (SellerProduct product : products) {
			idp.add(new IndexProductEntity(
					product.getProduct().getLocalName(),
					product.getProduct().getLocalDescription(), 
					product.getId()));
		}
	
		for (SellerProduct product : products) {
			
			HashMap<String,Object> map = new HashMap();
			map.put("pic", product.getProduct().getImages().get(0));
			map.put("seller",product.getSeller().getId());
			picAndSellers.add(map);
		}
		

		uiModel.addAttribute("products", idp);
		uiModel.addAttribute("picAndSellers",picAndSellers);
		uiModel.addAttribute("gallery",pds);
		
		return "index";
	}

}
