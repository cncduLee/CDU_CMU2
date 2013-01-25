package edu.cmucdu.ecommerce.web.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsDetail {

	@RequestMapping("/detailPage")
	public String goToPage(){
		return "goodsDetail";
	}

}
