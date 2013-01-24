package edu.cmucdu.ecommerce.dao.product;

import java.util.List;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = SellerProduct.class)
public interface SellerProductDao {
	public List<SellerProduct> getSellerProductByProductId(Long id);
	public List<SellerProduct> getSellerProdectBySellerId(Long id);
	public SellerProduct getSellerProductByProductIdAndSellerId(Long pid, Long sid);
	
}
