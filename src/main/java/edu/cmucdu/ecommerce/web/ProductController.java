package edu.cmucdu.ecommerce.web;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Set;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.ProductPicDao;
import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.util.LocaleEnum;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import edu.cmucdu.ecommerce.web.util.WebUtil;

//import org.springframework.web.util;

@RequestMapping("/products")
@Controller
@RooWebScaffold(path = "products", formBackingObject = Product.class)
public class ProductController {

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductPicDao productPicDao;

	@Autowired
	SellerProductDao sellerProductDao;

	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel,
			HttpServletRequest httpRequest) {
		Product p = productDao.findOne(id);
		p.setLocale(WebUtil.getLocaleEnum(httpRequest));
		uiModel.addAttribute("product", productDao.findOne(id));
		uiModel.addAttribute("itemId", id);
		return "products/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest httpRequest) {

		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			List<Product> all = productDao.findAll(
					new org.springframework.data.domain.PageRequest(firstResult
							/ sizeNo, sizeNo)).getContent();

			for (Product product : all)
				product.setLocale(WebUtil.getLocaleEnum(httpRequest));
			uiModel.addAttribute("products", all);
			float nrOfPages = (float) productDao.count() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			List<Product> all = productDao.findAll();
			for (Product product : all)
				product.setLocale(WebUtil.getLocaleEnum(httpRequest));

			uiModel.addAttribute("products", all);
		}
		return "products/list";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Product product, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, product);
			return "products/create";
		}
		uiModel.asMap().clear();
		productDao.save(product);
		return "redirect:/products/"
				+ encodeUrlPathSegment(product.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Product());
		return "products/create";
	}

	// @RequestMapping(value = "/{id}", produces = "text/html")
	// public String show(@PathVariable("id") Long id, Model uiModel) {
	// uiModel.addAttribute("product", productDao.findOne(id));
	// uiModel.addAttribute("itemId", id);
	// return "products/show";
	// }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Product product, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, product);
			return "products/update";
		}
		uiModel.asMap().clear();
		productDao.save(product);
		return "redirect:/products/"
				+ encodeUrlPathSegment(product.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		populateEditForm(uiModel, productDao.findOne(id));
		return "products/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		Product product = productDao.findOne(id);
		productDao.delete(product);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/products";
	}

	void populateEditForm(Model uiModel, Product product) {
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("productpics", productPicDao.findAll());
		uiModel.addAttribute("sellerproducts", sellerProductDao.findAll());
	}

	String encodeUrlPathSegment(String pathSegment,
			HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {
		}
		return pathSegment;
	}

}
