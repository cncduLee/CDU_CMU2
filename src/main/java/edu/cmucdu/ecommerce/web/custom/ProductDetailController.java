package edu.cmucdu.ecommerce.web.custom;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cmucdu.ecommerce.dao.product.SellerProductDao;
import edu.cmucdu.ecommerce.dao.product.comment.CommentDao;
import edu.cmucdu.ecommerce.dao.user.SellerDao;
import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.product.comment.Comment;
import edu.cmucdu.ecommerce.domain.user.Seller;
import edu.cmucdu.ecommerce.domain.user.UserDetail;
import edu.cmucdu.ecommerce.service.product.ProductService;
import edu.cmucdu.ecommerce.service.product.SellerProductService;




@Controller
public class ProductDetailController {

	@Autowired
	ProductService productService;
	@Autowired
	SellerDao sellerDao;
	@Autowired
	SellerProductDao sellerProductDao; 

	
	@Autowired
	HttpSession session;
	@Autowired
	CommentDao commentDao;

	@Autowired
	SellerProductService sellerProductService;


	@RequestMapping(value = "/goodsDetail", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest httpServletRequest) {

		//Get request product id from other page
		String productId = httpServletRequest.getParameter("productId");
		String sellerId = httpServletRequest.getParameter("sellerId");
		// List<Product> product = productDao.findOne(productId);

		long id = Long.parseLong(productId);
		SellerProduct sellerProduct = sellerProductDao.findOne(id);
		
		System.out.println(productId + "   "+ sellerId);

		long pid = Long.parseLong(productId);
		long sid = Long.parseLong(sellerId);
		// Get SellerProduct item from product id and seller id
//		SellerProduct sellerProduct = sellerProductService.getSellerProductFromSellerAndProductID(pid, sid);
		
//		HashMap<String,Object> productItem = new HashMap();
//		productItem.put("product", productService.getProductFromID(pid));
//		productItem.put("seller", sellerProduct.getSeller());
//		productItem.put("price", sellerProduct.getPrice());
//		productItem.put("promotion", sellerProduct.getPromotion());
//		productItem.put("pics", sellerProduct.getImages());
//		productItem.put("weight", sellerProduct.getWeight());
//		productItem.put("readyTime", sellerProduct.getReadyTime());
//		productItem.put("fruitDuration", sellerProduct.getDurationOfFruit());
//		productItem.put("brandName", sellerProduct.getBrandName());

		
		map.addAttribute("product", sellerProduct.getProduct());
		map.addAttribute("pics", sellerProduct.getProduct().getImages().get(0));
		map.addAttribute("piclist", sellerProduct.getProduct().getImages());
		map.addAttribute("seller", sellerProduct.getSeller());

		map.addAttribute("productid", sellerProduct.getId());
		
		loadData(map,httpServletRequest);

//		map.addAttribute("productInfo", productItem);

		
		return "goodsDetail";
	}

	
	
	
	public void loadData(ModelMap map,HttpServletRequest httpServletRequest){
		map.addAttribute("commentList", commentDao.findAll());
		//System.out.println("-!@----"+sellerProductDao.getCommentById(Long.valueOf(1)).get(0).getComment());
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public String addComments(
			@RequestParam Long productid,
			@RequestParam Long sellerid,
			@RequestParam String cotent,
			ModelMap errorMap){
		
		UserDetail user = (UserDetail) session.getAttribute("logined_user");
		if(user == null){
			return "redirect:toLoginPage";
		}
		Comment co = new Comment();
		co.setComment(cotent);
		co.setSellerProduct(sellerProductDao.findOne(productid));
		co.setCommenter(user);
		commentDao.save(co);
		
		return "redirect:goodsDetail?productId="+productid+"&sellerId="+sellerid;
	}
	
}
