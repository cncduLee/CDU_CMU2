package edu.cmucdu.ecommerce.web;

import edu.cmucdu.ecommerce.domain.user.security.Authority;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/authoritys")
@Controller
@RooWebScaffold(path = "authoritys", formBackingObject = Authority.class)
public class AuthorityController {
}
