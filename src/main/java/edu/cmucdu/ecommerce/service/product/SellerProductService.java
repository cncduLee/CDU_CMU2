package edu.cmucdu.ecommerce.service.product;

import java.util.List;

import edu.cmucdu.ecommerce.domain.product.SellerProduct;

public interface SellerProductService {

	public List<SellerProduct> getSellerProductFromProductID(long id);

	public List<SellerProduct> getSellerProductFromSeller(long id);

	public SellerProduct getSellerProductFromSellerAndProductID(long productId,
			long sellerId);

}
