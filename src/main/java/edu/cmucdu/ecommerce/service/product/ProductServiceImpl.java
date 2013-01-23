package edu.cmucdu.ecommerce.service.product;

import java.util.ArrayList;
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
	public List<Product> getProductRandomly(int amount) {
		
		List<Product> productList = productDao.findAll();
		List<Product> randomList = new ArrayList<Product>();
		
		Random rd = new Random();
		
		for(int i = 0; i < amount; i++)
		{
			int index = (int) (rd.nextDouble() * productList.size());
            randomList.add(productList.get(index));
            productList.remove(index);
		}
		
		return randomList;
	}

	@Override
	public List<Product> getProductsByType(int type) {
		
		List<Product> all = new ArrayList<Product>();
		
		switch (type) {
		case 1:
			//get the all products
			all = productDao.findAll();
			break;
		case 2:
			//get the promotion(Hot sell) products
			
			break;
		case 3:
			//up coming(新品，即将上市)
			
			break;

		default:
			break;
		}
		return all;
	}

}
