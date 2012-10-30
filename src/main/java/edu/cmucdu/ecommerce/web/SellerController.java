package edu.cmucdu.ecommerce.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.user.security.Authority;
import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import edu.cmucdu.ecommerce.domain.user.security.Principal;
import edu.cmucdu.ecommerce.web.util.WebUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/sellers")
@Controller
@RooWebScaffold(path = "sellers", formBackingObject = Seller.class)
public class SellerController {
		@Autowired
	    SellerDao sellerDao;
		@Autowired
	    SellerProductDao sellerProductDao;
	    @RequestMapping(method=RequestMethod.POST,produces = "text/html", value ="create",params="Upload")
	    public String uploadPic(Model uiModel, HttpServletRequest httpServletRequest,

	    				@RequestParam String username, @RequestParam String password,
	    				// this part is for uploading picture
	    				@RequestParam(value="action", required=false) String action,
	    				@RequestParam(value="image", required=false) MultipartFile uploadImages,
	    				@RequestParam(value="img_cn_desc", required=false) String imgCnDesc,
	    				@RequestParam(value="img_thai_desc", required=false) String imgThDesc,
	    				@RequestParam(value="img_eng_desc", required=false) String imgEnDesc){
	    	System.out.println("uploading");
	    	return "sellers/create";
	    }
		
	    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
	    public String create(@Valid Seller seller, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,

	    				@RequestParam String username, @RequestParam String password,
	    				// this part is for uploading picture
	    				@RequestParam(value="action", required=false) String action,
	    				@RequestParam(value="image", required=false) MultipartFile uploadImages,
	    				@RequestParam(value="img_cn_desc", required=false) String imgCnDesc,
	    				@RequestParam(value="img_thai_desc", required=false) String imgThDesc,
	    				@RequestParam(value="img_eng_desc", required=false) String imgEnDesc) {
	    	if (action != null && action.equals("Upload"))
	    	{
	    		
	    		
	    	}
	    	
	        if (bindingResult.hasErrors()) {
	            return "sellers/create";
	        }
	        Principal principal = new Principal();
	        principal.setUserName(username);
	        principal.setPassword(password);
	        principal.setEnabled(true);
	        principal.setUser(seller);
	        Authority a = Authority.findAuthoritysByAuthorityLike("ROLE_USER").getResultList().get(0);
	        AuthorityPrincipalAssignment as = new AuthorityPrincipalAssignment();
	        as.setRoleId(a);
	        as.setUsername(principal);
	        seller.setPrinciple(principal);
	        principal.persist();
	        as.persist();
	        uiModel.asMap().clear();
	        sellerDao.save(seller);
	        return "redirect:/sellers/" + encodeUrlPathSegment(seller.getId().toString(), httpServletRequest);
	    }
	    
	    @RequestMapping(params = "form", produces = "text/html")
	    public String createForm(Model uiModel) {
	        populateEditForm(uiModel, new Seller());
	        return "sellers/create";
	    }
	    
	    @RequestMapping(value = "/{id}", produces = "text/html")
	    public String show(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
	    	Seller seller =  sellerDao.findOne(id);
	    	seller.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
	    	
	        uiModel.addAttribute("seller",seller);
	        uiModel.addAttribute("itemId", id);
	        return "sellers/show";
	    }
	    
	    @RequestMapping(produces = "text/html")
	    public String list(
	    		@RequestParam(value = "page", required = false) Integer page, 
	    		@RequestParam(value = "size", required = false) Integer size, 
	    		Model uiModel, HttpServletRequest httpServletRequest) {
	        if (page != null || size != null) {
	            int sizeNo = size == null ? 10 : size.intValue();
	            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
	            List<Seller> all = sellerDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent();
	            for(Seller seller : all){
	    	    	seller.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
	            }
	            uiModel.addAttribute("sellers", all);
	            float nrOfPages = (float) sellerDao.count() / sizeNo;
	            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
	        } else {
	        	List<Seller> all = sellerDao.findAll();
	            for(Seller seller : all){
	    	    	seller.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
	            }
	            uiModel.addAttribute("sellers", all);
	        }
	        return "sellers/list";
	    }
	    
	    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	    public String update(@Valid Seller seller, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, seller);
	            return "sellers/update";
	        }
	        uiModel.asMap().clear();
	        sellerDao.save(seller);
	        return "redirect:/sellers/" + encodeUrlPathSegment(seller.getId().toString(), httpServletRequest);
	    }
	    
	    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
	        populateEditForm(uiModel, sellerDao.findOne(id));
	        return "sellers/update";
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	        Seller seller = sellerDao.findOne(id);
	        sellerDao.delete(seller);
	        uiModel.asMap().clear();
	        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
	        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
	        return "redirect:/sellers";
	    }
	    
	    void populateEditForm(Model uiModel, Seller seller) {
	        uiModel.addAttribute("seller", seller);
	        uiModel.addAttribute("sellerproducts", sellerProductDao.findAll());
	        uiModel.addAttribute("principals", Principal.findAllPrincipals());
	    }
	    
	    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
	        String enc = httpServletRequest.getCharacterEncoding();
	        if (enc == null) {
	            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
	        }
	        try {
	            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
	        } catch (UnsupportedEncodingException uee) {}
	        return pathSegment;
	    }
}
