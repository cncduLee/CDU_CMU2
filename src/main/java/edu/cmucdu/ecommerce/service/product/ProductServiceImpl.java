package edu.cmucdu.ecommerce.service.product;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cmucdu.ecommerce.dao.product.ProductDao;
import edu.cmucdu.ecommerce.domain.product.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Override
	@SuppressWarnings("null")
	public List<Product> getProductRandomly(int amount) {
		
		List<Product> productList = productDao.findAll();
		List<Product> randomProductList = null;
		
		Random random = new Random();
		int randomNum;
		
		for(int i = 0; i < 3; i++)
		{
			randomNum = random.nextInt(amount);
			randomProductList.add(productList.get(randomNum));
		}
		
		return randomProductList;
	}

}
