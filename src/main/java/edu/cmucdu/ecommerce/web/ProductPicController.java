package edu.cmucdu.ecommerce.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import edu.cmucdu.ecommerce.dao.product.ProductPicDao;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.web.util.WebUtil;

import org.apache.commons.io.IOUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@RequestMapping("/productpics")
@Controller
@RooWebScaffold(path = "productpics", formBackingObject = ProductPic.class)
public class ProductPicController {
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid ProductPic productPic, BindingResult bindingResult, Model uiModel,
    		@RequestParam("image") MultipartFile image,
    		HttpServletRequest httpServletRequest) {
    	
    	productPic.setImageType(image.getContentType());
    	
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, productPic);
            return "productpics/create";
        }
        
        
        uiModel.asMap().clear();
        productPicDao.save(productPic);
        return "redirect:/productpics/" + encodeUrlPathSegment(productPic.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel,
    		HttpServletRequest request) {
    	ProductPic pic = productPicDao.findOne(id);
    	
    	pic.setLocale(WebUtil.getLocaleEnum(request));
    	pic.setUrl(request.getContextPath()+"/productpics/showpic/"+id);
    	
        uiModel.addAttribute("productpic", pic);
        uiModel.addAttribute("itemId", id);
        return "productpics/show";
    }
    
    @RequestMapping(value = "/showpic/{id}", method = RequestMethod.GET)
    public String showdoc(	@PathVariable("id") Long id,
    						HttpServletResponse response,
    						Model model) {
    	ProductPic pic = productPicDao.findOne(id);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +pic.getUrl()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(pic.getImageType());

           IOUtils.copy( new ByteArrayInputStream(pic.getImage()) , out);
            out.flush();      
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
