package edu.cmucdu.ecommerce.web.custom;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;

@Controller
public class IndexController {
	@Autowired
	ProductDao productDao;
	
	@Transactional
	@RequestMapping("/")
	public String index(Model uiModel,HttpServletRequest httpServletRequest){
		List<Product> products = productDao.findAll();
		List<IndexProductEntity> idp = new ArrayList<IndexProductEntity>();
		for (Product product : products) {
			idp.add(new IndexProductEntity(product.getLocalName(), product.getLocalDescription()));
		}
//		Long picID = products.get(0).getImages().get(0).getId();
		uiModel.addAttribute("products",products);
		List<ProductPic> pics = new ArrayList<ProductPic>();
		for (Product product2 : products) {
			pics.add(product2.getImages().get(0));
		}
		uiModel.addAttribute("pics",pics);
//		uiModel.addAttribute("img",products.get(0).getImages().get(0));
//		uiModel.addAttribute("product", products.get(0));
		return "index";
	}
	
}
