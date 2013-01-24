package edu.cmucdu.ecommerce.service.product;

import java.util.List;

import edu.cmucdu.ecommerce.domain.product.Product;
import edu.cmucdu.ecommerce.domain.product.ProductPic;
import edu.cmucdu.ecommerce.domain.product.Promotion;


public interface ProductService {
	public List <Product> getProductRandomly(int amount);
	public List <Product> getProductsByType(int type);
	public List<ProductPic> getPictureFromID(long id);
	public Product getProductFromID(long id);
}
