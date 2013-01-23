package edu.cmucdu.ecommerce.web.custom;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.service.product.ProductService;
import edu.cmucdu.ecommerce.web.util.WebUtil;

@Controller
public class ProductsController {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;

	
	@RequestMapping("/productList")
	public String productsPresentation(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest httpServletRequest){
		
		final int totle = productDao.findAll().size();
		
		if (page == null || size == null) {
			page=1;
			size=12;
		}
		int sizeNo = size == null ? 1 : size.intValue();//12 products every page
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		List<Product> all = productDao.findAll(
				new org.springframework.data.domain.PageRequest(firstResult
						/ sizeNo, sizeNo)).getContent();
		
		for (Product product : all) {
			product.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
		}
		
		uiModel.addAttribute("products", all);
		uiModel.addAttribute("currentPage",page);
		uiModel.addAttribute("totle",totle);
		
		float nrOfPages = (float) productDao.count() / sizeNo;
		uiModel.addAttribute(
				"maxPages",
				(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
						: nrOfPages));
		
		
		return "products";
	}
}
