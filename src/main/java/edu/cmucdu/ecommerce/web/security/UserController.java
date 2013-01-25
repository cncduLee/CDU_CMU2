package edu.cmucdu.ecommerce.web.security;

import java.beans.PropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.cmucdu.ecommerce.domain.user.UserDetail;
import edu.cmucdu.ecommerce.domain.user.security.Principal;

@RequestMapping("/security/users")
@Controller
@RooWebScaffold(path = "security/users", formBackingObject = Principal.class)
public class UserController {
	class UserDetailsPropertyEditor extends PropertyEditorSupport {
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			UserDetail user = 	userDetailDao.findOne(Long.parseLong(text));
			setValue(user);
		}
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(UserDetail.class,new UserDetailsPropertyEditor());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Principal principal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	for (ObjectError error : bindingResult.getAllErrors()) {
				System.out.println((error.toString())+"\n");
			} 
            populateEditForm(uiModel, principal);
            return "security/users/create";
        }
        uiModel.asMap().clear();
        principal.persist();
        return "redirect:/security/users/" + encodeUrlPathSegment(principal.getId().toString(), httpServletRequest);
    }
}
