package edu.cmucdu.ecommerce.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.cmucdu.ecommerce.dao.user.BuyerDao;
import edu.cmucdu.ecommerce.domain.user.Buyer;
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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/buyers")
@Controller
@RooWebScaffold(path = "buyers", formBackingObject = Buyer.class)
public class BuyerController 
{

	@Autowired
    BuyerDao buyerDao;
	
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Buyer buyer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    		@RequestParam String username, @RequestParam String password) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, buyer);
            return "buyers/create";
        }
        
        Principal principal = new Principal();
        principal.setUsername(username);
        principal.setPassword(password);
        principal.setCheckEnabled(true);
        principal.setUser(buyer);
        Authority a = Authority.findAuthoritysByAuthorityLike("ROLE_BUYER").getResultList().get(0);
        AuthorityPrincipalAssignment as = new AuthorityPrincipalAssignment();
        as.setRoleId(a);
        as.setUsername(principal);
        buyer.setPrinciple(principal);
        principal.persist();
        as.persist();
        
        uiModel.asMap().clear();
        buyerDao.save(buyer);
        return "redirect:/buyers/" + encodeUrlPathSegment(buyer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Buyer());
        return "buyers/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) 
    {
    	Buyer buyer = buyerDao.findOne(id);
    	buyer.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
    	
        uiModel.addAttribute("buyer", buyerDao.findOne(id));
        uiModel.addAttribute("itemId", id);
        return "buyers/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, Model uiModel,
    		HttpServletRequest httpServletRequest) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
           
            List<Buyer> all = buyerDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent();
            for(Buyer buyer : all){
    	    	buyer.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
            }
            
            uiModel.addAttribute("buyers", buyerDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / sizeNo, sizeNo)).getContent());
            float nrOfPages = (float) buyerDao.count() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
        	List<Buyer> all = buyerDao.findAll();
            for(Buyer buyer : all){
    	    	buyer.setLocale(WebUtil.getLocaleEnum(httpServletRequest));
            }
            uiModel.addAttribute("buyers", buyerDao.findAll());
        }
        return "buyers/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Buyer buyer, BindingResult bindingResult, 
    		Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, buyer);
            return "buyers/update";
        }
        uiModel.asMap().clear();
        buyerDao.save(buyer);
        return "redirect:/buyers/" + encodeUrlPathSegment(buyer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, buyerDao.findOne(id));
        return "buyers/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Buyer buyer = buyerDao.findOne(id);
        buyerDao.delete(buyer);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/buyers";
    }
    
    void populateEditForm(Model uiModel, Buyer buyer) {
        uiModel.addAttribute("buyer", buyer);
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
