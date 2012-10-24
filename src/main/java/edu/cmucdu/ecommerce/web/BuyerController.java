package edu.cmucdu.ecommerce.web;

import edu.cmucdu.ecommerce.domain.user.Buyer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/buyers")
@Controller
@RooWebScaffold(path = "buyers", formBackingObject = Buyer.class)
public class BuyerController {
}
