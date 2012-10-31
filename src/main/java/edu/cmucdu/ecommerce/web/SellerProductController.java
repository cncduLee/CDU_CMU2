package edu.cmucdu.ecommerce.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.dao.product.ProductPicDao;
import edu.cmucdu.ecommerce.dao.product.PromotionDao;
import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.domain.util.Description;
import edu.cmucdu.ecommerce.web.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/sellerproducts")
@Controller
@RooWebScaffold(path = "sellerproducts", formBackingObject = SellerProduct.class)
@SessionAttributes("picList")
public class SellerProductController {

	@Autowired
	SellerProductDao sellerProductDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductPicDao productPicDao;

	@Autowired
	PromotionDao promotionDao;

	@ModelAttribute("picList")
	public List<ProductPic> getSellerProductModel() {
		//System.out.println("seller product model method");
		return new ArrayList<ProductPic>();
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public String showdoc(@PathVariable("id") Long id,
			HttpServletResponse response, Model model,
			@ModelAttribute("picList") List<ProductPic> picList,
			HttpServletRequest httpServletRequest) {

		try {
			// response.setHeader("Content-Disposition", "inline;filename=\""
			// +pic.getUrl()+ "\"");
			OutputStream out = response.getOutputStream();
			ProductPic pic = picList.get(id.intValue());
			response.setContentType(pic.getImageType());

			IOUtils.copy(new ByteArrayInputStream(pic.getImage()), out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(
			@Valid SellerProduct sellerProduct,
			BindingResult bindingResult,
			Model uiModel,
			HttpServletRequest httpServletRequest,

			// this part is for uploading picture
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "uploadImages", required = false) MultipartFile uploadImages,
			@RequestParam(value = "img_cn_desc", required = false) String imgCnDesc,
			@RequestParam(value = "img_thai_desc", required = false) String imgThDesc,
			@RequestParam(value = "img_eng_desc", required = false) String imgEnDesc,
			@ModelAttribute("picList") List<ProductPic> picList) {
		if (action != null && action.equals("Upload")) {
			// uiModel.addAttribute("username", username);
			ProductPic pp = new ProductPic();
			if (uploadImages.getSize() > 0) {
				try {
					pp.setImage(uploadImages.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pp.setImageType(uploadImages.getContentType());
				pp.setDescription(new Description(imgThDesc, imgCnDesc,
						imgEnDesc));
				picList.add(pp);
				uiModel.addAttribute("picList", picList);
			}
			//System.out.println("it works!");
			populateEditForm(uiModel, sellerProduct);
			return "sellerproducts/create";
		}
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, sellerProduct);
			return "sellerproducts/create";
		}
		uiModel.asMap().clear();
		
		//sellerProduct.setImages((picList));
		sellerProductDao.save(sellerProduct);
		return "redirect:/sellerproducts/"
				+ encodeUrlPathSegment(sellerProduct.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel, SessionStatus status) {
		
		// clean all the session attribute
		((ModelMap) uiModel).clear();
		status.setComplete();
		
		SellerProduct sp = new SellerProduct();
		populateEditForm(uiModel, sp);
		return "sellerproducts/create";
	}

	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
		addDateTimeFormatPatterns(uiModel);
		
		SellerProduct sellerProduct = sellerProductDao.findOne(id);
		sellerProduct.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
		
		uiModel.addAttribute("sellerproduct", sellerProductDao.findOne(id));
		uiModel.addAttribute("itemId", id);
		return "sellerproducts/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1)
					* sizeNo;
			uiModel.addAttribute(
					"sellerproducts",
					sellerProductDao.findAll(
							new org.springframework.data.domain.PageRequest(
									firstResult / sizeNo, sizeNo)).getContent());
			float nrOfPages = (float) sellerProductDao.count() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("sellerproducts", sellerProductDao.findAll());
		}
		addDateTimeFormatPatterns(uiModel);
		return "sellerproducts/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid SellerProduct sellerProduct,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, sellerProduct);
			return "sellerproducts/update";
		}
		uiModel.asMap().clear();
		sellerProductDao.save(sellerProduct);
		return "redirect:/sellerproducts/"
				+ encodeUrlPathSegment(sellerProduct.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		populateEditForm(uiModel, sellerProductDao.findOne(id));
		return "sellerproducts/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		SellerProduct sellerProduct = sellerProductDao.findOne(id);
		sellerProductDao.delete(sellerProduct);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/sellerproducts";
	}

	void populateEditForm(Model uiModel, SellerProduct sellerProduct) {
		uiModel.addAttribute("sellerProduct", sellerProduct);
		// get the current user login
		Principal userDetails = (Principal) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<Seller> sellerList = new ArrayList<Seller>();
		sellerList.add((Seller) userDetails.getUser());
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("products", productDao.findAll());
		uiModel.addAttribute("productpics", productPicDao.findAll());
		uiModel.addAttribute("promotions", promotionDao.findAll());
		uiModel.addAttribute("sellers", sellerList);
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute(
				"sellerProduct_readydate_date_format",
				DateTimeFormat.patternForStyle("M-",
						LocaleContextHolder.getLocale()));
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
