package edu.cmucdu.ecommerce.web;

import edu.cmucdu.ecommerce.domain.user.Seller;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sellers")
@Controller
@RooWebScaffold(path = "sellers", formBackingObject = Seller.class)
public class SellerController {
}
