package edu.cmucdu.ecommerce.web;

import edu.cmucdu.ecommerce.domain.user.security.AuthorityPrincipalAssignment;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/authorityprincipalassignments")
@Controller
@RooWebScaffold(path = "authorityprincipalassignments", formBackingObject = AuthorityPrincipalAssignment.class)
public class AuthorityPrincipalAssignmentController {
}
