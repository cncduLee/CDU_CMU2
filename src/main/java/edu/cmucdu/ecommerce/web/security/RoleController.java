package edu.cmucdu.ecommerce.web.security;

import edu.cmucdu.ecommerce.domain.user.security.Authority;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/security/roles")
@Controller
@RooWebScaffold(path = "security/roles", formBackingObject = Authority.class)
public class RoleController {
}
