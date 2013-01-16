package edu.cmucdu.ecommerce.service.product;

import java.util.List;

import edu.cmucdu.ecommerce.domain.product.Product;


public interface ProductService {
	public List <Product> getProductRandomly(int amount);
}
