package edu.cmucdu.ecommerce.dao.product;

import java.util.List;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import edu.cmucdu.ecommerce.domain.product.comment.Comment;


@RooJpaRepository(domainType = SellerProduct.class)
public interface SellerProductDao {
	
	public List<SellerProduct> getSellerProductByProductId(Long id); // search sellerproduct form product
	public List<SellerProduct> getSellerProdectBySellerId(Long id); // search sellerproduct form seller
	public SellerProduct getSellerProductByProductIdAndSellerId(Long pid, Long sid); // search sellerproduct form both
	public List<Comment> getCommentById(Long id);
}
