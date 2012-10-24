package edu.cmucdu.ecommerce.web;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sellerproducts")
@Controller
@RooWebScaffold(path = "sellerproducts", formBackingObject = SellerProduct.class)
public class SellerProductController {
}
