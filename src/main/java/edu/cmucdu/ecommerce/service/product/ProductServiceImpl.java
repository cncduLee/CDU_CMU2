package edu.cmucdu.ecommerce.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		
		for(int i = 0; i < 3; i++)
		{
			int index = (int) (rd.nextDouble() * productList.size());
            randomList.add(productList.get(index));
            productList.remove(index);
		}
		System.out.println(amount);
		System.out.println(productList.size());
		return randomList;
	}

}
